package Study;

/*
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH
 * SWEA 4008 숫자만들기
 */

import java.util.*;
import java.io.*;

public class SW_4008_숫자만들기_김하영 {
    public static int N, max, min;
    public static int [] op;
    public static int [] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Test_case = Integer.parseInt(br.readLine());
        for(int T = 1; T < Test_case+1; T++){
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            op = new int [4];
            arr = new int [N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i< 4; i++){
                op[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i< N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Op(1,0,0,0,0,arr[0]);
            int result = max-min;
            System.out.println("#"+T+" "+result);

        }
    }
    public static void Op(int len, int a, int s, int m, int d, int result){
        if(len >= N){
            min= Math.min(min, result);
            max= Math.max(max, result);
            return;
        }
        if(a < op[0]){
            Op(len+1, a+1, s, m, d, result+arr[len]);
        }
        if(s < op[1]){
            Op(len+1, a, s+1, m, d, result-arr[len]);
        }
        if(m < op[2]){
            Op(len+1, a, s, m+1, d, result*arr[len]);
        }
        if(d < op[3]){
            Op(len+1, a, s, m, d+1, result/arr[len]);
        }
    }
}
