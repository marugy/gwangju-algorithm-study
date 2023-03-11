import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기 {
	static int[][] direction = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int N, M, basket[][], nx, ny, total;
	static boolean visit[][];
	static Deque<cloud> deq, clouds;

	public static class cloud {
		int x, y;

		public cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void move(int x, int y, int d, int s) {
		nx = (((x + direction[d][1] * s) % N) + N) % N;
		ny = (((y + direction[d][0] * s) % N) + N) % N;
		visit[ny][nx] = true;
		basket[ny][nx] += 1;
	}

	public static void water(int x, int y) {
		if (x - 1 >= 0 && x - 1 < N && y - 1 >= 0 && y - 1 < N && basket[y - 1][x - 1] > 0) {
			basket[y][x] += 1;
		}
		if (x - 1 >= 0 && x - 1 < N && y + 1 >= 0 && y + 1 < N && basket[y + 1][x - 1] > 0) {
			basket[y][x] += 1;
		}
		if (x + 1 >= 0 && x + 1 < N && y - 1 >= 0 && y - 1 < N && basket[y - 1][x + 1] > 0) {
			basket[y][x] += 1;
		}
		if (x + 1 >= 0 && x + 1 < N && y + 1 >= 0 && y + 1 < N && basket[y + 1][x + 1] > 0) {
			basket[y][x] += 1;
		}
	}

	public static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j] && basket[i][j] >= 2) {
					deq.addLast(new cloud(j, i));
					basket[i][j] -= 2;
					visit[i][j] = true;
				}
			}
		}
	}

	public static void sumWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				total += basket[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(basket[i]));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		basket = new int[N][N];
		visit = new boolean[N][N];
		deq = new ArrayDeque<>();
		clouds = new ArrayDeque<>();
		int d = 0, s = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				basket[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			for (int a = 0; a < N; a++)
				Arrays.fill(visit[a], false);
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			if (i == 0) {
				move(0, N - 1, d, s);
				clouds.add(new cloud(nx, ny));
				move(1, N - 1, d, s);
				clouds.add(new cloud(nx, ny));
				move(0, N - 2, d, s);
				clouds.add(new cloud(nx, ny));
				move(1, N - 2, d, s);
				clouds.add(new cloud(nx, ny));
				while (!clouds.isEmpty()) {
					cloud temp = clouds.pollFirst();
					water(temp.x, temp.y);
				}
				makeCloud();
			} else {
				int size = deq.size();
				for (int a = 0; a < size; a++) {
					cloud temp = deq.pollFirst();
					move(temp.x, temp.y, d, s);
					clouds.add(new cloud(nx, ny));
				}
				while (!clouds.isEmpty()) {
					cloud temp = clouds.pollFirst();
					water(temp.x, temp.y);
				}
				makeCloud();
			}
		}
		sumWater();
		System.out.println(total);

	}
}