package com.ssafy.Feb25;

public class PM_42897_도둑질_박은규 {

	public int solution(int[] money) {
		// 돈의 개수 = 집의 개수
		int N = money.length;

		// 0번 집을 반드시 포함하는 경우에 대해 최대 VALUE 계산
		int[] dp0 = new int[N];
		// (N-1)번 집을 반드시 포함하는 경우에 대해 최대 VALUE 계산
		int[] dpN_1 = new int[N];
		
		// 초깃값 지정
		dp0[0] = money[0]; dp0[1] = Integer.max(money[0], money[1]);
		dpN_1[0] = 0; dpN_1[1] = money[1];
		
		// case 1: i번째 집을 훔치는 경우
		// case 2: (i-1)번째 집을 훔치는 경우
		// case 1과 case 2의 최댓값으로 지정
		for (int i = 2; i < N-1; i++) {
			dp0[i] = Integer.max(dp0[i-2]+money[i], dp0[i-1]);
			dpN_1[i] = Integer.max(dpN_1[i-2]+money[i], dpN_1[i-1]);
		}
		// 0번 집을 반드시 포함하는 경우: 마지막 집 추가 계산 X
		dp0[N-1] = dp0[N-2];
		// 마지막 집을 반드시 포함하는 경우: 마지막 집까지 최댓값 비교 및 계산
		dpN_1[N-1] = Integer.max(dpN_1[N-3] + money[N-1], dpN_1[N-2]);
		
		// 결과 반환
		return Integer.max(dp0[N-1], dpN_1[N-1]);
	}

}
