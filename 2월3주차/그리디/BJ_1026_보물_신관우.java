import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1026 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] brr = new int[n];
		int[] arr = new int[n];
		boolean[] visit = new boolean[n];
		StringTokenizer sta = new StringTokenizer(br.readLine());
		StringTokenizer stb = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(sta.nextToken());
			brr[i] = Integer.parseInt(stb.nextToken());
		}

		Arrays.sort(arr);
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int index = 0;
		for (int i = 0; i < n; i++) {
			for(int k=0; k<n; k++) {
				if(!visit[k]) {
					index = k;
					break;
				}
			}
			for (int j = 0; j < n; j++) {
				if (!visit[j]) {
					if (max < arr[i] * brr[j]) {
						index = j;
						max = arr[i] * brr[j];
					}
					if (max == arr[i] * brr[j] && brr[index] < brr[j]) {
						index = j;
					}
				}
			}
			visit[index] = true;
			sum += max;
			max = 0;
		}

		System.out.println(sum);
	}

}
