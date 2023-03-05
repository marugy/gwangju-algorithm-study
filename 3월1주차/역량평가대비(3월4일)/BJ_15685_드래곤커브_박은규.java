package com.ssafy.hw.Mar04;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_15685_드래곤커브_박은규 {

	static int N;
	static boolean[][] visited;
	static ArrayList<Stack<Integer>> dList;
	static int[][] lastPoint;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		visited = new boolean[101][101];
		lastPoint =  new int[N][2];
		dList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			dList.add(new Stack<>());
		}
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = parseInt(st.nextToken());
			int y = parseInt(st.nextToken());
			visited[y][x] = true;
			int first = parseInt(st.nextToken());
			dList.get(i).push(first);
			lastPoint[i][0] = x + dx[first];
			lastPoint[i][1] = y + dy[first];
			visited[lastPoint[i][1]][lastPoint[i][0]] = true;
			int gen = parseInt(st.nextToken());
			for (int j = 0; j < gen; j++) {
				Stack<Integer> temp = (Stack)dList.get(i).clone();
				while (!temp.isEmpty()) {
					int dir = (temp.pop() + 1) % 4;
					dList.get(i).push(dir);
					lastPoint[i][0] += dx[dir];
					lastPoint[i][1] += dy[dir];
					visited[lastPoint[i][1]][lastPoint[i][0]] = true;
				}
			}
		}
		
		// 격자 돌면서 네 귀퉁이 모두 드래곤 커브의 일부인 격자 찾기
		int res = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]) {
					res++;
				}
			}
		}
		
		System.out.println(res);
		
	}

}
