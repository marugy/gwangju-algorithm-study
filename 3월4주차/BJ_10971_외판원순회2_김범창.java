package daily0329;

import java.io.*;
import java.util.*;

public class BJ_10971_외판원순회2_김범창 {
	
	static int minCost = Integer.MAX_VALUE;
	static int N;
	static int[][] field;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		field = new int[N][N];
		visited = new boolean[N];
		
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			
			for(int x=0; x<N; x++) {
				field[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0] = true;
		dfs(0, 0, 0);
		
		System.out.println(minCost);
		
		br.close();
	}
	
	static void dfs(int curr, int count, int cost) {
		if(count == N-1 && field[curr][0] > 0) {
			cost = cost + field[curr][0];
			minCost = Math.min(minCost, cost);
		}
		
		for(int i=1; i<N; i++) {
			if(!visited[i] && field[curr][i] > 0) {
				visited[i] = true;
				dfs(i, count + 1, cost + field[curr][i]);
				visited[i] = false;
			}
		}
	}
}
