package com.ssafy.Feb25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_1987_알파벳_박은규 {

	static int R, C;
	static char[][] field;
	static int res = 1;
	static boolean[][] visited;
	static Set<Character> set;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// 표의 크기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 표의 정보
		field = new char[R][C];
		// 방문 여부
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = in.readLine();
			for (int j = 0; j < C; j++) {
				field[i][j] = s.charAt(j);
			}
		}
		
		// 현재 알파벳 지났는지 확인
		set = new HashSet<>();
		dfs(0, 0, 1);
		
		// 결과 출력
		System.out.println(res);
	}
	
	private static void dfs(int x, int y, int cnt) {
		// 현재 알파벳 정보 추가
		set.add(field[x][y]);
		// 방문 완료
		visited[x][y] = true;
		// 계속 진행 가능 여부
		boolean isValid = false;
		for (int i = 0; i < 4; i++) {
			// 진행 가능 여부
			if (x+dx[i] >= 0 && x+dx[i] < R && y+dy[i] >= 0 && y+dy[i] < C 
				&& !visited[x+dx[i]][y+dy[i]]) {
				// 진행 가능
				isValid = true;
				// 기존에 거친 알파벳인지 확인
				int temp = set.size();
				set.add(field[x+dx[i]][y+dy[i]]);
				if (temp == set.size()) {
					if (res < cnt) res = cnt;
					continue;
				}
				// 방문 처리
				visited[x+dx[i]][y+dy[i]] = true;
				// 다음 칸으로 진행
				dfs(x+dx[i], y+dy[i], cnt+1);
				// 방문 처리 해제
				visited[x+dx[i]][y+dy[i]] = false;
				set.remove(field[x+dx[i]][y+dy[i]]);
			}
		}
		// 진행 불가한 경우 지금까지의 경로 길이 계산
		if (!isValid && res < cnt) res = cnt;
		// 방문 처리 해제
		visited[x][y] = false;
	}
}
