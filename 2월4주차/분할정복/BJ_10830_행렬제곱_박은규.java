package com.ssafy.Feb25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10830_행렬제곱_박은규 {
	
	static int N;
	static long B;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		// 행렬의 크기와 거듭제곱 값
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		// 행렬 및 결과 행렬
		long[][] arr = new long[N][N];
		long[][] res = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		// 행렬 거듭제곱 수행
		res = getExp(arr, B);
		// 결과 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	// 이차원 행렬의 거듭제곱
	private static long[][] getExp(long[][] a, long n) {
		// 1제곱인 경우 그대로 출력(나머지 연산)
		if (n == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					a[i][j] %= 1000;
				}
			}
			return a;
		// 2 이상 제곱인 경우
		// 절반 제곱한 결과의 곱
		} else {
			long[][] temp = getExp(a, n/2);
			if (n % 2 == 0) return getMul(temp, temp);
			else return getMul(getMul(temp, temp), a);
		}
	}
	
	// 두 이차원 행렬의 곱
	private static long[][] getMul(long[][] a, long[][] b) {
		long[][] res = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					res[i][j] += (a[i][k] * b[k][j]) % 1000;
				}
				res[i][j] %= 1000;
			}
		}
		return res;
	}

}
