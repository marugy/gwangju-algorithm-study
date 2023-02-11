import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14713 {
	static boolean flag;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<String>[] que = new Queue[n];
		for (int i = 0; i < n; i++) {
			que[i] = new LinkedList<String>();
			StringTokenizer st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				que[i].add(st.nextToken());
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			flag = true;
			String s = st.nextToken();
			for (int i = 0; i < n; i++) {
				if (!(que[i].isEmpty()) && que[i].peek().equals(s)) {
					que[i].poll();
					flag = false;
					break;
				}
			}
			if (flag)
				break;
		}
		for (int i = 0; i < n; i++) {
			if (!que[i].isEmpty()) {
				flag = true;
				break;
			}
		}
		if (!flag)
			System.out.println("Possible");
		else
			System.out.println("Impossible");
	}
}
