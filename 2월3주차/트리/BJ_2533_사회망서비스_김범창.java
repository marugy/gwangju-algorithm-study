package study02;

import java.io.*;
import java.util.*;

public class BJ_2533_사회망서비스_김범창 {
	
	static int N;
	static int[][] dp;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		dp(1, -1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		
		br.close();
	}
	
	static void dp(int cur,  int parent) {
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		
		for(int next : list.get(cur)) {
			// 부모노드로 접근을 허용하지 않는다.
			// 별도로 visited가 필요하지 않음
			if(next != parent) {
				dp(next, cur);
				dp[cur][0] += dp[next][1];
				dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}
}