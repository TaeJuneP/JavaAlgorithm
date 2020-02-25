//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class babyShark {
//    static int N;
//    static int count = 0;
//    static int[] dx = {1, 0, 0, -1};
//    static int[] dy = {0, -1, 1, 0};
//    static int[][] map;
//    static Queue<int[]> shark = new LinkedList<>();
//    static int sharkSize = 2;
//    static int sum = 0;
//    static boolean[][] visit;
//    static PriorityQueue<Loc> feed = new PriorityQueue<>();
//    static int ateFeed = 0;
//
//    public static class Loc implements Comparable<Loc> {
//        int y;
//        int x;
//
//        Loc(int y, int x) {
//            this.y = y;
//            this.x = x;
//        }
//
//        @Override
//        public int compareTo(Loc o) {
//            if (this.y < o.y) return -1;
//            else if (this.y == o.y) {
//                if (this.x < o.x) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            } else return 1;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        map = new int[N][N];
//        visit = new boolean[N][N];
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                int m = Integer.parseInt(st.nextToken());
//                if (m == 9) {
//                    shark.offer(new int[]{i, j});
//                } else {
//                    map[i][j] = m;
//                }
//            }
//        }
//        bfs();
//        System.out.println(sum);
//    }
//
//    public static void bfs() {
//        while (!shark.isEmpty()) {
//            int size = shark.size();
//            count++;
//            for (int i = 0; i < size; i++) {
//                int[] loc = shark.poll();
//                int x = loc[0];
//                int y = loc[1];
//                visit[x][y] = true;
//                for (int k = 0; k < 4; k++) {
//                    int nx = x + dx[k];
//                    int ny = y + dy[k];
//                    if (0 <= nx && 0 <= ny && nx < N && ny < N && map[nx][ny] <= sharkSize && !visit[nx][ny]) {
//                        visit[nx][ny] = true;
//                        if (0 < map[nx][ny] && map[nx][ny] < sharkSize) {
//                            feed.offer(new Loc(nx, ny));
//                        } else {
//                            shark.offer(new int[]{nx, ny});
//                        }
//                    }
//                }
//            }
//            if (!feed.isEmpty()) {
//                visit = new boolean[N][N];
//                shark.clear();
//                sum += count;
//                count = 0;
//                ateFeed++;
//                if (ateFeed == sharkSize) {
//                    sharkSize++;
//                    ateFeed = 0;
//                }
//                Loc shackLoc = feed.poll();
//                map[shackLoc.y][shackLoc.x] = 0;
//                shark.offer(new int[]{shackLoc.y, shackLoc.x});
//                feed.clear();
//            }
//        }
//    }
//
//}

//package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*

 */

public class babyShark {

    static int N;
    static int sharkSize[] = {2,0}; // 0번째 -> 현재 사이즈, 1번째 -> 먹은 물고기의 수
    static int[][] fishMap;
    static int[][] sharkMap;
    static boolean[][] visited;
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    static int count= 0 ,result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        fishMap = new int[N][N];
        sharkMap = new int[N][N];
        visited = new boolean[N][N];

        int[] sharkLocation = new int[2]; //초기 상어의 위치

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int fish = Integer.parseInt(st.nextToken());
                if(fish != 9)
                    fishMap[i][j] = fish;
                else{
                    sharkLocation[0] = i;
                    sharkLocation[1] = j;
                }
            }
        }

        searchFish(sharkLocation[0],sharkLocation[1]);
        System.out.println(result);
    }

    public static void searchFish(int i , int j){
        Queue<int[]> q = new LinkedList<>();
        PriorityQueue<Point> nextLoc = new PriorityQueue<>();
        q.offer(new int[] {i,j});

        while(!q.isEmpty()){
            int size = q.size();
            int[] loc = q.poll();
            visited[loc[0]][loc[1]] = true;
            System.out.println(size);
            for(int k = 0; k<size; k++){
                for(int dir = 0; dir<4; dir++){
                    int r = loc[0] + dr[dir];
                    int c = loc[1] + dc[dir];

                    if(r >= 0 && c >= 0 && r < N && c < N){
                        //다음 갈 위치의 물고기가 상어보다 작거나 같다면
                        if(sharkSize[0] >= fishMap[r][c] && visited[r][c] != true){
                            // 위치를 이동
                            visited[r][c] = true;
                            sharkMap[r][c] = sharkMap[loc[0]][loc[1]] + 1;

                            //상어보다 작은 물고기라면 먹기
                            if(sharkSize[0] > fishMap[r][c] && fishMap[r][c]!=0){
                                System.out.println("eat"+r +" "+c);
                                nextLoc.offer(new Point(r,c));
                            }
                            else {
                                System.out.println("check"+r +" "+c);
                                q.offer(new int[]{r, c});
                            }
                        }
                    }
                }
            }

            if(nextLoc.size() != 0){
                for(int p=0; p<N; p++){
                    for(int o=0; o<N; o++){
                        System.out.print(fishMap[p][o]);
                    }
                    System.out.println();
                }
                System.out.println();
                for(int p=0; p<N; p++){
                    for(int o=0; o<N; o++){
                        System.out.print(sharkMap[p][o]);
                    }
                    System.out.println();
                }
                Point location = nextLoc.poll();
                int x = location.x;
                int y = location.y;
                nextLoc.clear();
                result += sharkMap[x][y];
                visited = new boolean[N][N];
                sharkMap = new int[N][N];
                q.clear();
                System.out.println("eat " + location.x + " , " + location.y);
                q.offer(new int[] {x, y});
                fishMap[x][y] = 0;

                sharkSize[1]++;
                if(sharkSize[0] == sharkSize[1]){
                    sharkSize[0]++;
                    sharkSize[1] = 0;
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                if (this.y < o.y) {
                    return -1;
                } else {
                    return 0;
                }
            } else if (this.x < o.x) {
                return -1;
            } else {
                return 1;
            }
        }
    }





}