package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17070_½Å°ü¿ì {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int dp[][][] = new int[3][n + 1][n + 1];
		int a = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a = Integer.parseInt(st.nextToken());
				if (a != 0) {
					dp[0][i + 1][j + 1] = -1;
					dp[1][i + 1][j + 1] = -1;
					dp[2][i + 1][j + 1] = -1;
				}
			}
		}

		dp[0][1][2] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (dp[0][i][j] > 0) {
					if (j < n) {
						if (dp[0][i][j + 1] >= 0) {
							dp[0][i][j + 1] += dp[0][i][j];
						}
						if (i < n) {
							if (dp[2][i][j + 1] >= 0 && dp[2][i + 1][j] >= 0 && dp[2][i + 1][j + 1] >= 0) {
								dp[2][i + 1][j + 1] += dp[0][i][j];
							}
						}
					}
				}
				if (dp[1][i][j] > 0) {
					if (i < n) {
						if (dp[1][i + 1][j] >= 0) {
							dp[1][i + 1][j] += dp[1][i][j];
						}
						if (j < n) {
							if (dp[2][i][j + 1] >= 0 && dp[2][i + 1][j] >= 0 && dp[2][i + 1][j + 1] >= 0) {
								dp[2][i + 1][j + 1] += dp[1][i][j];
							}
						}
					}
				}
				if (dp[2][i][j] > 0) {
					if (j < n) {
						if (dp[0][i][j + 1] >= 0 && j < n) {
							dp[0][i][j + 1] += dp[2][i][j];
						}
					}
					if (i < n) {
						if (dp[1][i + 1][j] >= 0 && i < n) {
							dp[1][i + 1][j] += dp[2][i][j];
						}
					}
					if (i < n && j < n) {
						if (dp[2][i][j + 1] >= 0 && dp[2][i + 1][j] >= 0 && dp[2][i + 1][j + 1] >= 0 && j < n
								&& i < n) {
							dp[2][i + 1][j + 1] += dp[2][i][j];
						}
					}
				}
			}
		}

//		for (int i = 0; i < 3; i++) {
//			System.out.println(Arrays.deepToString(dp[i]));
//		}

		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += dp[i][n][n];
		}

		if (sum <= 0)
			System.out.println(0);
		else
			System.out.println(sum);
	}
}
