import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {
	static int x, y, r, c, ni, nj;
	static int max = Integer.MIN_VALUE;
	static char[][] arr;
	static boolean[] list = new boolean[26];

	public static void move(int a, int b, int count) {
		if (a >= 0 && b >= 0 && a < r && b < c && !list[arr[a][b]-'A']) {
			list[arr[a][b]-'A'] = true;
			count += 1;
			if (max < count)
				max = count;
			move(a - 1, b, count);
			move(a + 1, b, count);
			move(a, b - 1, count);
			move(a, b + 1, count);
			list[arr[a][b]-'A'] = false;
			return;
		} else
			return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		x = y = 0;
		move(x, y, 0);
		System.out.println(max);

	}
}
