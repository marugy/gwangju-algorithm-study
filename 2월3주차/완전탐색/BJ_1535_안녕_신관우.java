import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1535 {
	static int n = 0;
	static int max = Integer.MIN_VALUE;
	static int[] happy;
	static int[] health;

	public static void check(int start, int sump, int sumh) {
		if (start == n) {
			if (sumh < 100) {
				if (max < sump)
					max = sump;
			}
			return;
		}

		check(start + 1, sump, sumh);
		check(start + 1, sump + happy[start], sumh + health[start]);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		health = new int[n];
		happy = new int[n];

		StringTokenizer sta = new StringTokenizer(br.readLine());
		StringTokenizer stb = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			health[i] = Integer.parseInt(sta.nextToken());
			happy[i] = Integer.parseInt(stb.nextToken());
		}

		check(0, 0, 0);
		
		System.out.println(max);

	}

}
