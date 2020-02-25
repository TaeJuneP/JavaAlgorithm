import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class matchingScore {
    static ArrayList<ArrayList> cutPages = new ArrayList<ArrayList>();
    static ArrayList<Double> basicScore = new ArrayList<>();
    static ArrayList<Double> externalLink = new ArrayList<>();
    static double maxScore;
    static int answer;

    public static void main(String[] args) {
//        solution("Blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
        solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"});
    }

    ;

    public static int solution(String word, String[] pages) {
        splitPage(word, pages);
        checkScore();
        checkMaxScore();
        System.out.println(basicScore);
        for(int i=0; i<cutPages.size();i++){
            System.out.println(cutPages.get(i));
        }
        return answer;
    }

    public static void splitPage(String word, String[] pages) {
        for (int i = 0; i < pages.length; i++) {
            Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(pages[i]);
            ArrayList<String> page = new ArrayList<>();
            if (matcher.find()) {
                System.out.println(matcher.group(1));
                page.add(matcher.group(1));
            }
            Pattern pattern1 = Pattern.compile("<a href=\\\"https://(.+?)\">");
            Matcher matcher1 = pattern1.matcher(pages[i]);
            double linkCount = 0;
            while (matcher1.find()) {
                linkCount++;
                page.add(matcher1.group(1));
            }
            Pattern pattern2 = Pattern.compile("(?i)([^a-zA-Z]?"+word+"[^a-zA-Z])");
            Matcher matcher2 = pattern2.matcher(pages[i]);
            double basicScoreCount = 0;
            while (matcher2.find()) {
                matcher2.group(1);
                basicScoreCount++;
            }

            externalLink.add(basicScoreCount / linkCount);
            basicScore.add(basicScoreCount);
            cutPages.add(page);
        }
    }

    public static void checkScore() {
        int pageLength = cutPages.size();
        for (int i = 0; i < pageLength; i++) {
            for (int j = 1; j < cutPages.get(i).size(); j++) {
                for (int k = 0; k < pageLength; k++) {
                    if (cutPages.get(i).get(j).equals(cutPages.get(k).get(0))) {
                        basicScore.set(k, externalLink.get(i) + basicScore.get(k));
                        break;
                    }
                }
            }
        }
    }

    public static void checkMaxScore() {
        maxScore = basicScore.get(0);
        int pageLength = cutPages.size();
        for (int i = 0; i < pageLength; i++) {
            if (basicScore.get(i) > maxScore) {
                maxScore = basicScore.get(i);
                answer = i;
            }
        }
    }
}
