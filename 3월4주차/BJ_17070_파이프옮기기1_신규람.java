package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17070_파이프옮기기1 {

	static int N;
	static int[][] arr;
	static int[][][] dp;

	static void makeDp() {
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if(arr[i][j]==1) continue;
				//가로 파이프
				dp[0][i][j] = dp[0][i][j-1] + dp[1][i][j-1];
				
				//세로 파이프
				dp[2][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
				
				//대각선 파이프
				if(arr[i-1][j]==1 || arr[i][j-1]==1) continue;
				dp[1][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		dp = new int[3][N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][1][2]=1;
		makeDp();
		System.out.println(dp[0][N][N]+dp[1][N][N]+dp[2][N][N]);
	}
}