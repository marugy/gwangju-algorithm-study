import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1208_sub {

	static int[] arr;
	static long count = 0;
	static int target;
	static ArrayList<Integer> left = new ArrayList<>();
	static ArrayList<Integer> right = new ArrayList<>();
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void counts(int sum, int start, int end, ArrayList<Integer> list, boolean flag) {
		if (start == end) {
			if (flag == false) {
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			} else {
				count += map.getOrDefault(target - sum, 0);
			}
			return;
		}
		counts(sum + arr[start], start+1, end, list, flag);
		counts(sum, start+1, end, list, flag);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		counts(0, 0, n / 2, left, false);
		counts(0, n / 2, n, right, true);

		if (target == 0)
			count--;

		System.out.println(count);
	}

}
