package Study;

/*
 * https://www.acmicpc.net/problem/1941
 * 백준 1941번, 소문난 칠공주
 */
import java.util.*;
import java.io.*;

public class 백준_1941_김하영  {
    public static char [][] Map = new char[5][5];
    public static ArrayList<int[]> queue = new ArrayList<>();
    public static int[] arr = new int[7];
    public static int result = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i< 5; i++){
            String temp = br.readLine();
            for(int j = 0; j< 5; j++){
                Map[i][j] = temp.charAt(j);
            }
        }
        Combi(0,0);
        System.out.println(result);
    }


    public static boolean BFS(int x, int y){
        int [] dx = {0, 1, 0, -1};
        int [] dy = {-1, 0, 1, 0};
        int cnt = 0;

        Queue <int[]> queue = new LinkedList<>();
        boolean [] visited = new boolean [7]; 
        queue.add(new int []{x, y}); // 조합의 0번째 인덱스가 파라미터로 들어오기에
        visited[0] = true; // 0번째 인덱스 방문 처리
        while(queue.peek() != null){
            int [] coor = queue.poll();
            if(Map[coor[0]][coor[1]] == 'S')cnt++; // 이어져 있는 좌표이기에 S이면 카운트
            for(int i = 0; i< 4; i++){ // 4방을 모두 탐색
                int new_x = coor[0] + dx[i];
                int new_y = coor[1] + dy[i];
                if(new_x >= 5 || new_x < 0 || new_y >= 5 || new_y < 0)continue; // 좌표 벗어나면 넘기기
                int temp = new_x*5+new_y; // 2차원 배열 좌표 정보를 1차원 정보로
                for(int j = 0; j< 7; j++){ // 뽑은 조합에 존재하면 이동
                    if(!visited[j] && arr[j] == temp){
                        queue.add(new int []{new_x, new_y});
                        visited[j] = true;
                        
                    }
                }
            }
        }

        if(cnt <4) return false; // S가 4개보다 적으면 실패
        for(int i = 0; i< 7; i++){
            if(!visited[i])return false; // 이어져 있지 않으면 표시
        }
        return true;
    }

    public static void Combi(int len, int start){
        if(len >= 7){
            if(BFS(arr[0]/5, arr[0]%5))result++; // 0번째 인덱스를 넣어서 시작
            return;
        }
        for(int i = start; i < 25; i++){ // x, y 좌표를 일차원 배열로 표현
            arr[len] = i;
            Combi(len+1, i+1);
        }
    }
}
