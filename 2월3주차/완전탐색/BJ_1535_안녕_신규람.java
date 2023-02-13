package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1535_안녕 {

	static int sHealth = 100;
	static int sHappy=0;
	static int maxHappy=0;
	static int N;
	static int[] lose;
	static int[] happy;
	static boolean []visited;
	
	public static void makeHappy(int index) {
		//체력이 남아 있을때 최대 기쁨 갱신
		if(sHappy>maxHappy)
			maxHappy=sHappy;
		
		for(int i=index; i <N; i++) {
			//아직 담지 않은 수라면
			if(visited[i]==false) {
				//방문 및 체력, 기쁨 갱신
				visited[i]=true;
				sHealth -= lose[i];
				sHappy += happy[i];
				if(sHealth>0) // 체력이 있을때만 재귀
					makeHappy(i+1);
				sHappy -= happy[i];
				sHealth += lose[i];
				visited[i]=false;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//사람 수
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		
		//잃는 체력 입력받기
		lose = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			lose[i]=Integer.parseInt(st.nextToken());
		
		//얻는 기쁨 입력받기
		happy = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			happy[i]=Integer.parseInt(st.nextToken());
		
		//함수 실행
		makeHappy(0);
		//최대 기쁨 출력
		System.out.println(maxHappy);
	}
}
