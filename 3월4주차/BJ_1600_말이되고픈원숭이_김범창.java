package daily0329;

import java.io.*;
import java.util.*;

public class BJ_1600_말이되고픈원숭이_김범창 {
	
	static class Node {
		int x, y, k, count;
		
		Node(int x, int y, int k, int count) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.count = count;
		}
	}
	
	static int[] mx = {1, -1, 0, 0};
	static int[] my = {0, 0, 1, -1};
	static int[] hx = {2, 2, -2, -2, 1, -1, 1, -1};
	static int[] hy = {1, -1, 1, -1, 2, 2, -2, -2};
	static int W;
	static int H;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int[][] field = new int[H][W];
		int[][] visited = new int[H][W];
		int result = -1;
		
		for(int y=0; y<H; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<W; x++) {
				field[y][x] = Integer.parseInt(st.nextToken());
				visited[y][x] = -1;
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, K, 0));
		
		// 해당 위치를 말 이동 횟수를 K번 가지고 방문
		// 이후에 해당 위치를 K번보다 적거나 같은 값으로 오는 경우, 중복된 탐색만 수행하게 되므로 제외한다.
		visited[0][0] = K;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			// 도착
			if(node.x == W-1 && node.y == H-1 && field[node.y][node.x] == 0) {
				result = node.count;
				break;
			}
			
			// 원숭이 이동
			for(int i=0; i<4; i++) {
				int nx = node.x + mx[i];
				int ny = node.y + my[i];
				
				if(isInArray(nx, ny) && visited[ny][nx] < node.k && field[node.y][node.x] == 0) {
					queue.add(new Node(nx, ny, node.k, node.count+1));
					visited[ny][nx] = node.k;
				}
			}
			
			// 말 이동
			if(node.k > 0) {
				for(int i=0; i<8; i++) {
					int nx = node.x + hx[i];
					int ny = node.y + hy[i];
					
					if(isInArray(nx, ny) && visited[ny][nx] < node.k-1 && field[node.y][node.x] == 0) {
						queue.add(new Node(nx, ny, node.k-1, node.count+1));
						visited[ny][nx] = node.k-1;
					}
				}
			}
		}
		
		System.out.println(result);
		
		br.close();
	}
	
	public static boolean isInArray(int x, int y) {
		if(0 <= x && x < W && 0 <= y && y < H) {
			return true;
		} else {
			return false;
		}
	}
}
