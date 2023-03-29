package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10971_외판원순회2 {

	static int N;
	static boolean[]visited;
	static int[][]map;
	static int minCost = Integer.MAX_VALUE;
	static int cost;
	
	static void travel(int start,int now, int cnt) {
		// 모든 여행지를 방문한 경우
		if(cnt==N) {
			//돌아올 수 없는 경우
			if(map[now][start]==0) return;
			//돌아올 수 있는 경우
			cost+=map[now][start];
			if(minCost>cost) { //갱신
				minCost = cost;
			}
			cost-=map[now][start];
			return;
		}
		//다음 여행지 방문하기
		for(int next=0;next<N;next++) {
			if(map[now][next]!=0 && !visited[next]) { //0이 아니고 아직 방문하지 않은 경우
				visited[next]=true;
				cost+=map[now][next];
				travel(start,next,cnt+1); // 다음 방문지로 방문
				cost-=map[now][next];
				visited[next]=false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//N 입력 및 초기화
		N=Integer.parseInt(br.readLine());
		map = new int[N][N];
		//여행 비용 입력받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 방문지 방문하기
		for(int i=0;i<N;i++) {
			visited = new boolean[N];
			visited[i]=true;
			cost=0;
			travel(i,i,1);
		}
		//결과 출력
		System.out.println(minCost);
	}
}
