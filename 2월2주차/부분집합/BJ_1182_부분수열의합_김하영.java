package Study;
/*
 * https://www.acmicpc.net/problem/
 * 백준 1182번, 부분수열의 합 
 */
import java.util.*;
import java.io.*;

public class BJ_1182_부분수열의합_김하영{
    public static int [] arr;
    public static int N, S, result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            SubSet(i, i+1, arr[i]);
        }
        System.out.println(result);
        return;

    }   
    
    public static void SubSet(int len, int start, int sum){
        if( sum == S){
            result++;
        }
        if(start >= N ) return;
        for(int i = start; i < N; i++){
            SubSet(len+1, i+1, sum+arr[i]);
        }
    }
}
