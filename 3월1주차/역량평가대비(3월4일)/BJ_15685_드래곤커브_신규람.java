package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BJ_15685_드래곤커브 {

	static int cnt;//드래곤 커브의 일부인 것 개수 출력
	static ArrayList<Pos> DCList = new ArrayList<>(); //D커브 리스트
	static boolean[][] curve = new boolean[101][101];
	
	static class Pos{ //0세대의 D커브, 한 변씩 시작 좌표 끝 좌표
		int x,y; //시작 좌표, 끝 좌표, 방향
		Pos(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	//D커브 만들기
	static void play(int px, int py,int g, int nowG) { //px,py는 돌릴 끝점
		if(g==nowG)//목표 세대라면 종료
			return;
		for(int i=DCList.size()-1; i>=0; i--) {
			//기준점인 좌표는 생략
			if(DCList.get(i).x == px && DCList.get(i).y == py) continue;
			//회전 시킨 새로운 좌표 생성
			int newX = px+(DCList.get(i).y-py);
			int newY = py-(DCList.get(i).x-px);
			DCList.add(new Pos(newX,newY));//좌표 추가
			curve[newX][newY]=true;//방문 처리
		}
		//다음 세대로 진행
		play(DCList.get(DCList.size()-1).x, DCList.get(DCList.size()-1).y, g, nowG+1);
	}
	static void calPoint() {//네꼭지점이 드래곤 커브인것 찾기
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(curve[i][j] && curve[i][j+1]&&curve[i+1][j] && curve[i+1][j+1])
					cnt++;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		//N번 반복
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()); // 시작점
			int x = Integer.parseInt(st.nextToken()); // 시작점
			int d = Integer.parseInt(st.nextToken()); // 시작방향 0:오른, 1:위, 2:왼, 3:아래
			int g = Integer.parseInt(st.nextToken()); // 세대
			DCList = new ArrayList<>(); //현재 값을 넣어둘 리스트
			//0세대의 끝점 찾기
			int x2=0,y2=0;
			if(d==0) {
				x2=x;
				y2=y+1;
			}else if(d==1) {
				x2=x-1;
				y2=y;
			}else if(d==2) {
				x2=x;
				y2=y-1;
			}else if(d==3) {
				x2=x+1;
				y2=y;
			}
			DCList.add(new Pos(x,y));//0세대 시작점
			curve[x][y]=true;
			DCList.add(new Pos(x2,y2));//0세대 끝점 넣기
			curve[x2][y2]=true;
			//드래곤 커브 생성 시작
			play(x2, y2, g, 0); //돌릴 기준점, 목표 세대를 인자로 전달
		}
		//네 꼭짓점이 모두 드래곤 커브의 일부인 것 갯수 세기
		calPoint();
		//결과 출력
		System.out.println(cnt);
	}
}
