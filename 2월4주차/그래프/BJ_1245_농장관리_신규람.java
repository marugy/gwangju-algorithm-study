package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1245_농장관리 {

	static int N;
	static int M;
	static int[][]mountain;
	static int top;
	static boolean[][]visited;
	static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
	static boolean isTop;
	
	public static void countMountain(int i, int j) {
		for(int d=0;d<8;d++) {
			int xx = i + dir[d][0];
			int yy = j + dir[d][1];
			//범위안에 없으면 생략
			if(!(0 <= xx && xx < N && 0 <= yy && yy<M)) continue;
			
			//나보다 높은 곳이 있으면 꼭대기가 아님
			if(mountain[i][j]< mountain[xx][yy]) {
				isTop=false;
				continue;
			}
			
			//이미 방문한 곳이거나 다른 값이면 넘어가
			if(visited[xx][yy] || mountain[i][j]!=mountain[xx][yy]) continue;
			
			//같은 값이면 true로 만들고 재귀
			visited[xx][yy]=true;
			countMountain(xx,yy);
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//값 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mountain = new int[N][M];
		visited = new boolean[N][M];
		top=0;
		
		//산 정보 입력받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				mountain[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//하나씩 꼭대기인지 검사
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j]==false) {
					isTop = true;
					countMountain(i,j);
					if(isTop)
						top++;
				}
			}
		}
		System.out.println(top);
	}
}
