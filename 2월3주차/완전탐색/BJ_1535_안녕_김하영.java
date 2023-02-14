/*
 * https://www.acmicpc.net/problem/1535
 * 백준 1535번, 안녕
 */
import java.util.*;
import java.io.*;

class BJ_1535_안녕_김하영{
    public static int N, result = Integer.MIN_VALUE;
    public static int [] Loss, Happy;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Loss = new int[N];
        for(int i=0; i< N; i++){
            Loss[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        Happy = new int[N];
        for(int i = 0; i< N; i++){
            Happy[i] = Integer.parseInt(st.nextToken());
        }
        Calc(0,0,100);
        System.out.println(result);
    }

    public static void Calc(int idx, int h_sum, int s_sum){
        if(idx >= N){
            result = Integer.max(result, h_sum);
            return;
        }
        for(int i = idx; i < N; i++){
            if(s_sum - Loss[i] > 0) {
                Calc(i+1, h_sum+Happy[i], s_sum-Loss[i]);
            }else{
                Calc(N, h_sum, s_sum);
            }
            
        }

    }
}