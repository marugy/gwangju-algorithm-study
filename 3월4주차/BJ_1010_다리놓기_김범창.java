package daily0329;

import java.io.*;
import java.util.*;

public class BJ_1010_다리놓기_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[m+1][m+1];
			
			for(int i=1; i<=m; i++) {
				dp[i][0] = dp[i][i] = 1;
			}
			
			for(int i=2; i<=m; i++) {
				for(int j=1; j<=n; j++) {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
			
			sb.append(dp[m][n]).append("\n");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
}