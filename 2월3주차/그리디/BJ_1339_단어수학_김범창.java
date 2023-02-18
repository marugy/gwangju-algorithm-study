package study02;

import java.io.*;
import java.util.*;

public class BJ_1339_단어수학_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] word = new String[N];
		for(int i=0; i<N; i++) {
			word[i] = br.readLine();
		}
		
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			char[] alphabet = word[i].toCharArray();
			
			// 각 알파벳마다 자릿수를 곱한 합 구하기
			for(int j=alphabet.length-1; j>=0; j--) {
				if(map.get(alphabet[j]) == null) {
					map.put(alphabet[j], (int)Math.pow(10, alphabet.length-j-1));
				}else {
					map.put(alphabet[j], map.get(alphabet[j]) + (int)Math.pow(10, alphabet.length-j-1));
				}
			}
		}
		
		// HashMap의 value값을 배열에 저장
		Integer[] values = new Integer[map.size()];
		int index=0;
		for(Character key : map.keySet()) {
			values[index++] = map.get(key);
		}
		
		// 저장된 value값을 내림차순으로 정렬
		Arrays.sort(values, Comparator.reverseOrder());
		
		int result = 0;
		
		// 알파벳의 합이 가장 큰 순서대로 가중치를 곱한 합 구하기
		int weight = 9;
		for(int i=0; i<values.length; i++) {
			result += values[i] * weight--;
		}
		
		System.out.println(result);
		
		br.close();
	}
}