import java.awt.Point;
import java.io.*;
import java.util.*;

public class BJ_21610_마법사상어와비바라기_김범창 {
	
	private static int N, M;
	private static int[][] field;
	private static boolean[][] check;
	private static ArrayList<Point> cloud = new ArrayList<>();
	private static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	private static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		field = new int[N][N];
		check = new boolean[N][N];
		// 비바라기
		cloud.add(new Point(0, N-1));
		cloud.add(new Point(1, N-1));
		cloud.add(new Point(0, N-2));
		cloud.add(new Point(1, N-2));
		
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				field[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			order(d, s);
		}
		
		// 바구니에 들어있는 물의 합 구하기
		int ans = 0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				ans += field[y][x];
			}
		}
		
		System.out.println(ans);
		
		br.close();
	}
	
	// 명령
	private static void order(int d, int s) {
		// 구름 이동
		move(d, s);
		
		// 비
		rain();
		
		// 물복사버그
		addWater();
		
		// 구름 생성
		createCloud();
	}
	
	// 구름 이동
	private static void move(int d, int s) {
		for(int i=0; i<cloud.size(); i++) {
			Point p = cloud.get(i);
			
			int nx = (N*25 + p.x + dx[d]*s)%N;
			int ny = (N*25 + p.y + dy[d]*s)%N;
			
			cloud.set(i, new Point(nx, ny));
		}
	}
	
	// 비
	private static void rain() {
		for(Point p : cloud) {
			field[p.y][p.x]++;
			check[p.y][p.x] = true;
		}
	}
	
	// 물복사버그
	private static void addWater() {
		int[][] temp = new int[N][N];
		
		int[] dx = {1, 1, -1, -1};
		int[] dy = {1, -1, 1, -1};
		
		for(Point p : cloud) {
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < N && field[ny][nx] > 0) {
					temp[p.y][p.x]++;
				}
			}
		}
		
		cloud.clear();
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				field[y][x] += temp[y][x];
			}
		}
	}
	
	// 구름 생성
	private static void createCloud() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(!check[y][x]) {
					if(field[y][x] >= 2) {
						field[y][x] -= 2;
						cloud.add(new Point(x, y));
					}
				}else {
					check[y][x] = false;
				}
			}
		}
	}
}
