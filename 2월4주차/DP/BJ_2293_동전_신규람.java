package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2293_동전 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력 및 초기화
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coinlist = new int[n];
		int[] dp = new int[k + 1];
		dp[0]=1;
		
		//동전 종류 입력 받기
		for(int i=0;i<n;i++)
			coinlist[i]=Integer.parseInt(br.readLine());
		
		//동전 별로 dp 생성
		for (int i = 0; i < n; i++) {
			int coin = coinlist[i];
			for (int j = 1; j < k + 1; j++) {
				if(j-coin >=0)
					dp[j] = dp[j - coin] + dp[j]; //dp의 index는 가격, 값은 경우의 수
			}
		}
		//최종 결과 출력
		System.out.println(dp[k]);
	}
}
