package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BJ_2504_괄호의값_신규람 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character> stack = new Stack<>();
		
		//총합
		int totalSum = 0;
		//곱해질 값들
		int mul = 1;
		//괄호 수만큼 반복
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='(') {//여는괄호 (이면 2곱하기
				stack.push(str.charAt(i));
				mul*=2;
			}
			else if(str.charAt(i)=='[') {//여는괄호 [이면 3 곱하기
				stack.push(str.charAt(i));
				mul*=3;
			}
			else if(str.charAt(i)==')') { //닫는괄호 )일때
				if(stack.isEmpty() || stack.peek()!='(') { //stack이 비어있거나 마지막이 (아니면
					totalSum = 0; //0으로 초기화 후 종료
					break;
				}else if(str.charAt(i-1)=='(') { //(이면 현재 곱한 값 더하기
					totalSum+=mul;
				}
				stack.pop(); //제거
				mul/=2; //(하나 해결했기때문에 2로 나누기
			}
			else if(str.charAt(i)==']') { // 위와 동일
				if(stack.isEmpty() || stack.peek()!='[') {
					totalSum = 0;
					break;
				}else if(str.charAt(i-1)=='[') {
					totalSum+=mul;
				}
				stack.pop();
				mul/=3;
			}
		}
		if(stack.isEmpty()) //완전한 괄호라면 그대로 출력
			System.out.println(totalSum);
		else // 어긋나서 종료해버린 경우 0출력
			System.out.println(0);
	}
}
