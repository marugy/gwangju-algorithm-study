package com.ssafy.Mar11;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_21610_마법사상어와비바라기_박은규 {

	static int N, M; 
	static int[][] field, _field;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		field = new int[N][N];
		_field = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				_field[i][j] = field[i][j] = parseInt(st.nextToken());
			}
		}
		
		List<int[]> moves = new ArrayList<>();
		List<int[]> clouds = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int d = parseInt(st.nextToken());
			int s = parseInt(st.nextToken());
			moves.add(new int[] {d-1, s});
		}
		
		clouds.add(new int[] {N-2, 0});
		clouds.add(new int[] {N-2, 1});
		clouds.add(new int[] {N-1, 0});
		clouds.add(new int[] {N-1, 1});
		
		for (int i = 0; i < M; i++) {
			// 구름 이동 + 비 내리기
			int d = moves.get(i)[0];
			int s = moves.get(i)[1];
			int nSize = clouds.size();
			for (int j = 0; j < nSize; j++) {
				int[] pos = clouds.get(0);
				clouds.remove(0);
				int nx = pos[0] + dx[d] * s;
				int ny = pos[1] + dy[d] * s;
				nx %= N; ny %= N;
				if (nx < 0) nx += N;
				if (ny < 0) ny += N;
				visited[nx][ny] = true;
				field[nx][ny]++; _field[nx][ny]++;
				clouds.add(new int[] {nx, ny});
			}
			
			// 물복사버그 조지기
			for (int j = 0; j < clouds.size(); j++) {
				int[] pos = clouds.get(j);
				int temp = 0;
				for (int k = 0; k < 4; k++) {
					int nx = pos[0] + dx[2*k+1];
					int ny = pos[1] + dy[2*k+1];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && field[nx][ny] != 0) temp++;
				}
				_field[pos[0]][pos[1]] += temp;
			}
			
			clouds.clear();
			// 구름 새 위치에 생성하기(2 이상) + 2씩 줄이기
			for (int j = 0; j < N; j++) {
 				for (int k = 0; k < N; k++) {
 					if (!visited[j][k] &&_field[j][k] >= 2) {
 						_field[j][k] -= 2;
 						clouds.add(new int[] {j, k});
 						visited[j][k] = true;
 					}
 					if (visited[j][k]) visited[j][k] = false;
 				}
			}
			
			for (int j = 0; j < N; j++) {
				field[j] = Arrays.copyOf(_field[j], N);
			}
			
		}
		
		System.out.println(getWaterSum());
	}
	
	private static int getWaterSum() {
		
		int nSum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nSum += field[i][j];
			}
		}
		
		return nSum;
	}

}
