import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class gerrymandering {
    static int N;
    static int[] point;
    static ArrayList<ArrayList<Integer>> arr;
    static int min = Integer.MAX_VALUE;

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new ArrayList<>();
        point = new int[N + 1];
        for (int i = 0; i <= N; i++)
            arr.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                arr.get(i + 1).add(Integer.parseInt(st.nextToken()));
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int m = (int) Math.floor(N / 2);
        for (int i = 1; i <= m; i++) {
            combination(list, N + 1, i, 1);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void combination(ArrayList<Integer> list, int length, int r, int pivot) {
        if (r == 0) {
            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            boolean[] aVisit = new boolean[N + 1];
            boolean[] bVisit = new boolean[N + 1];
            aVisit[0] = true;
            bVisit[0] = true;
            for (int i = 1; i <= N; i++) {
                if (list.contains(i)) {
                    a.add(i);
                    bVisit[i] = true;
                } else {
                    b.add(i);
                    aVisit[i] = true;
                }
            }
            Queue<Integer> qA = new LinkedList<>();
            qA.offer(a.get(0));
            int aPoint = bfs(qA, aVisit);
            Queue<Integer> qB = new LinkedList<>();
            qB.offer(b.get(0));
            int bPoint = bfs(qB, bVisit);
            if (check(aVisit) && check(bVisit)) {
                if (min > Math.abs(aPoint - bPoint))
                    min = Math.abs(aPoint - bPoint);
            }
            return;
        }

        for (int i = pivot; i < length; i++) {
            list.add(i);
            combination(list, length, r - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    static public int bfs(Queue<Integer> q, boolean[] visit) {
        int sum=0;
        while (!q.isEmpty()) {
            int v = q.poll();
            visit[v] = true;
            sum += point[v];
            for (int i = 0; i < arr.get(v).size(); i++) {
                if (!visit[arr.get(v).get(i)]) {
                    visit[arr.get(v).get(i)] = true;
                    q.offer(arr.get(v).get(i));
                }
            }
        }
        return sum;
    }

    static public boolean check(boolean[] visit) {
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i]) {
                return false;
            }
        }
        return true;
    }
}
