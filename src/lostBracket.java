import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lostBracket {
    static String line;
    static long max = 0;
    static boolean check = false;
    static String num = "";
    static long temp = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '-') {
                System.out.println(temp);
                if (!check) {
                    max += Integer.parseInt(num);
                    check = true;
                } else {
                    temp += Integer.parseInt(num);
                }
                num = "";
            } else if (line.charAt(i) == '+') {
                System.out.println(temp);
                if (!check) {
                    max += Integer.parseInt(num);
                } else {
                    temp += Integer.parseInt(num);
                }
                num = "";
            } else {
                num += line.charAt(i);
            }
        }
        if (check) {
            temp += Integer.parseInt(num);
        } else {
            max += Integer.parseInt(num);
        }
        max -= temp;
        System.out.println(max);
    }
}
