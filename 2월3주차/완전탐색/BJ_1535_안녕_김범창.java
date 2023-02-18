package study02;

import java.io.*;
import java.util.*;

public class BJ_1535_안녕_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] pleasure = new int[N+1];
		int[] hearth = new int[N+1];
		int[][] dp = new int[N+1][100];

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			pleasure[i] = Integer.parseInt(st1.nextToken());
			hearth[i] = Integer.parseInt(st2.nextToken());
		}
		
		for(int y=1; y<=N; y++) {
			for(int x=1; x<100; x++) {
				if(pleasure[y] <= x) {
					dp[y][x] = Math.max(dp[y-1][x-pleasure[y]] + hearth[y], dp[y-1][x]);
				} else {
					dp[y][x] = dp[y-1][x];
				}
			}
		}
		
		System.out.println(dp[N][99]);
		
		br.close();
	}
}
