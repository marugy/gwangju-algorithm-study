package study01;

import java.io.*;

public class BJ_10974_모든순열_김범창 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 순열의 값을 순차적으로 저장하는 공간을 배열로 구현
		int[] arr = new int[n];
		
		// 중복된 값을 출력하지 않기 위해 저장한 값을 기록
		boolean[] visited = new boolean[n];
		
		printPermutation(arr, visited, 0);
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static void printPermutation(int[] arr, boolean[] visited, int index) {
		// 순열의 값을 모두 저장한 경우
		if(index >= arr.length) {
			// 저장된 순열을 순차적으로 하나씩 출력한다.
			for(int i=0; i<arr.length; i++) {
				sb.append(arr[i]+1).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		// 모든 값 중에
		for(int i=0; i<arr.length; i++) {
			// 이전에 방문하지 않은 값에 값에 방문한다.
			if(!visited[i]) {
				// 방문기록
				visited[i] = true;
				
				// 해당 값을 순열의 index번째로 저장한다.
				arr[index] = i;
				
				// index+1번째 값을 순열에 저장하기 위해 재귀 호출
				printPermutation(arr, visited, index+1);
				
				// 다른 경우를 구하기 위해 방문기록을 제거
				visited[i] = false;
			}
		}
	}
}
