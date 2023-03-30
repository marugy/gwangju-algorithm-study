package daily0329;

import java.io.*;
import java.util.*;

public class BJ_17070_파이프옮기기1_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] field = new int[N+1][N+1];
		
		for(int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++) {
				field[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N+1][N+1][3];
		dp[1][2][0] = 1;
		
		for(int y=1; y<=N; y++) {
			for(int x=3; x<=N; x++) {
				if(field[y][x] == 0) {
					dp[y][x][0] = dp[y][x-1][0] + dp[y][x-1][1];
				}
				
				if(field[y][x] == 0) {
					dp[y][x][2] = dp[y-1][x][1] + dp[y-1][x][2];
				}
				
				if(field[y][x] == 0 && field[y][x-1] == 0 && field[y-1][x] == 0 && field[y-1][x-1] == 0) {
					dp[y][x][1] = dp[y-1][x-1][0] + dp[y-1][x-1][1] + dp[y-1][x-1][2];
				}
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
		
		br.close();
	}
}
