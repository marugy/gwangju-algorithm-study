package Gold;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class BJ_15685_드래곤커브_김범창 {
	
	private static boolean[][] map = new boolean[101][101];;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Point>[] list = new ArrayList[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<Point>();
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			// 드레곤 커브를 리스트로 구현
			list[i].add(new Point(x, y));
			list[i].add(new Point(x+dx[d], y+dy[d]));
			
			// g세대 드래곤 커브 만들기
			make(list[i], g);
		}
		
		// N개의 드래곤 커브를 map에 그리기
		for(int i=0; i<N; i++) {
			draw(list[i]);
		}
		
		//printMap();
		//printList(list[0]);
		
		// 1x1 정사각형 구하기
		System.out.println(getSquare());
		
		br.close();
	}
	
	// 만들기
	private static void make(ArrayList<Point> list, int g) {
		if(g == 0) {
			return;
		}
		
		// 끝 좌표
		int size = list.size() - 1;
		Point last = list.get(size);
		
		// 리스트를 끝에서부터 순차적으로 접근
		for(int i=size; i>=1; i--) {
			// 이전 방향
			int prev = getDirection(list.get(i), list.get(i-1));
			
			// 다음 방향
			int next = (prev + 1)%4;
			
			// 끝 좌표에 다음 좌표 추가
			list.add(new Point(last.x + dx[next], last.y + dy[next]));
			
			// 끝 좌표 갱신
			last = list.get(list.size()-1);
		}
		
		make(list, g-1);
	}
	
	// 방향 구하기
	private static int getDirection(Point curr, Point next) {
		int nx = curr.x - next.x;
		int ny = curr.y - next.y;
		
		if(nx == 1 && ny == 0) {
			return 0;
		}else if(nx == 0 && ny == -1) {
			return 1;
		}else if(nx == -1 && ny == 0) {
			return 2;
		}else if(nx == 0 && ny == 1) {
			return 3;
		}
		
		return -1;
	}
	
	// 맵에 드래곤 커브 그리기
	private static void draw(ArrayList<Point> list) {
		// 현재 좌표
		Point curr = list.get(0);
		// 다음 좌표
		Point next = null;
		
		for(int i=1; i<list.size(); i++) {
			next = list.get(i);
			
			// x축으로 이동이 있을 경우
			if(Math.abs(curr.x - next.x) > 0) {
				int s = Math.min(curr.x, next.x);
				int e = Math.max(curr.x, next.x);
				int y = curr.y;
				
				for(int x=s; x<=e; x++) {
					if(0 <= x && x <= 100 && 0 <= y && y <= 100) {
						map[y][x] = true;
					}
				}
			}
			// y축으로 이동이 있을 경우
			else {
				int s = Math.min(curr.y, next.y);
				int e = Math.max(curr.y, next.y);
				int x = curr.x;
				
				for(int y=s; y<=e; y++) {
					if(0 <= x && x <= 100 && 0 <= y && y <= 100) {
						map[y][x] = true;
					}
				}
			}
			
			// 현재좌표 갱신
			curr = next;
		}
	}
	
	// 정사각형 개수 구하기
	private static int getSquare() {
		int count = 0;
		
		for(int y=0; y<100; y++) {
			for(int x=0; x<100; x++) {
				if(map[y][x]) {
					int temp = 0;
					
					temp += map[y+1][x]?1:0;
					temp += map[y][x+1]?1:0;
					temp += map[y+1][x+1]?1:0;
					
					if(temp == 3) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	// map 출력
	private static void printMap() {
		for(int y=0; y<=20; y++) {
			for(int x=0; x<=20; x++) {
				int val = map[y][x]?1:0;
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}
	
	// list 출력
	private static void printList(ArrayList<Point> list) {
		for(Point p : list) {
			System.out.println(p.x + " " + p.y);
		}
	}
}
