package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14713_앵무새_신규람 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//N입력
		int N = Integer.parseInt(st.nextToken());
		
		//앵무새의 문장을 담을 Deque List 선언
		List<Deque<String>> engmu = new ArrayList<Deque<String>>();
		
		//앵무새들 문장 입력받기
		for(int i=0;i<N;i++) {
			Deque<String> q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				q.add(st.nextToken());
			}
			engmu.add(q);
		}
		
		//머시깽이의 문장 L입력받기
		Deque<String> L = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			L.add(st.nextToken());
		}
		//L문장 살펴보기
		while(!L.isEmpty()) {
			String str = L.peek();
			boolean isThere = false;//L의 요소가 앵무새의 문장에 있는지 여부
			
			for(int i=0;i<N;i++) {//앵무새들 문장 훑어보기
				if(engmu.get(i).isEmpty()) //앵무새 문장이 비었다면 패스
					continue;
				if(engmu.get(i).peek().equals(str)) {//동일하다면
					engmu.get(i).removeFirst(); //앵무새문장에서 제거
					L.removeFirst();//L문장에서도 제거
					isThere = true;//있음으로 변경
					break;//단어는 중복되지 않다고 했으니 중단
				}
			}
			if(!isThere)//문장에있지 않다면 중단
				break;
		}
		if(L.isEmpty()) {//L문장을 모두 사용한경우
			boolean pass = true;
			
			for(int i=0;i<N;i++) {//앵무새의 단어들중 빠트린게 없는지 확인
				if(!engmu.get(i).isEmpty())//빠트린게 있다면
					pass=false;//false로
			}
			if(pass) { //결과 출력
				System.out.println("Possible");
			}else {
				System.out.println("Impossible");
			}
		}else{//잘못된 단어를 적어 L에 단어가 남아있는 경우
			System.out.println("Impossible");
		}
	
			
	}
}
