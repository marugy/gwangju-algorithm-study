package study01;

import java.io.*;
import java.util.*;

public class SW_4008_숫자만들기_김범창 {
	static long max;
	static long min;
	static int[] operaters;
	static int[] numbers;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=t; tc++) {
			max = -100000000;
			min = 100000000;
			
			int N = Integer.parseInt(br.readLine());
			int[] opCount = new int[4];
			operaters = new int[N-1];
			numbers = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				opCount[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			getPermutation(opCount, 0);
			
			sb.append("#").append(tc).append(" ").append(max - min).append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static void getPermutation(int[] opCount, int depth) {
		if(depth == operaters.length) {
			long value = calculation();
			if(max < value) {
				max = value;
			}
			
			if(min > value) {
				min = value;
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(opCount[i] > 0) {
				opCount[i]--;
				operaters[depth] = i;
				getPermutation(opCount, depth+1);
				opCount[i]++;
			}
		}
	}
	
	static long calculation() {
		long value = numbers[0];
		for(int i=0; i<operaters.length; i++) {
			switch (operaters[i]) {
			case 0:
				value += numbers[i+1];
				break;
			case 1:
				value -= numbers[i+1];
				break;
			case 2:
				value *= numbers[i+1];
				break;
			case 3:
				value /= numbers[i+1];
				break;
			}
		}
		
		return value;
	}
}
