import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class alphabet {
    static int N;
    static int K;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static HashMap<String, Boolean> visit = new HashMap<>();
    static String[][] map;
    static int count = 1;
    static Queue<String> q = new LinkedList<>();
    static int max = 0;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new String[N][K];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < K; j++) {
                String M = String.valueOf(line.charAt(j));
                map[i][j] = M;
                visit.put(M, false);
            }
        }
        dfs(new int[]{0, 0});
        if (max == 0) {
            System.out.println(1);
        } else {
            System.out.println(max);
        }
    }

    static public void dfs(int[] loc) {
        int x = loc[0];
        int y = loc[1];
        visit.put(map[x][y], true);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < K) {
                if (!visit.get(map[nx][ny])) {
                    int[] next = new int[]{nx, ny};
                    count++;
                    if (count > max) {
                        dfs(next);
                    }
                }
            }
        }
        visit.put(map[x][y], false);
        count--;
    }

}

