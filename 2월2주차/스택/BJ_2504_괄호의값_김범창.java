package study01;

import java.io.*;
import java.util.*;

public class BJ_2504_괄호의값_김범창 {
	static Stack<Character> stack = new Stack<>();
	static HashMap<Character, Integer> map = new HashMap<>();
	static char[] str;
	static int index = -1;
	static boolean isCorrect = true;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map.put('(', 2);
		map.put(')', -2);
		map.put('[', 3);
		map.put(']', -3);
		
		str = br.readLine().toCharArray();
		
		System.out.println(getValue());
		
		br.close();
	}
	
	static int getValue() {
		int subValue = 0;
		
		while(index < str.length-1) {
			index++;
			// 여는 괄호일 경우 스택에 저장
			if(map.get(str[index]) > 0) {
				stack.add(str[index]);
				// 괄호 계산
				subValue += map.get(str[index]) * getValue();
			}else {
				if(!stack.isEmpty() && map.get(stack.pop())+map.get(str[index]) == 0) {
					return subValue==0?1:subValue;
				}else {
					// 1. 닫는 괄호가 나왔는데 스택에 값이 없음(여는 괄호 부족)
					// 2. 스택에 저장된 괄호와 현재 괄호의 짝이 맞지 않음
					isCorrect = false;
					return 0;
				}
			}
		}
		
		// 닫는 괄호가 부족한 경우
		if(!stack.isEmpty()) {
			isCorrect = false;
		}
		
		return isCorrect?subValue:0;
	}
}
