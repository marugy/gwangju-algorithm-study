package com.ssafy.Feb25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2293_동전1_박은규 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// 동전의 종류와 만들 가치의 합
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// 인덱스에 해당하는 가치를 만들 수 있는 동전 조합의 수
		int[] dp = new int[k+1];
		// 초기화 - 0원: 동전 0개 사용하는 경우(1가지)
		dp[0] = 1;
		
		// 새로운 화폐의 종류를 한 개씩 새로 도입할 때마다
		// 해당 화폐의 값을 뺀 값에서 새로운 값을 만드는 경우의 수 발생
		for (int i = 0; i < n; i++) {
			int val = Integer.parseInt(in.readLine());
			for (int j = val; j <= k; j++) {
				dp[j] += dp[j-val];
			}
		}
		
		// 결과 출력
		System.out.println(dp[k]);
	}
}
