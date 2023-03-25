import java.awt.Point;
import java.io.*;
import java.util.*;

public class BJ_21608_상어초등학교_김범창 {
	
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static int N;
	private static int[][] field;
	private static ArrayList<ArrayList<Integer>> likes = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		field = new int[N][N];
		
		for(int i=0; i<=N*N; i++) {
			likes.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<4; j++) {
				likes.get(student).add(Integer.parseInt(st.nextToken()));
			}
			
			int maxValue = 0;
			Point maxPos = new Point();
			
			// field를 탐색하면서 자리찾기
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					int value = 0;
					
					if(field[y][x] == 0) {
						value += 1;
						
						for(int d=0; d<4; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							
							if(0 <= nx && nx < N && 0 <= ny && ny < N) {
								for(int like : likes.get(student)) {
									if(field[ny][nx] == like) value += 10;
								}
								if(field[ny][nx] == 0) value += 1;
							}
						}
						
						if(value > maxValue) {
							maxValue = value;
							maxPos = new Point(x, y);
						}
					}
				}
			}
			
			field[maxPos.y][maxPos.x] = student;
		}
		
		System.out.println(getSatisfy());
		
		br.close();
	}
	
	private static int getSatisfy() {
		int satisfy = 0;
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				int count = 0;
				
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(0 <= nx && nx < N && 0 <= ny && ny < N) {
						for(int like : likes.get(field[y][x])) {
							if(field[ny][nx] == like) count++;
						}
					}
				}
				
				switch (count) {
				case 0:
					satisfy += 0;
					break;
				case 1:
					satisfy += 1;
					break;
				case 2:
					satisfy += 10;
					break;
				case 3:
					satisfy += 100;
					break;
				case 4:
					satisfy += 1000;
					break;
				}
			}
		}
		
		return satisfy;
	}
}
