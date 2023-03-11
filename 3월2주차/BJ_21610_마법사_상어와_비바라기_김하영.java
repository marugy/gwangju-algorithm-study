package Study;

/*
 * https://www.acmicpc.net/problem/21610
 * 백준 21610, 마법사 상어와 비바라기
 */


/*
 * 1. 모든 구름 칸 이동
2. 구름이 있는 칸 물 1만큼 증가(구름은 사라짐-기억해두어야 함)
3. 기억해 둔 곳을 바탕으로 대각선 확인후 물의 양 증가
4. 기억해 둔 곳을 제외하고 물의 양이 2 이상인 모든 칸에 구름 생성(생성된 곳에 물 2만큼 빼주어야함)

 */
import java.util.*;
import java.io.*;

public class BJ_21610_마법사_상어와_비바라기_김하영 {
    static int [][] Map, Command;
    static ArrayList<int[]> queue = new ArrayList<>();
    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map = new int[N+1][N+1];
        Command = new int[M][2];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        queue.add(new int[] {N,1});
        queue.add(new int[] {N,2});

        queue.add(new int[] {N-1,1});
        queue.add(new int[] {N-1,2});

        for(int i=0; i<M; i++){
            boolean [][] visited;
            st = new StringTokenizer(br.readLine());
            Command[i][0] = Integer.parseInt(st.nextToken());
            Command[i][1] = Integer.parseInt(st.nextToken());
            Move(Command[i][0], Command[i][1]); // 이동 및 물의 양 1 증가
            visited = Increase(); // 바뀐 큐의 정보를 기반으로 대각선 확인 후 물의 양 증가
            // 기억해둔 곳을 배열로 하나 생성하여 반환
            MakeCloud(visited); // 배열을 기반으로 false이며, 물의 양이 2 이상인 모든 칸에 구름 생성 
        }
        System.out.println(CountWater());

    }
    static void Move(int dir, int speed){

        ArrayList<int[]> new_queue = new ArrayList<>();
        int [] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        int [] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

        for(int i=0; i< queue.size(); i++){
            int [] temp = queue.get(i);
            int x = temp[0];
            int y = temp[1];

            for(int j=0; j<speed; j++){
                int new_x = x+dx[dir];
                int new_y = y+dy[dir];
                if(new_x<=0){
                    new_x = N;
                }
                if(new_x>N){
                    new_x = 1;
                }
                if(new_y<=0){
                    new_y = N;
                }
                if(new_y>N){
                    new_y = 1;
                }
                x = new_x;
                y = new_y;
            }
            Map[x][y] += 1;
            new_queue.add(new int[] {x, y});

        }

        queue = new_queue;
    }
    static boolean[][] Increase(){
        boolean [][] Check = new boolean[N+1][N+1];
        int [] dx = {-1, -1, 1, 1};
        int [] dy = {-1, 1, -1, 1}; 
        for(int i=0; i< queue.size(); i++){
            int [] temp = queue.get(i);
            int x = temp[0];
            int y = temp[1];
            int cnt = 0;
            Check[x][y] = true;
            for(int j=0; j<4; j++){
                int new_x = x+dx[j];
                int new_y = y+dy[j];
                if(new_x>N || new_x<=0 || new_y>N || new_y<=0){
                    continue;
                }
                if(Map[new_x][new_y]>=1){
                    cnt++;
                }
            }
            Map[x][y] += cnt;

        }
        return Check;

    }
    static void MakeCloud(boolean [][] visited){
        ArrayList<int[]> new_queue = new ArrayList<>();

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(visited[i][j]){
                    continue;
                }
                if(Map[i][j]>=2){
                    Map[i][j] -= 2;
                    new_queue.add(new int [] {i, j});
                }

            }
        }
        queue = new_queue;
    }
    static int CountWater(){
        int result =0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                result += Map[i][j];
            }
        }
        return result;
    }
}
