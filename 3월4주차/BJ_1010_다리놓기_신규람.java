package bj;

import java.util.Scanner;

public class BJ_1010_다리놓기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int test_case = 0; test_case < t; test_case++) {
			int n = sc.nextInt();
			int m = sc.nextInt();

			int answer = 1;
			for(int i=0; i<n; i++) {
				answer*=(m-i);
				answer/=(i+1);
			}
			System.out.println(answer);
		}
	}
}
