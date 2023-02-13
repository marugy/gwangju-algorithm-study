package BJ;

import java.util.Scanner;

public class BJ_12919_A와B_2 {

	static String S;
	static String T;
	static StringBuilder sb;//문자열 수정할거
	
	static boolean pass;//통과 여부
	
	public static void findAB() { //T에서 S로 돌아갈 수 있는지 확인
		//S의 길이와 같다면
		if(sb.toString().length() == S.length()) {
			if(sb.toString().equals(S)) { // 동일하다면 pass=true로
				pass =true;
			}
			return;
		}
		//가장 뒤가 A라면 제거하고 재귀
		if(sb.charAt(sb.length()-1)=='A') {
			sb.delete(sb.length()-1, sb.length());
			findAB();
			sb.append('A'); //복구
		}
		if(sb.charAt(0)=='B') { //맨 앞이 B라면
			sb.reverse(); //거꾸로 만들고
			sb.delete(sb.length()-1, sb.length());//재거하고
			findAB();//재귀
			sb.append("B");//복구
			sb.reverse();
		}
	}
	
	public static void main(String[] args) {
		Scanner  sc = new Scanner (System.in);
		
		//입력 받기
		S = sc.nextLine();
		T = sc.nextLine();
		sb = new StringBuilder(T);
		
		//확인 함수 실행
		findAB();
		if(pass) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
}
