package Study;
/*
 * https://www.acmicpc.net/problem/10830
 * 백준 10830번, 행렬 제곱
 */
import java.util.*;
import java.io.*;

public class BJ_10830_행렬제곱_김하영{
    public static int[][] in_arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        in_arr = new int[N][N];
        for(int i =0 ; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j< N; j++){
                in_arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [][] result = mulArr(N, B);
        for(int i =0; i< N; i++){
            for(int j =0; j< N; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[][] mulArr(int N, long depth){
        if(depth == 1){
            int [][] result = new int[N][N];
            for(int i=0; i<N; i++){
                for(int j =0; j< N; j++){
                    result[i][j] = in_arr[i][j]%1000;
                }
            }
            return result;
        }
        if(depth == 2){
            return Multiply(N, in_arr, in_arr);
        }
        if(depth%2==0){
            int [][] result = mulArr(N, depth/2);
            return Multiply(N, result, result);
        }else{
            return Multiply(N, in_arr, mulArr(N, depth-1));
        }
    }
    public static int[][] Multiply(int N, int[][] arr1, int[][] arr2){
        int [][] result = new int[N][N];

        for(int i =0; i< N; i++){
            
            for(int j = 0; j< N; j++){
                int temp = 0;
                for(int k = 0; k< N; k++){
                    temp += (arr1[i][k] * arr2[k][j])%1000;
                }
                result[i][j] = temp%1000;
            }
            
        }
        return result;
    }
}