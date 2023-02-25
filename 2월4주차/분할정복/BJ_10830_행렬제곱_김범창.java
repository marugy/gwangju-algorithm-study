package study03;

import java.io.*;
import java.util.*;

public class BJ_10830_행렬제곱_김범창 {
	static int[][] matrix;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		long B = Long.parseLong(st.nextToken()); 
		matrix = new int[N][N];
		
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			
			for(int x=0; x<N; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] result = bfs(B);
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				sb.append(result[y][x]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static int[][] bfs(long B) {
		if(B <= 0) { // 0번 이하로 곱할 경우 빈 행렬을 반환한다.
			return new int[N][N];
		}else if(B == 1) { // 1번 곱할 경우 단위행렬을 곱해 나머지 연산을 수행한다.
			int[][] temp = new int[N][N];
			for(int y=0; y<N; y++) {
				temp[y][y] = 1;
			}
			
			return getProduct(matrix, temp);
		}else if(B == 2) { // 2번 곱할 경우
			return getProduct(matrix, matrix);
		}else {
			if(B%2 == 0) { // B가 짝수인 경우 A행렬의 B제곱은 (A행렬의 B/2제곱 * A행렬의 B/2제곱)으로 나타낼 수 있다.
				int[][] temp = bfs(B/2);
				return getProduct(temp, temp);
			}else { // B가 홀수인 경우 A행렬의 B제곱은 (A행렬의 B-1제곱 * A행렬)으로 나타낼 수 있다.
				return getProduct(bfs(B-1), matrix);
			}
		}
	}
	
	// 행렬의 크기가 N*N인 두 행렬의 곱을 반환하는 메서드
	static int[][] getProduct(int[][] arr1, int[][] arr2) {
		int[][] result = new int[N][N];
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				for(int index=0; index<N; index++) {
					// 나머지 연산, 모듈러 법칙 사용
					result[y][x] += (arr1[y][index] % 1000)*(arr2[index][x] % 1000) % 1000;
				}
				result[y][x] %= 1000;
			}
		}
		
		return result;
	}
}
