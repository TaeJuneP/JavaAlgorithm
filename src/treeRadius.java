import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class treeRadius {
    static int N;
    static int[] dist;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visit;
    static int start = 1;
    static int max = 0;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] arr = (ArrayList<int[]>[]) new ArrayList[N + 1];
        dist = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int x = 0;
            int j[] = new int[2];
            while (x != -1) {
                x = Integer.parseInt(st.nextToken());
                if (j[0] == 0) {
                    j[0] = x;
                } else {
                    j[1] = x;
                    arr[k].add(j);
                    j = new int[2];
                }
            }
        }
        bfs(arr, start);
        arrMax(dist);
        visit = new boolean[N + 1];
        dist = new int[N + 1];
        bfs(arr, start);
        arrMax(dist);
        System.out.println(max);
    }

    static public void bfs(ArrayList<int[]>[] arr, int i) {
        q.offer(i);
        visit[i] = true;
        while (!q.isEmpty()) {
            int k = q.poll();
            for (int j = 0; j < arr[k].size(); j++) {
                if (!visit[arr[k].get(j)[0]]) {
                    dist[arr[k].get(j)[0]] = dist[k] + arr[k].get(j)[1];
                    q.offer(arr[k].get(j)[0]);
                    visit[arr[k].get(j)[0]] = true;
                }
            }
        }
    }

    static public void arrMax(int[] arr) {
        for (int i = 1; i <= N; i++) {
            if (max < dist[i]) {
                max = dist[i];
                start = i;
            }
        }

    }
}
