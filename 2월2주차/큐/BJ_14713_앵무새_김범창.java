package study01;

import java.io.*;
import java.util.*;

public class BJ_14713_앵무새_김범창 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Queue<String>> queList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			Queue<String> que = new LinkedList<>();
			
			String[] input = br.readLine().split(" ");
			for(int j=0; j<input.length; j++) {
				que.add(input[j]);
			}
			
			queList.add(que);
		}
		
		String[] result = br.readLine().split(" ");
		
		boolean isPossible = true;
		
		for(int i=0; i<result.length; i++) {
			boolean find = false;
			
			for(int j=0; j<N; j++) {
				if(queList.get(j).isEmpty()) continue;

				if(queList.get(j).peek().equals(result[i])) {
					queList.get(j).poll();
					find = true;
					break;
				}
			}
			
			if(find == false) {
				isPossible = false;
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			if(!queList.get(i).isEmpty()) {
				isPossible = false;
				break;
			}
		}
		
		System.out.println(isPossible?"Possible":"Impossible");
	}
}
