import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class makeBridge {
    static boolean[][] visit;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int num = 0;
    static Queue<int[]> bfsQ = new LinkedList<>();
    static int min = 9999999;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        int[][] bridge = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int K = Integer.parseInt(st.nextToken());
                map[i][j] = K;
                if (K == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] location = q.poll();
            if (!visit[location[0]][location[1]]) {
                num++;
                dfsIsland(map, N, location);
            }
        }
        bfsBridge(map, N, bridge);
        System.out.println(min);
    }

    public static void dfsIsland(int[][] map, int N, int[] loc) {
        bfsQ.offer(loc);
        int x = loc[0];
        int y = loc[1];
        map[x][y] = num;
        visit[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (map[nx][ny] == 1 && !visit[nx][ny]) {
                    int[] next = new int[]{nx, ny};
                    dfsIsland(map, N, next);
                }
            }
        }
    }

    public static void bfsBridge(int[][] map, int N, int[][] bridge) {
        while (!bfsQ.isEmpty()) {
            int[] cut = bfsQ.poll();
            int x = cut[0];
            int y = cut[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (map[nx][ny] != 0 && map[nx][ny] != map[x][y]) {
                        if (min > bridge[nx][ny] + bridge[x][y]) {
                            min = bridge[nx][ny] + bridge[x][y];
                        }
                    }
                    if (map[nx][ny] == 0) {
                        bfsQ.offer(new int[]{nx, ny});
                        map[nx][ny] = map[x][y];
                        bridge[nx][ny] = bridge[x][y] + 1;
                    }
                }
            }
        }
    }
}
