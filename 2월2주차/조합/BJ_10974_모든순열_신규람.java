package bj;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ_10974_모든순열_신규람 {

	public static int n;
	public static int cnt = 0;
	
	static ArrayList<Integer> list = new ArrayList<>(); //숫자 리스트
	static ArrayList<Integer> ans = new ArrayList<>(); //순열을 담을 리스트
	static boolean[] visited = new boolean[9]; // 담았는지 확인할 리스트
	
	public static void DFS(int cnt) {
		if(cnt == n) { //n개만큼 담았으면
			for(int i=0;i<ans.size();i++) { // 담은 순열을 출력
				System.out.print(ans.get(i)+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0;i<n;i++) { //n개를 담지 않았을때
			if(visited[i]) //이미 담은 수라면 통과
				continue;
			visited[i]=true;
			ans.add(list.get(i)); // 아직 담지 않은 수 담고
			DFS(cnt+1); // 재귀 수행
			ans.remove(ans.size()-1); // 순서가 바뀌어 출력이 가능하기 때문에 담은것을 제거하고
			visited[i]=false; //false로 전환
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc  = new Scanner(System.in);
		//n 입력
		n = sc.nextInt();
		
		//n까지 list에 입력
		for(int i=1;i<=n;i++) {
			list.add(i);
		}
		//재귀함수 수행
		DFS(0);
	}

}

