package study03;

import java.io.*;
import java.util.*;

public class BJ_1987_알파벳_김범창 {
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int R, C;
	static char[][] board;
	static boolean[][] visited;
	static int maxCount = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		for(int y=0; y<R; y++) {
			String input = br.readLine();
			for(int x=0; x<C; x++) {
				board[y][x] = input.charAt(x);
			}
		}
		
		ArrayList<Character> list = new ArrayList<>();
		list.add(board[0][0]);
		visited[0][0] = true;
		dfs(0, 0, list);
		
		System.out.println(maxCount);
		
		br.close();
	}
	
	static void dfs(int x, int y, ArrayList<Character> list) {
		maxCount = Math.max(maxCount, list.size());
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			boolean isPossible = true;
			if(0 <= nx && nx < C && 0 <= ny && ny < R && !visited[ny][nx]) {
				// 해당 알파벳을 이전에 만났었을때, 이동하지 않는다.
				for(Character c : list) {
					if(c == board[ny][nx]) {
						isPossible = false;
						break;
					}
				}
				
				if(isPossible) {
					visited[ny][nx] = true;
					list.add(board[ny][nx]);
					
					dfs(nx, ny, list);
					
					visited[ny][nx] = false;
					list.remove(list.size()-1);
				}
			}
		}
	}
}
