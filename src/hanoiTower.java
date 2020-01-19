import java.io.*;

public class hanoiTower {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        bw.write((1 << n) - 1 +"\n");
        bw.flush();
        solve(n, 1, 3);
    }

    static public void solve(int n, int x, int y) throws IOException {
        if (n == 0) return;
        solve(n - 1, x, 6 - x - y);
        bw.write(x + " " + y+"\n");
        bw.flush();
        solve(n - 1, 6 - x - y, y);
    }
}
