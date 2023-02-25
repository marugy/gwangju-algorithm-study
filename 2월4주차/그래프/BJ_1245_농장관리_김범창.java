package study03;

import java.io.*;
import java.util.*;

public class BJ_1245_농장관리_김범창 {
	
	static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
	static int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
	static int N, M;
	static int[][] field;
	static boolean[][] visited;
	static boolean isPeak;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		field = new int[N][M];
		visited = new boolean[N][M];
		int feakCount = 0;
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<M; x++) {
				field[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(!visited[y][x]) {
					isPeak = true;
					dfs(x, y);
					feakCount += isPeak?1:0;
				}
			}
		}
		
		System.out.println(feakCount);
		
		br.close();
	}
	
	static void dfs(int x, int y) {
		visited[y][x] = true;
		
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(0 <= nx && nx < M && 0 <= ny && ny < N) {
				if(field[y][x] == field[ny][nx]) {
					if(!visited[ny][nx]) {
						dfs(nx, ny);
					}
				} else if(isPeak && field[y][x] < field[ny][nx]) {
					isPeak = false;
				}
			}
		}
	}
}
