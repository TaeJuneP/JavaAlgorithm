import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class numberCombination {
    static int N;
    static ArrayList<Integer> plus = new ArrayList<>();
    static ArrayList<Integer> minus = new ArrayList<>();
    static int zeroCount = 0;
    static int oneCount = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int line = Integer.parseInt(br.readLine());
            if (line < 0) {
                minus.add(Math.abs(line));
            } else if (line > 1) {
                plus.add(line);
            } else if (line == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }
        Collections.sort(plus);
        Collections.sort(minus);
        if ((plus.size()) % 2 == 1) {
            max += plus.get(0);
            for (int i = plus.size() - 1; i >= 1; i -= 2) {
                max += plus.get(i) * plus.get(i - 1);
            }
        } else {
            for (int i = plus.size() - 1; i >= 0; i -= 2) {
                max += plus.get(i) * plus.get(i - 1);
            }
        }
        max += 1 * oneCount;

        if ((minus.size()) % 2 == 1) {
            for (int i = minus.size() - 1; i >= 1; i -= 2) {
                max += minus.get(i) * minus.get(i - 1);
            }
            if (zeroCount < 1) {
                max -= minus.get(0);
            }
        } else {
            for (int i = minus.size() - 1; i >= 0; i -= 2) {
                max += minus.get(i) * minus.get(i - 1);
            }
        }
        System.out.println(max);
    }
}
