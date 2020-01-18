import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class coin {
    static int N;
    static int K;
    static int[] coinArr;
    static int check = 0;
    static int min = 999999;
    static int count = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coinArr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            coinArr[i] = k;
        }
        for (int i = N - 1; i >= 0; i--) {
            if (coinArr[i] <= K) {
                check = coinArr[i];
                int j = (K / check);
                count += j;
                K = K - (j * check);
            }
        }
        System.out.println(count);

    }
}
