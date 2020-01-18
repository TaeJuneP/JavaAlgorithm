import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MST {
    static int N;
    static int K;
    static int[] parent;
    static boolean[] visit;
    static int kruskalResult = 0;
    static int primResult = 0;
    static PriorityQueue<Edge> kruskalQ = new PriorityQueue<>();
    static PriorityQueue<PrimEdge> primQ = new PriorityQueue<>();
    static ArrayList<ArrayList<PrimEdge>> list = new ArrayList<>();

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

    public static class PrimEdge implements Comparable<PrimEdge> {
        int end;
        int weight;

        PrimEdge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(PrimEdge o) {
            if (this.weight > o.weight) return 1;
            else if (this.weight == o.weight) return 0;
            else return -1;
        }

    }

    static public void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        visit = new boolean[N + 1];
        visit[0] = true;
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
            list.add(new ArrayList());
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(s).add(new PrimEdge(e, w));
            list.get(e).add(new PrimEdge(s, w));
            kruskalQ.offer(new Edge(s, e, w));
        }
//        while (!kruskalQ.isEmpty()) {
//            Edge a = kruskalQ.poll();
//            if (find(a.start) != find(a.end)) {
//                union(a.start, a.end);
//                kruskalResult += a.weight;
//            }
//        }
        for (int i = 0; i < list.get(1).size(); i++) {
            primQ.offer(list.get(1).get(i));
        }
        visit[1] = true;
        bfs();
//        System.out.println(kruskalResult);
        System.out.println(primResult);
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

    static public void bfs() {
        while (!primQ.isEmpty()) {
            PrimEdge p = primQ.poll();
            if (!visit[p.end]) {
                visit[p.end] = true;
                primResult += p.weight;
                for (int i = 0; i < list.get(p.end).size(); i++) {
                    primQ.offer(list.get(p.end).get(i));
                }
            }
        }
    }
}

