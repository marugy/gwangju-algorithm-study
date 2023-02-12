package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4008_숫자만들기_신규람 {

	static int N;
	static int[]num;
	static int[]operator;
	static int minSum;
	static int maxSum;
	static int sum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc< T; tc++) {
			//연산 최대 최소값
			minSum = Integer.MAX_VALUE;
			maxSum = Integer.MIN_VALUE;
			sum = 0; // 누적 결과
			
			//숫자 갯수
			N = Integer.parseInt(br.readLine());

			num = new int[N]; //숫자 저장
			operator = new int[4]; // +, -, *, / 연산자 수 저장
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4;i++) { //연산자 수 입력
				operator[i]=Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) { //숫자 입력받기
				num[i]=Integer.parseInt(st.nextToken());
			}
			sum+=num[0];//첫번째 숫자 저장하고 시작
			cal(1);//연산 수행
			System.out.println("#"+(tc+1)+" "+(maxSum - minSum));//결과 수행
		}
	}
	
	public static void cal(int count) {
		if(count == N) { //n개 수를 연산했다면 재귀 중단
			if(sum < minSum) { //최솟값 갱신
				minSum = sum;
			}
			if(sum > maxSum) {//최댓값 갱신
				maxSum = sum;
			}
			return;
		}
		
		for(int i=0;i<4;i++) { //연산자 훑기
			if(operator[i]>0) { //연산자 횟수가 남아 있다면
				operator[i]--; //1 감속시키고
				int tmp = sum; //연산 전의 sum값 저장
				//연산자 위치에 따른 연산 수행
				if(i==0) {
					sum+=num[count];
				}else if(i==1) {
					sum-=num[count];
				}else if(i==2) {
					sum*=num[count];
				}else if(i==3) {
					sum/=num[count];
				}
				cal(count+1); //재귀 반복
				sum = tmp; // 연산 전의 수로 복구
				operator[i]++; //연산자 사용 횟수 증가
			}
		}
	}
}
