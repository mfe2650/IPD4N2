import java.io.FileInputStream;
import java.io.IOException;

public class bug {
    // ログイン失敗時は null を返す
    static String getSessionToken(boolean success) {
        if (!success) {
            return null;
        }
        return "TOKEN-12345";
    }

    // 意図的なバグ: FileInputStream を close しない
    static void loadConfig(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[128];
        int size = fis.read(buffer);
        System.out.println("Read bytes: " + size);
    }

    static void connectDatabase() {
        // 意図的なバグ: 認証情報をハードコード
        String user = "admin";
        String password = "P@ssw0rd123";
        System.out.println("Connecting with user=" + user + ", pass=" + password);
    }

    public static void main(String[] args) throws Exception {
        connectDatabase();

        loadConfig("config.txt");

        String token = getSessionToken(false);
        // 意図的なバグ: null チェックなしでメソッド呼び出し
        System.out.println("Token length: " + token.length());
    }
}
