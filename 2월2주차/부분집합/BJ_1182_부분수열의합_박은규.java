package com.ssafy.study.Feb12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1182_부분수열의합_박은규 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] nList = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nList[i] = Integer.parseInt(st.nextToken());
		}
		
		int nRes = 0;
		
		for (int i = 1; i < (1<<N); i++) {
			int tempSum = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1<<j)) != 0) {
					tempSum += nList[j];
				}
			}
			if (tempSum == S) nRes++;
		}
		System.out.println(nRes);
	}
}
