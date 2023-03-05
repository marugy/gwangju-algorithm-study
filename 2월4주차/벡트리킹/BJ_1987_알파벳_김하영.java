/*
 * https://www.acmicpc.net/problem/1987
 * 백준 1987, 알파벳
 */
import java.io.*;
import java.util.*;

public class Main_1987_김하영 {
    public static char [][] Map;
    public static int R, C, result;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Map = new char[R][C];
        for(int i =0; i< R; i++){
            String temp = br.readLine();
            for(int j =0; j<C; j++){
                Map[i][j]=temp.charAt(j);
            }
        }
        DFS(0,0); //함수 시작
        System.out.println(result);
    }
    public static void DFS(int X, int Y){
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{X, Y, 0 | (1<<(Map[X][Y]-'A')), 1}); // 좌측 상단 시작
        int [] dx ={0,1,0,-1};
        int [] dy ={-1,0,1,0};
        while(!stack.isEmpty()){
            int []temp = stack.pop();
            int x= temp[0];
            int y = temp[1];
            int visited = temp[2];
            int sum = temp[3];
            result = Math.max(result, sum); // 결과 갱신
            for(int i =0; i< 4; i++){
                int new_x = x+dx[i];
                int new_y = y+dy[i];
                if(new_x<0||new_x>=R || new_y<0||new_y>=C||(visited & (1<<(Map[new_x][new_y]-'A')))!=0)continue; // 방문된 곳은 못감
                stack.add(new int[] {new_x,new_y,visited | (1<<(Map[new_x][new_y]-'A')), sum+1});  // 스택에 넣으면서 방문 처리

            }
        }
        
        
    }
}
