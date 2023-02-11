package Study;
/*
 * https://www.acmicpc.net/problem/14713
 * 백준 14713번, 앵무새 
 */
import java.util.*;
import java.io.*;

class BJ_14713_앵무새_김하영{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String [][] arr= new String[N][];
        int [] idx = new int[N];
        for(int i = 0; i< N; i++){
            arr[i] = br.readLine().split(" ");
        }
        String[] pos = br.readLine().split(" ");

        for(int i = 0; i< pos.length; i++){
            String temp = pos[i];
            boolean flag = false;
            for(int j = 0; j < N; j++){
                if(idx[j] < arr[j].length && arr[j][idx[j]].equals(temp)){
                    idx[j]++;
                    flag = true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Impossible");
                return;
            }
        }
        for(int i = 0; i< N; i++){
            if(idx[i] < arr[i].length){
                System.out.println("Impossible");
                return;
            }
        }
        System.out.println("Possible");
        return;
        
    } 
}