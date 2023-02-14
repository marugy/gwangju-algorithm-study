package com.ssafy.Feb18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1339_단어수학_박은규 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 단어의 수
		int N = Integer.parseInt(in.readLine());
		
		// 단어
		String[] vocas = new String[N];
		for (int i = 0; i < N; i++) {
			vocas[i] = in.readLine();
		}
		
		// 각 알파벳의 등장 횟수를 저장할 list 선언 및 초기화
		int[] cNum = new int[26];

		for (int i = 0; i < N; i++ ) {
			int len = vocas[i].length();
			for (int j = len-1; j >= 0; j--) {
				cNum[vocas[i].charAt(len-j-1) - 'A'] += (int)Math.pow(10, j);
			}
		}
 		
 		// 각 알파벳의 값 정렬
		Arrays.sort(cNum);
 		
 		// 값이 가장 큰 알파벳에 가장 큰 값 할당, 최댓값 계산
 		int ans = 0, v = 9;
 		for (int i = 25; cNum[i] != 0; i--) {
 			ans += cNum[i]*(v--);
 		}
		
 		System.out.println(ans);
	}
}