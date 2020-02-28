import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class chickenDelivery {
    static int N;
    static ArrayList<int[]> chicken = new ArrayList<>();
    static int M;
    static ArrayList<int[]> home = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> chickenCombination = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 2) {
                    chicken.add(new int[]{i, j});
                } else if (k == 1) {
                    home.add(new int[]{i, j});
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        combination(list, chicken.size(), M, 0);
        calculateDistance();
        System.out.println(min);
    }

    public static void combination(ArrayList<Integer> list, int length, int r, int pivot) {
        if (r == 0) {
            ArrayList<Integer> obj;
            obj = (ArrayList<Integer>) list.clone();
            chickenCombination.add(obj);
            return;
        }

        for (int i = pivot; i < length; i++) {
            list.add(i);
            combination(list, length, r - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void calculateDistance() {
        int combinationSize = chickenCombination.size();
        int homeSize = home.size();
        for (int i = 0; i < combinationSize; i++) {
            int sum = 0;
            for (int k = 0; k < homeSize; k++) {
                int minDistance = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    int distance = Math.abs(home.get(k)[0] - chicken.get(chickenCombination.get(i).get(j))[0]) + Math.abs(home.get(k)[1] - chicken.get(chickenCombination.get(i).get(j))[1]);
                    if (minDistance > distance) {
                        minDistance = distance;
                    }
                }
                sum += minDistance;
            }
            if (min > sum) {
                min = sum;
            }
        }
    }
}
