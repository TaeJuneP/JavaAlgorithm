import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class babyShark {
    static int N;
    static int count = 0;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] map;
    static Queue<int[]> shark = new LinkedList<>();
    static int sharkSize = 2;
    static int sum = 0;
    static boolean[][] visit;
    static PriorityQueue<Loc> feed = new PriorityQueue<>();
    static int ateFeed = 0;

    public static class Loc implements Comparable<Loc> {
        int y;
        int x;

        Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Loc o) {
            if (this.y < o.y) return -1;
            else if (this.y == o.y) {
                if (this.x < o.x) {
                    return -1;
                } else {
                    return 0;
                }
            } else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int m = Integer.parseInt(st.nextToken());
                if (m == 9) {
                    shark.offer(new int[]{i, j});
                } else {
                    map[i][j] = m;
                }
            }
        }
        bfs();
        System.out.println(sum);
    }

    public static void bfs() {
        while (!shark.isEmpty()) {
            int size = shark.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] loc = shark.poll();
                int x = loc[0];
                int y = loc[1];
                visit[x][y] = true;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (0 <= nx && 0 <= ny && nx < N && ny < N && map[nx][ny] <= sharkSize && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        if (0 < map[nx][ny] && map[nx][ny] < sharkSize) {
                            feed.offer(new Loc(nx, ny));
                        } else {
                            shark.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            if (!feed.isEmpty()) {
                visit = new boolean[N][N];
                shark.clear();
                sum += count;
                count = 0;
                ateFeed++;
                if (ateFeed == sharkSize) {
                    sharkSize++;
                    ateFeed = 0;
                }
                Loc shackLoc = feed.poll();
                map[shackLoc.y][shackLoc.x] = 0;
                shark.offer(new int[]{shackLoc.y, shackLoc.x});
                feed.clear();
            }
        }
    }

}
