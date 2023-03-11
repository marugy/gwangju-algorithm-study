package bj;

import static java.lang.Integer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bJ_21610_마법사상어와비바라기 {

	static int N;
	static int M;
	static int[][]map;
	static boolean[][]isRainy;
	static Queue<Pos> cloud = new ArrayDeque<>();
	static int[][] dir = {{-1,-1}, {-1,1}, {1,-1},{1,1}};//대각선
	
	static class Pos{	//위치 정보를 담을 class
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static void play(int d, int s) {
		//구름 이동(이동 후 비 1)
		moveCloud(d,s);
		//물복사
		copy();
		//구름 칸을 제외하고 물의양이 2 이상인 칸에 구름 생기기(생성 시 2만큼 줄어듦)
		makeCloud();
	}
	//해당 방향으로 구름 이동 시키고 비 내리기, 이후 물 복사
	static void moveCloud(int d, int s) {
		while(!cloud.isEmpty()) {
			Pos clo = cloud.poll();
			int xx = clo.x;
			int yy = clo.y;		
//			isRainy[xx][yy]=false;
			if(d==1) {
				yy = yy-s;
				if(yy<0) {
					yy = N+yy;
				}
			}else if(d==2) {
				xx = xx-s;
				if(xx<0) {
					xx = N+xx;
				}
				yy = yy-s;
				if(yy<0) {
					yy = N+yy;
				}
			}else if(d==3) {
				xx = xx-s;
				if(xx<0) {
					xx = N+xx;
				}
			}else if(d==4) {
				xx = xx-s;
				if(xx<0) {
					xx = N+xx;
				}
				yy = yy+s;
				if(yy>N-1) {
					yy = yy-N;
				}
			}else if(d==5) {
				yy = yy+s;
				if(yy>N-1) {
					yy = yy-N;
				}
			}else if(d==6) {
				xx = xx+s;
				if(xx>N-1) {
					xx = xx-N;
				}
				yy = yy+s;
				if(yy>N-1) {
					yy = yy-N;
				}
			}else if(d==7) {
				xx = xx+s;
				if(xx>N-1) {
					xx = xx-N;
				}
			}else if(d==8) {
				xx = xx+s;
				if(xx>N-1) {
					xx = xx-N;
				}
				yy = yy-s;
				if(yy<0) {
					yy= N+yy;
				}
			}
			map[xx][yy]++;
			isRainy[xx][yy]=true;
		}
	}
	//대각선 물 복사
	static void copy() {
		for(int i=0;i<N;i++) {
			for (int j = 0; j < N; j++) {
				if(isRainy[i][j]) {
					int cnt = 0;
					for(int d=0;d<4;d++) { //대각선에 물이 있는만큼 증가
						int xx = i + dir[d][0];
						int yy = j + dir[d][1];
						if(0<=xx && xx<N && 0<=yy && yy<N && map[xx][yy]>0)
							cnt++;
					}
					map[i][j]+=cnt;
				}
			}
		}
		
	}
	//구름 만들기
	static void makeCloud() {
		for(int i=0; i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!isRainy[i][j] && map[i][j]>=2) {
					cloud.add(new Pos(i,j));
					map[i][j]-=2;
				}
				if(isRainy[i][j])
					isRainy[i][j]=false;
			}
		}
	}
	// 비 수 세기
	static int countRainy() {
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]>0)
					cnt+=map[i][j];
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		isRainy = new boolean[N][N];
		
		cloud.add(new Pos(N-1,0));
		cloud.add(new Pos(N-1,1));
		cloud.add(new Pos(N-2,0));
		cloud.add(new Pos(N-2,1));
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=parseInt(st.nextToken());
			}
		}
		// 이동 정보 받고 수행
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = parseInt(st.nextToken());
			int s = parseInt(st.nextToken());
			play(d,s%N);
		}
		// 결과 출력
		System.out.println(countRainy());
	}
}