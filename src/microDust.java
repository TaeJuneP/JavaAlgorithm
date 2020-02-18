import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class microDust {
    static int R;
    static int C;
    static int T;
    static int[][][] map;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int[] change = new int[]{-1, 1, 1, -1};
    static ArrayList<int[]> airCleaner = new ArrayList<>();
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C][];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int M = Integer.parseInt(st.nextToken());
                if (M == -1) {
                    airCleaner.add(new int[]{i, j});
                }
                map[i][j] = new int[]{M, 0};
            }
        }
        bfs();
        dustSum();
        System.out.println(sum);
    }

    public static void bfs() {
        for (int k = 0; k < T; k++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (0 < map[i][j][0]) {
                        int A = map[i][j][0] / 5;
                        int cnt = 0;
                        for (int p = 0; p < 4; p++) {
                            int nx = i + dy[p];
                            int ny = j + dx[p];
                            if (0 <= nx && nx < R && 0 <= ny && ny < C && -1 < map[nx][ny][0]) {
                                cnt++;
                                map[nx][ny][1] += A;
                            }
                        }
                        map[i][j][0] = map[i][j][0] - A * cnt;
                    }
                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (0 < map[i][j][1]) {
                        map[i][j][0] += map[i][j][1];
                        map[i][j][1] = 0;
                    }
                }
            }
            rotatedAir();
        }
    }

    static void rotatedAir() {
        for (int j = airCleaner.get(0)[0] - 1; j > 0; j--) {
            map[j][0] = map[j - 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            map[0][j] = map[0][j + 1];
        }
        for (int j = 0; j < airCleaner.get(0)[0]; j++) {
            map[j][C - 1] = map[j + 1][C - 1];
        }
        for (int j = C - 1; j > 1; j--) {
            map[airCleaner.get(0)[0]][j] = map[airCleaner.get(0)[0]][j - 1];
        }
        map[airCleaner.get(0)[0]][1] = new int[]{0, 0};
        for (int j = airCleaner.get(1)[0] + 1; j < R - 1; j++) {
            map[j][0] = map[j + 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            map[R - 1][j] = map[R - 1][j + 1];
        }
        for (int j = R - 1; j > airCleaner.get(1)[0]; j--) {
            map[j][C - 1] = map[j - 1][C - 1];
        }
        for (int j = C - 1; j > 1; j--) {
            map[airCleaner.get(1)[0]][j] = map[airCleaner.get(1)[0]][j - 1];
        }
        map[airCleaner.get(1)[0]][1] = new int[]{0, 0};
    }

    public static void dustSum() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j][0] > 0) {
                    sum += map[i][j][0];
                }
            }
        }
    }
}

