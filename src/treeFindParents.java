import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class treeFindParents {
    static int N;
    static int[] parents;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);
        }
        parents = new int[N + 1];
        parents[0] = 1;
        parents[1] = 1;

        dfs(1, arr);
        for(int i=2; i<=N; i++){
            System.out.println(parents[i]);
        }
    }

    static public boolean dfs(int i, ArrayList<Integer>[] arr) {
        for (int j = 0; j < arr[i].size(); j++) {
            if (parents[arr[i].get(j)] == 0) {
                parents[arr[i].get(j)] = i;
                dfs(arr[i].get(j), arr);
            }
        }
        return false;
    }
}
