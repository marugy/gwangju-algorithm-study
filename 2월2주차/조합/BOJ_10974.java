import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_10974 {
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static boolean[] visit;
	
	public static void dfs(int n, int dep) {
		if(n==dep) {
			for(int ar : arr) {
				sb.append(ar).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<n; i++) {
			if(!visit[i]) {
				visit[i] = true;
				arr[dep] = i+1;
				dfs(n, dep+1);
				visit[i] = false;
			}
				
			
		}
		
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		visit = new boolean[n];
		dfs(n, 0);
		
		System.out.println(sb.toString());
	}

}
