package programmers;

public class PM_42897_도둑질 {
	public static void main(String[] args) {

		int []money = {1,2,3,4};
		//첫번쨰 집 털기
		int[]choice = new int[money.length];
		//털었으니 입력
		choice[0] = money[0];
		//2번째는 앞 집과 비교해서 max값
		choice[1] = Math.max(money[0], money[1]);
		//현재 집을 털지 말지는 앞의 집의 털어온 최대값과 앞앞집과 현재집의 합을 비교해서 결정
		for(int i=2;i < money.length-1; i++) {
			choice[i] = choice[i-1] > choice[i-2]+money[i] ? choice[i-1] : choice[i-2] + money[i];
		}
		//첫번째집 안털기
		int[]noChoice = new int[money.length];
		//안털었으니 0
		noChoice[0]=0;
		//앞집 안털었으니 2번째 집 그대로
		noChoice[1] = money[1];
		//동일
		for(int i=2; i < money.length; i++) {
			noChoice[i] = noChoice[i-1] > noChoice[i-2] + money[i] ? noChoice[i-1] : noChoice[i-2]+money[i];
		}
		//결과 출력
		System.out.println(Math.max(choice[money.length-1],noChoice[money.length-1]));
	}
}
