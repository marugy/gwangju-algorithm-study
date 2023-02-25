package study03;

import java.io.*;
import java.util.*;

public class BJ_2293_동전1_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		int[][] dp = new int[n][k+1];
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			dp[i][0] = 1;
		}
		
		for(int j=1; j<=k; j++) {
			if(j%coin[0] == 0) {
				dp[0][j] = 1;
			}
		}

		for(int i=1; i<n; i++) {
			for(int j=1; j<=k; j++) {
				if(coin[i] <= j) {
					dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]];
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[n-1][k]);
		
		br.close();
	}
}
