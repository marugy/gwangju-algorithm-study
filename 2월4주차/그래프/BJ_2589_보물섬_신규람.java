package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2589_보물섬 {
	static int L;
	static int W;
	static char[][] island;
	static boolean[][] visited;
	static int [][]dir = {{1,0},{-1,0},{0,1},{0,-1}};
	static int maxDepth = Integer.MIN_VALUE;

	//좌표를 저장할 class Pos
	public static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void findTreasure(int i, int j) {
		
		//시작 지점
		char start = island[i][j];
		
		//인접 정보를 담을 큐
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(i,j));
		visited[i][j]=true;
		
		//최대 depth를 계산할 변수 count;
		int count = -1;
		
		//큐가 비어있지 않을 경우 반복
		while(!q.isEmpty()) {
			int size = q.size();
			//depth별로 반복
			while(--size>=0) {
				Pos now = q.poll();
				int x = now.x;
				int y = now.y;
				//사방탐색
				for(int d=0;d<4;d++) {
					int xx = x +dir[d][0];
					int yy = y +dir[d][1];
					//아직 방문하지 않은 범위내에 있는 같은 정보의 경우
					if(0<=xx && xx<L && 0<=yy && yy<W && visited[xx][yy]==false && island[xx][yy]==start) {
						visited[xx][yy]=true;
						q.add(new Pos(xx,yy));
					}
				}
			}
			count++;
		}
		//최댓값 갱신
		if(maxDepth<count) maxDepth=count;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//값 입력받기
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		island = new char[L][W];//섬 정보를 담을 배열
		//섬 입력받기
		for(int i=0;i<L;i++) {
			st=new StringTokenizer(br.readLine());
			island[i]=st.nextToken().toCharArray();
		}
		//육지마다 BFS
		for(int i=0;i<L;i++) {
			for(int j=0;j<W;j++) {
				if(island[i][j]=='L') {
					visited= new boolean[L][W];
					findTreasure(i,j);
				}
			}
		}
		//결과출력
		System.out.println(maxDepth);
	}
}
