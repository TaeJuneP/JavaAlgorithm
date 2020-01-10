import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class safeArea {
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N;
    static int k = 1;
    static int[][] map;
    static int max = 0;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int M = Integer.parseInt(st.nextToken());
                map[i][j] = M;
                if (k < M) {
                    k = M;
                }
            }
        }

        for (int i = 1; i < k; i++) {
            visit = new boolean[N][N];
            int count = 0;
            for (int j = 0; j < N; j++) {
                for (int o = 0; o < N; o++) {
                    if (!visit[j][o] && i < map[j][o]) {
                        count++;
                        dfs(i, j, o);
                    }
                }
            }
            if (max < count) {
                max = count;
            }
        }

        if (k == 1) {
            System.out.println(1);
        } else {
            System.out.println(max);
        }
    }

    static public void dfs(int i, int j, int o) {
        visit[j][o] = true;

        for (int dir = 0; dir < 4; dir++) {
            int nx = j + dx[dir];
            int ny = o + dy[dir];
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (i < map[nx][ny] && !visit[nx][ny]) {
                    dfs(i, nx, ny);
                }
            }
        }
    }
}
