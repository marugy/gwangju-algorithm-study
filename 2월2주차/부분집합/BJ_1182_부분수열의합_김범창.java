package study01;

import java.io.*;
import java.util.*;

public class BJ_1182_부분수열의합_김범창 {
	static int count = 0;
	static int[] numbers;
	static int N;
	static int S;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		getSum(0, 0);
		
		System.out.println(S==0?count-1:count);
		
		br.close();
	}
	
	static void getSum(int value, int depth) {
		if(depth == numbers.length) {
			if(value == S) {
				count++;
			}
			return;
		}
		
		getSum(value, depth+1);
		getSum(value+numbers[depth], depth+1);
	}
}
