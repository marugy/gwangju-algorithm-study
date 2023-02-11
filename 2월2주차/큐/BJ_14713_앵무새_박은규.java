package com.ssafy.study.Feb12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_14713_앵무새_박은규 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 앵무새의 수
		int N = Integer.parseInt(in.readLine());
		
		// 각 앵무새가 말한 문장
		String[][] stcs = new String[N][100];
		
		// 각 앵무새가 다음으로 읽을 문장의 위치
		int[] idx = new int[N];
		
		// cseteram이 받아 적은 문장
		String[] input = new String[10000];
		
		for (int i = 0; i < N; i++) {
			stcs[i] = in.readLine().split(" ");
		}
		
		input = in.readLine().split(" ");
		
		boolean isValid = false;
		
		// 받아 적은 문장을 앞에서부터 읽음
		for (int i = 0; i < input.length; i++) {
			// 앵무새들 중 다음으로 읽을 단어가 현재 단어와 같은 앵무새 탐색
			for (int j = 0; j < N; j++) {
				// 현재 앵무새의 문장 끝까지 탐색한 경우 skip
				if (idx[j] >= stcs[j].length) {
					continue;
				}
				
				// 맞는 앵무새 찾은 경우 인덱스 1 증가시키고
				// 현재까지 읽은 문장은 valid함을 확인
				if (input[i].equals(stcs[j][idx[j]])) {
					idx[j]++;
					isValid = true;
					continue;
				}
			}
			// 맞는 앵무새를 찾지 못한 경우 invalid sentence
			if (!isValid) {
				System.out.println("Impossible");
				return;
			}
			isValid = false;
		}
		
		// 놓친 말이 있는지 확인
		for (int i = 0; i < N; i++) {
			if (idx[i] < stcs[i].length) {
				System.out.println("Impossible");
				return;
			}
		}
		
		// 문장 끝까지 탐색 완료한 경우 valid sentence
		System.out.println("Possible");
	}
}
