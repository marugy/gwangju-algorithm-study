package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1339_단어수학_신규람 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String [] words = new String[N];
		int []num = new int[26]; //자신이 위치한 자릿수의 합을 저장할 배열
		
		//word 입력받기
		for(int i =0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		//words 순회하기
		for(int i=0; i<N;i++) {
			//word의 길이만큼 자릿수 생성
			int digit = (int)Math.pow(10, words[i].length()-1);
			//단어별로 자릿수 만큼의 값을 더해줌
			for(int j=0; j< words[i].length();j++) { //
				num[words[i].charAt(j)-'A'] += digit;
				digit/=10;//자릿수를 10씩 줄여줌
			}
		}
		Arrays.sort(num); //정렬
		int calNum = 9;//곱해줄 수
		int sum = 0;//최종 결과
		for(int i=25;i>=0;i--) {//가장 큰 자릿수를 보유한 수부터 calNum을 곱해줌
			sum+=num[i]*calNum--;
			if(num[i]==0)
				break;
		}
		System.out.println(sum);//결과출력
	}
}
