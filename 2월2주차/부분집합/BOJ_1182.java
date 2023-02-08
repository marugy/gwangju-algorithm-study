import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
	static int count = 0;
	static int[] arr;

	public static void dfs(int dep, int limit, int m, int n) {
		if (dep == limit) {
			if (n == m)
				count++;
			return;
		}

		dfs(dep + 1, limit, m, n+arr[dep]);
		dfs(dep + 1, limit, m, n);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, n/2, m, 0);
		dfs(n/2, n, m, 0);

		if (m == 0)
			count--;

		System.out.println(count);

	}
}
