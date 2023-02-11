package com.ssafy.study.Feb12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author 박은규
 *
 */

public class BJ_10974_모든순열_박은규 {
	private static int[] numbers;
	private static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// N 입력
		int N = Integer.parseInt(in.readLine());
		
		// 가능한 순열의 case를 저장하는 배열 선언
		numbers = new int[N];
		
		// 수가 이전에 선택되었는지 여부를 저장하는 배열 선언
		isSelected = new boolean[N+1];
		
		// 가능한 모든 순열의 case를 출력하는 함수 호출
		permutation(N, 0);
				
	}
	
	private static void permutation(int N, int count) {
		// N번째 수까지 선택 완료한 경우
		// numbers 배열에 있는 내용 출력
		if (count == N) {
			for (int i = 0; i < numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
		
		// 가능한 순열을 탐색 중인 경우
		// 작은 수부터 선택 가능한 수 찾기	
		} else {
			for (int i = 1; i <= N; i++) {
				// 앞에서 선택한 수이면 continue
				if (isSelected[i]) {
					continue;
				}
				
				// 아직 선택하지 않은 수이면 배열에 저장
				numbers[count] = i;
				
				// 선택했음을 저장
				isSelected[i] = true;
				
				// 현재 만들고 있는 순열의 다음 자리 수 찾기
				permutation(N, count+1);
				
				// 하나의 순열 완성 이후 선택 여부 판별 배열 초기화
				isSelected[i] = false;
			}
		}
	}
}
