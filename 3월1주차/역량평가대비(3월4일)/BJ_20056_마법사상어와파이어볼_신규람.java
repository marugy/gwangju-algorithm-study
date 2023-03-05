package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_20056_마법사상어와파이어볼 {

	static int N;
	static int M;
	static int K;
	static ArrayList<Fireball> fbList = new ArrayList<>();
	static int sumM = 0;
	// Fireball정보를 저장할 class
	static class Fireball{
		int r;
		int c;
		int m;
		int s;
		int d;
		int sumCnt; // 몇개가 합쳐졌는지 저장할 변수
		boolean difd; // 합쳐지는 파이어볼의 방향의 홀짝이 다른지 저장할 변수 다르면 true
		Fireball(int r, int c,int m, int s, int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			this.sumCnt=1;
			this.difd = false;
		}
	}
	static void play() {
		//K번 명령 수행
		for(int kk = 0; kk <K; kk++) {
			if(fbList.size()==0)
				return;
			//이동 시키기(마지막 열, 행이면 첫번째 열, 행으로)
			moveFire();
			//합치기
			sumFire();
			//분리시키기
			devideFire();
		}
		//남아있는 파이어볼 질량 합 구하기
		sumWeight();
	}
	//모든 fireball을 방향에 따라 이동시키기
	public static void moveFire() {
		//하나씩 이동 처리
		for (Fireball fb : fbList) {
			if(fb.d==0) {
				//r좌표 위로 이동
				fb.r-=fb.s%N;
				if(fb.r<0)
					fb.r = N+fb.r;
			}else if(fb.d==1) {
				//r좌표 위로
				fb.r-=fb.s%N;
				if(fb.r<0)
					fb.r = N+fb.r;
				//c좌표 오른쪽으로
				fb.c+=fb.s%N;
				fb.c%=N;
			}else if(fb.d==2) {
				//c좌표 이동
				fb.c+=fb.s%N;
				fb.c%=N;
			}else if(fb.d==3) {
				//r좌표 이동
				fb.r+=fb.s%N;
				fb.r%=N;
				//c좌표 이동
				fb.c+=fb.s%N;
				fb.c%=N;
			}else if(fb.d==4) {
				//r좌표 이동
				fb.r+=fb.s%N;
				fb.r%=N;
			}else if(fb.d==5) {
				//r좌표 이동
				fb.r+=fb.s%N;
				fb.r%=N;
				//c좌표 이동
				fb.c-=fb.s%N;
				if(fb.c<0)
					fb.c = N+fb.c;
			}else if(fb.d==6) {
				//c좌표 이동
				fb.c-=fb.s%N;
				if(fb.c<0)
					fb.c = N+fb.c;
			}else if(fb.d==7) {
				//r좌표 이동
				fb.r-=fb.s%N;
				if(fb.r<0)
					fb.r = N+fb.r;
				//c좌표 이동
				fb.c-=fb.s%N;
				if(fb.c<0)
					fb.c = N+fb.c;
			}
		}
		//fireball 목록 좌표 순으로 정렬하기
		fbList.sort((a,b)->{
			if(a.r==b.r) {
				return a.c-b.c;
			}return a.r-b.r;
		});
	}
	
	//같은 위치에 있는 fireball 합치기
	public static void sumFire() {
		//사이즈가 하나면 pass
		if(fbList.size()<=1)
			return;
		//새롭게 만들어진 결과를 저장할 fireballList
		ArrayList<Fireball> newList = new ArrayList<>();
		//뒤부터 비교
		for (int i = fbList.size()-1; i>=1; i--) {
			//같은 좌표에 있다면
			if(fbList.get(i).r == fbList.get(i-1).r && fbList.get(i).c ==fbList.get(i-1).c ) {
				fbList.get(i-1).m +=fbList.get(i).m;//질량 합치기
				fbList.get(i-1).s +=fbList.get(i).s;//속도 합치기
				fbList.get(i-1).sumCnt+=fbList.get(i).sumCnt;//합쳐진 수 증가
				if(fbList.get(i-1).d%2 !=fbList.get(i).d%2 || fbList.get(i).difd) {//방향의 홀짝이 다르면 true로 변경
					fbList.get(i-1).difd=true;
				}
				fbList.remove(i); //합치고 남은건 제거
			}else {//같은 좌표의 fire ball이 없다면
				newList.add(fbList.get(i));
			}
		}
		newList.add(fbList.get(0));//마지막 하나 추가
		//fbList를 새로운 리스트로 갱신
		fbList = newList;
	}
	
	//fireball 목록 중 합쳐진 fire ball 나누기
	public static void devideFire() {
		//새롭게 만들어진 결과를 저장할 리스트
		ArrayList<Fireball> newList = new ArrayList<>();
		//뒤부터 비교
		for (int i = fbList.size()-1; i>=0; i--) {
			if(fbList.get(i).sumCnt!=1) { //여러개가 합쳐진 경우
				if(fbList.get(i).m/5==0)//나눴을때 질량이 0이면 제거
					continue;
				else {
					int startd = 0;
					if(fbList.get(i).difd) {//모두 짝이거나 홀이였다면 0,2,4,6 아니면 1,3,5,7
						startd=1;
					}
					for(int j=startd; j<8; j+=2) {//새로운 babyfireball 4개 추가
						Fireball baby = new Fireball(fbList.get(i).r, fbList.get(i).c, fbList.get(i).m/5, fbList.get(i).s/fbList.get(i).sumCnt, j);
						newList.add(baby);
					}
				}
			}else {//합쳐지지 않은 경우
				newList.add(fbList.get(i));//리스트에 그대로추가해주기
			}
		}
		//fbList를 새로운 리스트로 갱신
		fbList = newList;
	}
	
	//남아있는 파이어볼 질량 구하기
	public static void sumWeight() {
		if(fbList.size()==0)
			return;
		for (Fireball fb : fbList) {
			sumM += fb.m;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//값 입력
		N = Integer.parseInt(st.nextToken());//map 크기
		M = Integer.parseInt(st.nextToken());//파이어볼 갯수
		K = Integer.parseInt(st.nextToken());//명령 수
		
		for (int fireball = 0; fireball < M; fireball++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fbList.add(new Fireball(r,c,m,s,d));
		}
		//수행
		play();
		//결과 출력
		System.out.println(sumM);
	}
}
