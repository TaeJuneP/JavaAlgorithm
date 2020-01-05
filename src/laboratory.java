import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class laboratory {

    static boolean[][] visit;
    static ArrayList<int[]> virusArr = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> virusCombination = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<int[]> dfsQ = new LinkedList<>();
    static int N;
    static int max = 2;
    static int min = 999999;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int M = Integer.parseInt(st.nextToken());
                map[i][j] = M;
                if (M == 2) {
                    virusArr.add(new int[]{i, j});
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        combination(list, virusArr.size(), K, 0);

        for (int i = 0; i < virusCombination.size(); i++) {
            for (int j = 0; j < virusCombination.get(i).size(); j++) {
                dfsQ.offer(virusArr.get(virusCombination.get(i).get(j)));
            }
            max = 2;
            int[][] copyMap = new int[N][N];
            visit = new boolean[N][N];
            arrayCopy(map, copyMap);
            dfs(dfsQ, copyMap);
        }

        if (min == 999999) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void combination(ArrayList<Integer> list, int length, int r, int pivot) {
        if (r == 0) {
            ArrayList<Integer> obj;
            obj = (ArrayList<Integer>) list.clone();
            virusCombination.add(obj);
            return;
        }

        for (int i = pivot; i < length; i++) {
            list.add(i);
            combination(list, length, r - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void dfs(Queue<int[]> q, int[][] map) {
        int time = 0;
        int temp = 0;
        while (!q.isEmpty()) {
            boolean blank = false;
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                int[] loc = q.poll();
                int x = loc[0];
                int y = loc[1];
                visit[x][y] = true;

                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                        if (map[nx][ny] == 0 && !visit[nx][ny]) {
                            blank = true;
                            map[nx][ny] = 3;
                            q.offer(new int[]{nx, ny});
                        }
                        if (map[nx][ny] == 2 && !visit[nx][ny]) {
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            if (!blank) {
                temp++;
            } else {
                time += ++temp;
                temp = 0;
            }
        }

        if (check(map)) {
            if (min > time) {
                min = time;
            }
        }
    }

    public static void arrayCopy(int[][] array, int[][] copyarray) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(array[i], 0, copyarray[i], 0, N);
        }
    }

    public static boolean check(int[][] map) {
        loop:
        for (int o = 0; o < N; o++) {
            for (int p = 0; p < N; p++) {
                if (map[o][p] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
