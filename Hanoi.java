public class Hanoi {
    ///
    // これはAIが生成したハノイの塔の解法です。
    ///
    
    /**
     * n枚の円盤を from から to へ、aux を補助柱として移動する。
     */
    public static void solveHanoi(int a, char from, char aux, char to) {
        if (a <= 0) {
            return;
        }

        // 1) 上の n-1 枚を補助柱へ移動
        solveHanoi(a - 1, from, to, aux);

        // 2) 一番大きい円盤を目的柱へ移動
        System.out.println("円盤 " + a + " を " + from + " から " + to + " へ移動");

        // 3) 補助柱の n-1 枚を目的柱へ移動
        solveHanoi(a - 1, aux, from, to);
    }

    public static void main(String[] args) {
        int n = 3; // 円盤の枚数（必要なら変更）

        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("引数が不正です。デフォルト値 3 を使用します。");
            }
        }

        if (n < 1) {
            System.out.println("円盤の枚数は 1 以上で指定してください。");
            return;
        }

        System.out.println("ハノイの塔（円盤 " + n + " 枚）の手順:");
        solveHanoi(n, 'A', 'B', 'C');
    }
}
