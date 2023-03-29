package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이 {
	
	static class Pos{
		int x;
		int y;
		int cnt;
		Pos(int x, int y, int cnt){
			this.x =x;
			this.y=y;
			this.cnt = cnt;
		}
	}
	static int K;
	static int W;
	static int H;
	static int [][]map;
	static int [][]kMap;
	static boolean[][]visited;
 	static int [][]dir= {{-1,0},{1,0},{0,-1},{0,1},{-2,-1},{-2,1},{2,1},{2,-1},{-1,-2},{1,-2},{-1,2},{1,2}};
	
	static void move() {
		Pos pos  = new Pos(0,0,0);
		visited[0][0]=true;
		Queue<Pos> q = new ArrayDeque<>();
		q.add(pos);
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			while(--size>=0) {
				Pos p = q.poll();
				if(p.x==H-1 && p.y==W-1) {
					System.out.println(cnt-1);
					return;
				}
				int lim = 12;
				if(p.cnt==K) lim=4;
				for(int i = 0; i < lim;i++) {
					int xx = p.x + dir[i][0];
					int yy = p.y + dir[i][1];
					int kCnt = p.cnt;
					if(i>3) kCnt++;

					if(!(0<=xx && xx<H && 0<=yy && yy<W)|| map[xx][yy]==1) continue;

					if(!visited[xx][yy] || kMap[xx][yy]>kCnt) {
						visited[xx][yy]=true;
						kMap[xx][yy]=kCnt;
						q.add(new Pos(xx,yy,kCnt));
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		kMap = new int[H][W];
		visited = new boolean[H][W];
		for(int i =0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		move();
	}
}
