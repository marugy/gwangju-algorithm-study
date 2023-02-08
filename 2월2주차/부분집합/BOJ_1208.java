import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1208 {

	static int[] arr;
	static long count = 0;
	static int target;
	static ArrayList<Integer> left = new ArrayList<>();
	static ArrayList<Integer> right = new ArrayList<>();


	public static void counts(int sum, int start, int end, ArrayList<Integer> list) {
		if (start == end) {
			list.add(sum);
			return;
		}
		counts(sum + arr[start], start+1, end, list);
		counts(sum, start+1, end, list);
	}
	
	
	public static void findTarget() {
		int L = 0;
		int R = right.size()-1;
		while(L < left.size() && R >=0) {
			int val_l = left.get(L);
			int val_r = right.get(R);
			
			if(val_l + val_r == target) {
				long count_l = 0;
				long count_r = 0;
				
				while(L < left.size() && val_l == left.get(L)) {
					count_l++;
					L++;
				}
				while(R >= 0 && val_r == right.get(R)) {
					count_r++;
					R--;
				}
				count += count_r * count_l;
			}
			if(val_l + val_r<target) {
				L++;
			}
			if(val_l + val_r >target) {
				R--;
			}
		}
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

		counts(0, 0, n/2, left);
		counts(0, n/2, n, right);
		
		Collections.sort(left);
		Collections.sort(right);
		
		findTarget();
		
		if(target==0) count--;
		
		System.out.println(count);
	}

}
