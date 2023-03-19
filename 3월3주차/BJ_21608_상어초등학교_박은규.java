package com.ssafy.Mar18;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_21608_상어초등학교_박은규 {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		List<ArrayList<Integer>> fList = new ArrayList<>();
		int[][] seat = new int[N][N];
		
		for (int i = 0; i <= N*N; i++) {
			fList.add(new ArrayList<>());
		}
		
		// 입력
		StringTokenizer st;
		List<Integer> order = new ArrayList<>();
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(in.readLine());
			int num = parseInt(st.nextToken());
			order.add(num);
			for (int j = 0; j < 4; j++) {
				fList.get(num).add(parseInt(st.nextToken()));
			}
		}
		
		// 모든 칸 돌면서 비어있는 칸 중에 좋아하는 학생이 인접한 칸에 가장 많은 칸 탐색
		for (int s = 0; s < N*N; s++) {
			int student = order.get(s);
			List<Integer> sList = fList.get(student);
			List<int[]> seatList = new ArrayList<>();
			int adjStudent = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (seat[i][j] != 0) continue;
					int temp = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx >= 0 && nx < N && ny >= 0 && ny < N && seat[nx][ny] != 0) {
							for (int f: sList) {
								if (seat[nx][ny] == f) {
									temp++;
									break;
								}
							}
						}
					}
					if (temp >= adjStudent) {
						if (temp > adjStudent) {
							seatList.clear();
							adjStudent = temp;
						}
						seatList.add(new int[] {i, j});
					}
				}
			}
			// 가능한 케이스가 1개인 경우
			if (seatList.size() == 1) {
				seat[seatList.get(0)[0]][seatList.get(0)[1]] = student;
			// 가능한 케이스가 여러 개인 경우
			} else {
				int empty = -1;
				int x = -1; int y = -1;
				for (int[] cdnt: seatList) {
					int temp = 0;
					for (int i = 0; i < 4; i++) {
						int nx = cdnt[0] + dx[i];
						int ny = cdnt[1] + dy[i];
						if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
							if (seat[nx][ny] == 0) temp++;
						}
					}
					if (temp > empty) {
						x = cdnt[0]; y = cdnt[1];
						empty = temp;
					}
				}
				seat[x][y] = student;
			}
		}
		
		// 점수 계산
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int score = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && seat[nx][ny] != 0) {
						for (int f: fList.get(seat[i][j])) {
							if (seat[nx][ny] == f) {
								score++;
								break;
							}
						}
					}
				}
				if (score != 0) res += Math.pow(10, score-1);
			}
		}
		
		System.out.println(res);
	}

}