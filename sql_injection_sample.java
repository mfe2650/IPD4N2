// Automatic fix invoked by IPDefender: this agent is not allowed to create vulnerable code, either accidentally or on purpose.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class sql_injection_sample {
    public static void main(String[] args) {
        String url = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        if (url == null || dbUser == null || dbPass == null) {
            System.err.println("Please set DB_URL, DB_USER, and DB_PASS environment variables.");
            return;
        }

        String query = "SELECT id, username, role FROM users WHERE username = ?";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            System.out.print("Enter username: ");
            String userInput = scanner.nextLine();

            pstmt.setString(1, userInput);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                        "id=" + rs.getInt("id") +
                        ", username=" + rs.getString("username") +
                        ", role=" + rs.getString("role")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
