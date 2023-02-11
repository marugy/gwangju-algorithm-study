package com.ssafy.study.Feb12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_2504_괄호의값_박은규 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		Deque<Character> deq = new ArrayDeque<>();
		
		char c;
		int pValue = 0;
		int lp = 0, lb = 0;
		boolean isOpen = true,isValid = true;
		
		for (int i = 0; i < s.length() && isValid; i++) {
			c = s.charAt(i);
			switch (c) {
			case '(':
				deq.offer(c);
				lp += 1;
				isOpen = true;
				break;
			case '[':
				deq.offer(c);
				lb += 1;
				isOpen = true;
				break;
			case ')':
				if (deq.size() == 0 || deq.pollLast() != '(') {
					isValid = false;
					break;
				}
				
				if (isOpen) {
					pValue += Math.pow(2, lp) * Math.pow(3, lb);
				}
				isOpen = false;
				lp -= 1;
				break;
			case ']':
				if (deq.size() == 0 || deq.pollLast() != '[') {
					isValid = false;
					break;
				}
				if (isOpen) {
					pValue += Math.pow(2, lp) * Math.pow(3, lb);	
				}
				isOpen = false;
				lb -= 1;
				break;
			}
		}
		
		if (!isValid || !deq.isEmpty()) System.out.println("0");
		else System.out.println(pValue);
	}

}
