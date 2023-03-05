
/*
 * https://www.acmicpc.net/problem/20056
 * 백준 20056, 마법사 상어와 파이어볼
 */

import java.util.*;
import java.io.*;

public class Main01 {
    static int N, M, K;
    static ArrayList<ArrayList<Queue<int[]>>> Map = new ArrayList<>(); // 2차원 배열에 큐를 넣어서 풀음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<= N; i++){
            Map.add(new ArrayList<Queue<int[]>>());
            ArrayList<Queue<int[]>> temp = Map.get(i);
            for(int j=0; j<= N;j++){
                temp.add(new LinkedList<int[]>());
            }
        }
        boolean [][] visited = new boolean[N+1][N+1];
        for(int i=0; i< M; i++){
            int [] temp = new int [5];
            st= new StringTokenizer(br.readLine());
            temp[0] = Integer.parseInt(st.nextToken());
            temp[1] = Integer.parseInt(st.nextToken());
            temp[2] = Integer.parseInt(st.nextToken());
            temp[3] = Integer.parseInt(st.nextToken());
            temp[4] = Integer.parseInt(st.nextToken());
            visited[temp[0]][temp[1]] =true;
            Map.get(temp[0]).get(temp[1]).add(new int[] {temp[2],temp[3],temp[4]});
            
        }
        for(int i=0; i< K; i++){
            visited = Check(visited);
        }
        System.out.println(Count(visited));

    }
    static int Count(boolean [][] visited){
        int sum=0;
        for(int i=1; i<= N; i++){
            for(int j=1; j<= N; j++){
                if(visited[i][j]){
                    Queue<int[]> temp_queue = Map.get(i).get(j);
                    while(!temp_queue.isEmpty()){
                        int [] temp = temp_queue.poll();
                        sum += temp[0];
                    }
                }
            }
        }
        return sum;
    }
    static boolean[][] Check(boolean [][] visited){
        boolean [][] new_visit = new boolean[N+1][N+1];
        ArrayList<ArrayList<Queue<int[]>>> n_Map = new ArrayList<>();  // 기존꺼에 새로 이동시킨게 더해져서 문제였음... 새로 이동할 자료구조 생성해서 해결
        /* 새로운 위치 정보 자료구조 생성 */
        for(int i=0; i<= N; i++){
            n_Map.add(new ArrayList<Queue<int[]>>());
            ArrayList<Queue<int[]>> temp = n_Map.get(i);
            for(int j=0; j<= N;j++){
                temp.add(new LinkedList<int[]>());
            }
        }
        /* 처음 이동 */
        for(int i=1; i<= N; i++){
            for(int j=1; j<= N; j++){
                if(visited[i][j]){
                    Queue<int[]> temp_queue =  Map.get(i).get(j); // 쌓여있는 것들
                    Queue<int[]> new_queue =  null;
                    while(!temp_queue.isEmpty()){ // 이동시키기 -- 여기서 문제였음, 기존꺼 + 새로운거가 더해지니, 여러번 이동하는 것 발생
                        int [] temp = temp_queue.poll();
                        int [] new_move = Move(i,j,temp[0],temp[1],temp[2]);
                        new_visit[new_move[0]][new_move[1]] = true;
                        new_queue = n_Map.get(new_move[0]).get(new_move[1]);
                        new_queue.add(new int[] {temp[0],temp[1], temp[2]});
                    }
                }
            }
        }
        /* 하나에 여러개 있으면 걔네들 나누어주기 */
        for(int i=1; i<= N; i++){
            for(int j=1; j<= N; j++){
                if(new_visit[i][j]){

                    Queue<int[]> temp_queue =  n_Map.get(i).get(j);
                    int size = temp_queue.size();
                    if(size > 1){
                        int m_sum=0;
                        int s_sum=0;
                        int d_fir=-1;
                        boolean even = true;
                        int k=0;
                        while(!temp_queue.isEmpty()){
                            int [] temp = temp_queue.poll();
                            if(k==0){
                                d_fir = temp[2] % 2;
                            }else{
                                if(temp[2] % 2 != d_fir){
                                    even = false;
                                }
                            }
                            m_sum += temp[0];
                            s_sum += temp[1];
                            k++;
                        }
                        m_sum /= 5;
                        s_sum /= size;
                        if(m_sum > 0){
                            int [] n_dir={-1};
                            if(even){
                                n_dir = new int[] {0,2,4,6};
                            }else{
                                n_dir = new int[] {1,3,5,7};

                            }
                            for(int L: n_dir){
                                temp_queue.add(new int[] {m_sum, s_sum, L});
                            }
                        }
                        
                        
                    }

                }
            }
        }
        Map = n_Map; // 새로운 지도로 변환
        return new_visit; // 새로 방문해야할 위치 반환
    }
    static int[] Move(int X, int Y, int M, int S, int D){ // 이동하는 함수
        int [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int [] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        int num = S;

        int x = X;
        int y = Y;
        while(num>0){ // 넘어가면 반대편으로 이동
            int new_x = x+ dx[D];
            int new_y = y+ dy[D];
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
            num--;
        }
        return new int[] {x,y};
    }
}
