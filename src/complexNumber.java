import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class complexNumber {

    static boolean[][] visit;
    static Queue<int[]> q = new LinkedList<>();
    static int num = 0;
    static ArrayList<Integer> countArr = new ArrayList<>();
    static int count;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                int K = line.charAt(j) - '0';
                map[i][j] = K;
                if (K == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] loc = q.poll();
            if (!visit[loc[0]][loc[1]]) {
                if (count != 0) {
                    countArr.add(count);
                }
                count = 0;
                num++;
                bfs(loc, map, N);
            }
        }
        if(count!=0) {
            countArr.add(count);
        }
        Collections.sort(countArr);
        System.out.println((num));
        for (int i = 0; i < countArr.size(); i++) {
            System.out.println(countArr.get(i));
        }
    }

    static public void bfs(int[] loc, int[][] map, int N) {
        int x = loc[0];
        int y = loc[1];
        count++;
        visit[x][y] = true;
        map[x][y]=num;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (map[nx][ny] != 0 && !visit[nx][ny]) {
                    int[] next = new int[]{nx, ny};
                    bfs(next, map, N);
                }
            }
        }
    }
}
