package com.ssafy.study.Feb18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1535_안녕_박은규 {
	
	static int N;
	static ArrayList<Integer> hList, jList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		hList = new ArrayList<>();
		jList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			hList.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			jList.add(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getMaxJoy());
	}
	
	private static int getMaxJoy() {
		int nMax = 0;
		for (int i = 1; i < (1<<N); i++) {
			int hp = 100, joy = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1<<j)) > 0) {
					hp -= hList.get(j);
					joy += jList.get(j);
				}
			}
			if (hp <= 0) continue;
			nMax = joy > nMax ? joy : nMax;
		}
		return nMax;
	}

}
