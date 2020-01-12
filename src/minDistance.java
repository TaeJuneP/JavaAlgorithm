import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class minDistance {
    static int N;
    static int K;
    static boolean[] visit;
    static int[] dist;
    static ArrayList<int[]> minArr = new ArrayList<>();
    static int inf = 100000001;
    static int[] min = new int[]{0, inf};
    static int minX;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] arr = (ArrayList<int[]>[]) new ArrayList[N + 1];
        dist = new int[N + 1];
        visit = new boolean[N + 1];
        Arrays.fill(dist, inf);
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int[] arr1 = new int[2];
            arr1[0] = j;
            arr1[1] = l;
            arr[k].add(arr1);
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dist[start] = 0;
        dfs(start, arr);
        System.out.println(dist[end]);
    }

    static public void dfs(int v, ArrayList<int[]>[] arr) {
        visit[v] = true;
        for (int i = 0; i < arr[v].size(); i++) {
            if (!visit[arr[v].get(i)[0]] && dist[arr[v].get(i)[0]] > dist[v] + arr[v].get(i)[1]) {
                dist[arr[v].get(i)[0]] = dist[v] + arr[v].get(i)[1];
                minArr.add(new int[]{arr[v].get(i)[0], dist[arr[v].get(i)[0]]});
            }
        }
        int arrSize = minArr.size();
        if (arrSize != 0) {
            min = new int[]{0, inf};
            for (int i = 0; i < arrSize; i++) {
                if (min[1] > minArr.get(i)[1]) {
                    min = minArr.get(i);
                    minX = i;
                }
            }
            min = minArr.get(minX);
            minArr.remove(minX);
            dfs(min[0], arr);
        }
    }
}
