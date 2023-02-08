package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BJ_1182_부분수열의합_신규람 {

	static int n;
	static int s;
	static int sum=0;
	static int numCnt = 0;
	static int cnt =0;
	static int[] arr;
	static boolean[] visited;
	
	public static void findAnswer(int i) {
		//양의 정수 배열 판단 numCnt, sum=s면 cnt증가
		if(sum==s && numCnt!=0) {
			cnt++;
		}
		//조합 생성
		for(int idx=i; idx<n; idx++) {
			if(visited[idx]==false) {
				visited[idx]=true;
				sum+=arr[idx];
				numCnt++;
				
				findAnswer(idx+1);
				
				numCnt--;
				sum-=arr[idx];
				visited[idx]=false;
			}
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//값 입력받기
		String a = br.readLine();
		n = Integer.parseInt(a.split(" ")[0]);
		s = Integer.parseInt(a.split(" ")[1]);
		
		//배열 선언
		arr = new int [n];
		visited = new boolean[n];
		
		String[] num = br.readLine().split(" ");
		
		//값 입력
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(num[i]);
		}
		
		//함수 실행
		findAnswer(0);
		System.out.println(cnt);
	}
}
