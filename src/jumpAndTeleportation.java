public class jumpAndTeleportation {
    public static void main(String[] args) {
        solution(5000);
    }

    public static int solution(int n) {
        int ans = 0;
        while (n != 0) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = n - 1;
                ans++;
            }
        }
        return ans;
    }
}
