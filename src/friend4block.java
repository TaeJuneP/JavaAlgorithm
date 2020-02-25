public class friend4block {
    static int[] dx = {-1, 0, -1};
    static int[] dy = {0, -1, -1};
    static String[][] map;
    static int count = 1;
    static boolean[][] check;
    static int sum = 0;
    static boolean flag = true;

    public static void main(String[] args) {
        solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
//        solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"});
//        solution(8,5, new String[]{"HGNHU", "CRSHV", "UKHVL", "MJHQB", "GSHOT", "MQMJJ", "AGJKK", "QULKK"});
    }


    public static int solution(int m, int n, String[] board) {
        map = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = String.valueOf(board[i].charAt(j));
            }
        }
        while (flag) {
            flag = false;
            check = new boolean[m][n];
            checkBlock(m, n);
            removeBlock(m, n);
            moveBlock(m, n);
        }
        System.out.println(sum);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        return sum;
    }

    public static void checkBlock(int m, int n) {
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (!map[i][j].equals("0")) {
                    for (int k = 0; k < 3; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (0 <= x && 0 <= y && x < m && y < n) {
                            if (map[x][y].equals(map[i][j])) {
                                count++;
                            }
                            if (count == 4) {
                                check[i][j] = true;
                                for (int p = 0; p < 3; p++) {
                                    int cx = i + dx[p];
                                    int cy = j + dy[p];
                                    check[cx][cy] = true;
                                }
                            }
                        }
                    }
                }
                count = 1;
            }
        }
    }

    public static void removeBlock(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j]) {
                    sum++;
                    map[i][j] = "0";
                    flag = true;
                }
            }
        }
    }

    public static void moveBlock(int m, int n) {
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j].equals("0")) {
                    for (int k = i-1; k >= 0; k--) {
                        if (!map[k][j].equals("0")) {
                            map[i][j] = map[k][j];
                            map[k][j] = "0";
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
    }
}

