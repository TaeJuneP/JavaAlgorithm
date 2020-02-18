import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class newGame2 {
    static int N;
    static int K;
    static int map[][];
    static int piece[][];
    static int direct[];
    static ArrayList<Integer>[][] position;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        piece = new int[K + 1][2];
        direct = new int[K + 1];
        position = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int M = Integer.parseInt(st.nextToken());
                map[i][j] = M;
                position[i][j] = new ArrayList();
            }
        }
        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            position[x - 1][y - 1].add(i);
            piece[i] = new int[]{x - 1, y - 1};
            direct[i] = d;
        }
        for (int i = 1; i < K + 1; i++) {
            int nx = piece[i][0] + dy[direct[i] - 1];
            int ny = piece[i][1] + dx[direct[i] - 1];
            if (nx < 0 || ny < 0 || N < nx || N < ny || map[nx][ny] == 2) {
                if (nx < 0) {
                    direct[i] = 4;
                } else if (ny < 0) {
                    direct[i] = 1;
                } else if (nx > N) {
                    direct[i] = 3;
                } else if (ny > N) {
                    direct[i] = 2;
                } else if (map[nx][ny] == 2) {
                    if (direct[i] == 1) {
                        direct[i] = 2;
                    } else if (direct[i] == 2) {
                        direct[i] = 1;
                    } else if (direct[i] == 3) {
                        direct[i] = 4;
                    } else if (direct[i] == 4) {
                        direct[i] = 3;
                    }
                }
                nx = piece[i][0] + dy[direct[i] - 1];
                ny = piece[i][1] + dx[direct[i] - 1];
                if (nx < 0 || ny < 0 || N < nx || N < ny || map[nx][ny] == 2) {
                    continue;
                }
            }
            int index = position[piece[i][0]][piece[i][1]].indexOf(i);
            System.out.println(index);
            for (int j = index; j < position[piece[i][0]][piece[i][1]].size();) {
                position[nx][ny].add(position[piece[i][0]][piece[i][1]].get(j));
                position[piece[i][0]][piece[i][1]].remove(j);
            }
            for (int k = 0; k < N; k++) {
                System.out.println(Arrays.toString(position[k]));
            }
        }
    }
}
