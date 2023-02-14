package com.ssafy.Feb18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1026_보물_박은규 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		Integer[] A = new Integer[N];
		Integer[] B = new Integer[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		Arrays.sort(B, Collections.reverseOrder());
		
		int nSum = 0;
		for (int i = 0; i < N; i++) {
			nSum += A[i] * B[i];
		}
		
		System.out.println(nSum);
	}

}
