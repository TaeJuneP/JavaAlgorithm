public class nomalSqure {
    static public void main(String args[]) {
        solution(3, 7);
    }

    static public void solution(int w, int h) {
        long x=w;
        long y=h;
        long gcdxy = gcd(x, y);
        long answer;
        long area = x*y;
        System.out.println(gcdxy);
        if (gcdxy == 1) {
            answer = (x + y - 1);
        } else {
            answer = x + y - gcdxy;
        }
        System.out.println(area-answer);
    }

    static public long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
