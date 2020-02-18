import java.util.ArrayList;
import java.util.Arrays;

public class sticker {
    public static void main(String[] args) {
        int[] sticker = {14};
        solution(sticker);
    }

    public static int solution(int sticker[]) {
        int[] start1 = new int[sticker.length];
        int[] start2 = new int[sticker.length];
        int answer = 0;

        if(sticker.length ==1){
            return sticker[0];
        }

        for (int i = 0; i < sticker.length - 1; i++) {
            if (i - 2 >= 0) {
                start1[i] = start1[i - 2] + sticker[i];
            } else {
                start1[i] = sticker[i];
            }
            if (i - 3 >= 0) {
                if (start1[i] < start1[i - 3] + sticker[i])
                    start1[i] = start1[i - 3] + sticker[i];
            }
            if (answer < start1[i]) {
                answer = start1[i];
            }
        }

        for (int i = 0; i < sticker.length - 1; i++) {
            if (i - 2 >= 0) {
                start2[i] = start2[i - 2] + sticker[i + 1];
            } else {
                start2[i] = sticker[i + 1];
            }
            if (i - 3 >= 0) {
                if (start2[i] < start2[i - 3] + sticker[i + 1])
                    start2[i] = start2[i - 3] + sticker[i + 1];
            }
            if (answer < start2[i] ) {
                answer = start2[i] ;
            }
        }

        return answer;
    }
}