package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_½Å°ü¿ì {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int west = Integer.parseInt(st.nextToken());
			int east = Integer.parseInt(st.nextToken());
			int bridge[][] = new int[east + 1][west + 1];

			bridge[0][0] = 1;

			for (int a = 1; a <= east; a++) {
				for (int b = 0; b <= west; b++) {
					if (b == 0)
						bridge[a][b] = 1;
					if (bridge[a][b] == 0) {
						bridge[a][b] += bridge[a - 1][b - 1] + bridge[a - 1][b];
					}
				}
			}
			sb.append(bridge[east][west] + "\n");
		}
		System.out.println(sb);
	}
}
