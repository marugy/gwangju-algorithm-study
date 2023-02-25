package study03;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class BJ_2589_보물섬_김범창 {
	static class Point {
		int x;
		int y;
		int count;
		
		Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	
	static int X, Y;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		int result = 0;
		
		for(int y=0; y<Y; y++) {
			map[y] = br.readLine().toCharArray();
		}

		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++) {
				if(map[y][x] == 'L') {
					visited = new boolean[Y][X];
					int maxCount = bfs(x, y);
					
					result = Math.max(result, maxCount);
				}
			}
		}
		
		System.out.println(result);
		
		br.close();
	}
	
	static int bfs(int x, int y) {
		int maxCount = 0;
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(x, y, 0));
		visited[y][x] = true;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(isInArray(nx, ny) && map[ny][nx] == 'L' && !visited[ny][nx]) {
					visited[ny][nx] = true;
					que.add(new Point(nx, ny, p.count+1));
					maxCount = Math.max(maxCount, p.count+1);
				}
			}
		}
		
		return maxCount;
	}
	
	static boolean isInArray(int x, int y) {
		if(0 <= y && y < Y && 0 <= x && x < X) {
			return true;
		}else {
			return false;
		}
	}
}
