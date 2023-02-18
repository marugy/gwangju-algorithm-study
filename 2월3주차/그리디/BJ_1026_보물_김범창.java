package study02;

import java.io.*;
import java.util.*;

public class BJ_1026_보물_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Integer[] arr1 = new Integer[N];
		Integer[] arr2 = new Integer[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr1);
		Arrays.sort(arr2, Collections.reverseOrder());
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum += arr1[i] * arr2[i];
		}
		
		System.out.println(sum);
		
		br.close();
	}
}
