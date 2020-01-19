import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class makeBridge2 {
    static int N;
    static int K;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<int[]> dfsQ = new LinkedList<>();
    static Queue<int[]> bfsQ = new LinkedList<>();
    static int[] parent;
    static boolean[][] visit;
    static int kruskalResult = 0;
    static int edgeCount = 0;
    static PriorityQueue<Edge> kruskalQ = new PriorityQueue<>();


    public static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight > o.weight) return 1;
            else if (this.weight == o.weight) return 0;
            else return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][K];
        visit = new boolean[N][K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int o = Integer.parseInt(st.nextToken());
                map[i][j] = o;
                if (o == 1) {
                    dfsQ.offer(new int[]{i, j});
                    bfsQ.offer(new int[]{i, j});
                }
            }
        }
        int num = 0;
        while (!dfsQ.isEmpty()) {
            int[] location = dfsQ.poll();
            if (!visit[location[0]][location[1]]) {
                num++;
                dfs(location, num);
            }
        }
        parent = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            parent[i] = i;
        }
        while (!bfsQ.isEmpty()) {
            int direct = -1;
            int count = 0;
            int[] location = bfsQ.poll();
            int islandNum = map[location[0]][location[1]];
            bfs(direct, location, count, islandNum);
        }

        while (!kruskalQ.isEmpty()) {
            Edge a = kruskalQ.poll();
            if (find(a.start) != find(a.end)) {
                union(a.start, a.end);
                edgeCount++;
                kruskalResult += a.weight;
            }
        }
        if (num-1 != edgeCount) {
            System.out.println(-1);
        } else {
            System.out.println(kruskalResult);
        }
    }

    static public void dfs(int[] loc, int num) {
        int x = loc[0];
        int y = loc[1];
        map[x][y] = num;
        visit[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (0 <= nx && nx < N && 0 <= ny && ny < K) {
                if (map[nx][ny] == 1 && !visit[nx][ny]) {
                    int[] next = new int[]{nx, ny};
                    dfs(next, num);
                }
            }
        }
    }

    static public void bfs(int direct, int[] loc, int count, int islandNum) {
        int x = loc[0];
        int y = loc[1];
        if (direct == -1) {
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < N && 0 <= ny && ny < K) {
                    if (map[nx][ny] == 0) {
                        int[] next = new int[]{nx, ny};
                        direct = k;
                        count = 1;
                        bfs(direct, next, count, islandNum);
                    }
                }
            }
        } else {
            int nx = x + dx[direct];
            int ny = y + dy[direct];
            if (0 <= nx && nx < N && 0 <= ny && ny < K) {
                if (map[nx][ny] == 0) {
                    int[] next = new int[]{nx, ny};
                    count++;
                    bfs(direct, next, count, islandNum);
                } else if (map[nx][ny] != islandNum && 1 < count) {
                    kruskalQ.offer(new Edge(islandNum, map[nx][ny], count));
                }
            }
        }
    }


    static public int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
}
