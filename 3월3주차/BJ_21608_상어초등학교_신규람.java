package bj;

import static java.lang.Integer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_21608_상어초등학교 {

	static int N;
	static int [][]room;
	
	static Stu []students;
	static Stu []studentList;
	static int [][]dir= {{-1,0},{1,0},{0,-1},{0,1}};
	static int totalLike=0;
	
	//학생 번호와 좋아하는 친구 4명을 담을 class
	static class Stu{
		int num;
		int []likeList = null;
		Stu(int num, int frd1, int frd2, int frd3, int frd4) {
			this.num = num;
			int []fri = {frd1, frd2, frd3, frd4};
			this.likeList = fri;
		}
	}
	//현재 좌표와 빈 자리, 좋아하는 사람 수를 저장할 class
	static class Pos{
		int x;
		int y;
		int like;
		int empty;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static void makeClass(int stuIdx) {
		//앉힐 학생
		Stu stu = students[stuIdx];
		//자리 목록
		ArrayList<Pos> posList = new ArrayList<>();
		//전체 자리 순회
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//빈자리일떄 확인
				if(room[i][j]==0) {
					//해당 자리 정보
					Pos seat = new Pos(i, j);
					//사방에 좋아하는 짝이 있는지 
					for(int d=0; d<4;d++) {
						int xx = i+dir[d][0];
						int yy = j+dir[d][1];
						if(0<=xx && xx < N && 0<=yy && yy <N) { // 반 크기
							if(room[xx][yy]==0) { //빈자리일떄
								seat.empty++;
							}
							else {//빈자리가 아닐떄
								for (int fri : stu.likeList) {//좋아하는 목록에 있는지
									if(fri == room[xx][yy]) {
										seat.like++;
									}
								}
							}
						}
					}
					posList.add(seat);//자리 리스트에 추가
				}
			}
		}
		//문제 조건에 맞도록
		posList.sort((a,b)->{
			if(a.like == b.like && a.empty == b.empty) { //좋아하는 사람 수와 빈자리의 수가 같으면
				if(a.x == b.x)//행이 같다면 열로 오름차순
					return a.y-b.y;
				else//행으로 오름차순 정렬
					return a.x-b.x;
			}else if(a.like == b.like) {//좋아하는 사람수만 같다면
				return b.empty - a.empty;//빈자리 수로 내림차순 정렬
			}else {
				return b.like - a.like;//좋아하는 사람 수 내림차순 정렬
			}
		});
		Pos best = posList.get(0);//가장 최적의 자리 가져와서
		room[best.x][best.y]=stu.num;//자리 지정
	}
	
	static void calTotalLike() {
		//모든 자리 순회
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Stu stu = studentList[room[i][j]];//현재 자리의 학생 정보
				int cnt = -1;
				for(int d=0; d<4;d++) {//
					int xx = i+dir[d][0];
					int yy = j+dir[d][1];
					if(0<=xx && xx < N && 0<=yy && yy <N) { // 반 크기
						for (int fri : stu.likeList) {//좋아하는 목록에 있는지
							if(fri == room[xx][yy]) {
								cnt++;//있다면 수 증가
							}
						}
					}
				}if(cnt!=-1)//-1이 아니면 만족도 증가
					totalLike+= Math.pow(10, cnt);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N=parseInt(br.readLine());
		room = new int[N][N];
		students = new Stu[N*N];//순서대로 담을 리스트
		studentList = new Stu[N*N+1];//학생 번호 순 리스트
		//정보 입력받기
		for(int i=0;i<N*N;i++) {
			st = new StringTokenizer(br.readLine());
			int stuNum = parseInt(st.nextToken());
			int fri1 = parseInt(st.nextToken());
			int fri2 = parseInt(st.nextToken());
			int fri3 = parseInt(st.nextToken());
			int fri4 = parseInt(st.nextToken());
			students[i] = new Stu(stuNum, fri1, fri2, fri3, fri4);
			studentList[stuNum] = new Stu(stuNum, fri1, fri2, fri3, fri4);
		}
		//자리 지정해주기
		for(int i=0; i<N*N;i++) {
			makeClass(i);
		}
		//만족도 검사
		calTotalLike();
		//결과 출력
		System.out.println(totalLike);
	}
}
