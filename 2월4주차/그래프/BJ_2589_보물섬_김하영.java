/*
 * https://www.acmicpc.net/problem/2589
 * 백준 2589번, 보물섬 
 */
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        ArrayList<int[]> L_info = new ArrayList<>();
        int result=0;
        char [][] Map = new char[L][W];
        
        for(int i = 0; i< L; i++){
            String temp = br.readLine();
            for(int j =0; j< W; j++){
                Map[i][j] = temp.charAt(j);
                if(Map[i][j] == 'L'){
                    L_info.add(new int[] {i,j});
                }
            }
        }
        boolean [][] visited = new boolean[L][W];
        Queue<int[]> queue = new LinkedList<>();
        for(int i =0; i< L_info.size(); i++){
            int [] temp = L_info.get(i);
            for(int j =0; j< L; j++){
                for(int k=0; k<W; k++){
                    visited[j][k] = false;
                }
            }
            //int visited = 0;
            queue.clear();
            queue.add(new int[] {temp[0], temp[1], 0});
            int [] dx = {-1,0,1,0};
            int [] dy = {0,1,0,-1};
            visited[temp[0]][temp[1]] = true;
            while(!queue.isEmpty()){
                int [] coor = queue.poll();
                int x= coor[0];
                int y = coor[1];
                int time = coor[2];
                result = Math.max(result, time);
                
                //visited = visited |(1<<(x*L+y));
                for(int j =0; j< 4; j++){
                    int new_x = x+dx[j];
                    int new_y = y+dy[j];
                    if(new_x<0||new_x>=L||new_y<0||new_y>=W||Map[new_x][new_y]=='W'||visited[new_x][new_y]/*(visited&(1<<(L*new_x+new_y)))!=0*/){
                        continue;
                    }
                    queue.add(new int[] {new_x, new_y, time+1});
                    visited[new_x][new_y]=true;
                }
            }

        }
        System.out.println(result);

    }
}


// import java.util.*;
// import java.io.*;

// public class BJ_2589_보물섬_김하영{
//     static int  L, W;
//     static char [][] Map;
//     static int [] dx = {-1,0,1,0};
//     static int [] dy = {0,1,0,-1};
//     public static void main(String[] args) throws IOException{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         L = Integer.parseInt(st.nextToken());
//         W = Integer.parseInt(st.nextToken());
//         int result=0;
//         Map = new char[L][W];
        
//         for(int i = 0; i< L; i++){
//             String temp = br.readLine();
//             for(int j =0; j< W; j++){
//                 Map[i][j] = temp.charAt(j);
//             }
//         }

//         for(int a = 0; a< L; a++){
//             for(int b =0; b< W; b++){
//                 if(Map[a][b] == 'L'){
//                     result = Math.max(BFS(a, b), result);
//                 }
//             }
//         }
//         System.out.println(result);

//     }
//     public static int BFS(int X, int Y){
//         Queue<int[]> queue = new LinkedList<>();
//         boolean [][] visited = new boolean[L][W];
//         queue.add(new int[] {X, Y, 0});
//         visited[X][Y]=true;
//         int result = 0;
//         while(!queue.isEmpty()){
//             int [] coor = queue.poll();
//             // int x= coor[0];
//             // int y = coor[1];
//             int time = coor[2];
//             result = Math.max(result, time);
//             //visited[coor[0]][coor[1]]=true;

//             for(int j =0; j< 4; j++){
//                 // int new_x = x+dx[j];
//                 // int new_y = y+dy[j];
//                 int new_x = coor[0]+dx[j];
//                 int new_y = coor[1]+dy[j];
//                 if(new_x<0||new_x>=L||new_y<0||new_y>=W||Map[new_x][new_y]!='L'||visited[new_x][new_y]){
//                     continue;
//                 }
//                 // if(new_x>=0 && new_x<L && new_y>=0 && new_y<W && Map[new_x][new_y]=='L'&& !visited[new_x][new_y]){
//                 //     visited[new_x][new_y]=true;
//                 //     queue.add(new int[] {new_x, new_y, time+1});
//                 // }
//                 visited[new_x][new_y]=true;
//                 queue.add(new int[] {new_x, new_y, time+1});
                

//             }
//         }
//         return result;
//     }
// }

// /*
//  * https://ukyonge.tistory.com/125
//  */

// // import java.util.*;
// // import java.io.*;

// // public class BJ_2589_보물섬_김하영{
// //     static int  L, W;
// //     static char [][] Map;
// //     static int [] dx = {-1,0,1,0};
// //     static int [] dy = {0,1,0,-1};
// //     static boolean [][] visited;
// //     public static void main(String[] args) throws IOException{
// //         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// //         StringTokenizer st = new StringTokenizer(br.readLine());
// //         L = Integer.parseInt(st.nextToken());
// //         W = Integer.parseInt(st.nextToken());
// //         int result=0;
// //         Map = new char[L][W];
        
// //         for(int i = 0; i< L; i++){
// //             String temp = br.readLine();
// //             for(int j =0; j< W; j++){
// //                 Map[i][j] = temp.charAt(j);
// //             }
// //         }

// //         for(int a = 0; a< L; a++){
// //             for(int b =0; b< W; b++){
// //                 if(Map[a][b] == 'L'){
// //                     visited = new boolean[L][W];
// //                     result = Math.max(BFS(a, b), result);
// //                 }
// //             }
// //         }
// //         System.out.println(result);

// //     }
// //     public static int BFS(int X, int Y){
// //         Queue<int[]> queue = new LinkedList<>();
        
// //         queue.add(new int[] {X, Y, 0});
        
// //         int result = 0;
// //         while(!queue.isEmpty()){
// //             int [] coor = queue.poll();
// //             int x= coor[0];
// //             int y = coor[1];
// //             int time = coor[2];
// //             result = Math.max(result, time);
// //             visited[x][y]=true;
// //             for(int j =0; j< 4; j++){
// //                 int new_x = x+dx[j];
// //                 int new_y = y+dy[j];
// //                 if(new_x<0||new_x>=L||new_y<0||new_y>=W||Map[new_x][new_y]!='L'||visited[new_x][new_y]){
// //                     continue;
// //                 }
// //                 queue.add(new int[] {new_x, new_y, time+1});

// //             }
// //         }
// //         return result;
// //     }
// // }
