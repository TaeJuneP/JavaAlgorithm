import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class tomato {
    static boolean[][] visit;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N;
    static int K;
    static int[][] map;
    static int max = 0;


    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[K][N];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int M = Integer.parseInt(st.nextToken());
                map[i][j] = M;
                if (M == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        bfs(q);
        for (int i = 0; i < K; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        if (!check()) {
            System.out.println(-1);
        } else if(max==0){
            System.out.println(max);
        }else{
            System.out.println(max-1);
        }
    }

    static public void bfs(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int[] loc = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = loc[0] + dx[k];
                int ny = loc[1] + dy[k];
                System.out.println(N);
                if (0 <= nx && nx < K && 0 <= ny && ny < N) {
                    System.out.println(map[loc[0]][loc[1]]);
                    if (map[nx][ny] == 0 ) {
                        map[nx][ny] = map[loc[0]][loc[1]] + 1;
                        q.offer(new int[]{nx, ny});
                        if (map[nx][ny] > max) {
                            max = map[nx][ny];
                        }
                    }
                }
            }
        }
    }

    //|| map[nx][ny] > map[loc[0]][loc[1]] + 1
    static public boolean check() {
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
