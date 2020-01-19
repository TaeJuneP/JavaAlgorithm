import java.util.Arrays;

public class coloringBook {
    public static void main(String[] args) {

        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println(solution(6, 4, picture));
    }

    static int count = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && picture[i][j] != 0) {
                    int[] loc = {i, j};
                    numberOfArea++;
                    count = 0;
                    dfs(loc, visit, m, n, picture);
                    if (maxSizeOfOneArea < count) {
                        maxSizeOfOneArea = count;
                    }
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void dfs(int[] loc, boolean[][] visit, int m, int n, int[][] picture) {
        int x = loc[0];
        int y = loc[1];
        visit[x][y] = true;
        count++;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                if (!visit[nx][ny] && picture[x][y] == picture[nx][ny]) {
                    dfs(new int[]{nx, ny}, visit, m, n, picture);
                }
            }
        }
    }
}
