package study02;

import java.io.*;

public class BJ_12919_A와B_2_김범창 {
	static String S;
	static String T;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		T = br.readLine();
		
		dfs(T);
		
		System.out.println(result);
		
		br.close();
	}
	
	static void dfs(String str) {
		if(str.length() == S.length()) {
			if(str.equals(S)) {
				result = 1;
			}
			
			return;
		}

		if(str.charAt(str.length()-1) == 'A') {
			dfs(str.substring(0, str.length()-1));
		}
		
		if(str.charAt(0) == 'B') {
			dfs(new StringBuffer(str.substring(1)).reverse().toString());
		}
	}
}
