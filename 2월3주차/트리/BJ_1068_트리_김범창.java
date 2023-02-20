package study02;

import java.io.*;
import java.util.*;

public class BJ_1068_트리_김범창 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int root = 0;
		int count = 0;
		
		String[] input = br.readLine().split(" ");
		int removeNode = Integer.parseInt(br.readLine());
		
		// 트리를 리스트 구현 : i가 노드의 번호이면, list.get(i)가 부모노드의 번호이다.
		ArrayList<Integer> list = new ArrayList<>();
		
		// 노드 입력받기
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(input[i]));
		}
		
		// 루트노드 위치 구하기
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) == -1) {
				root = i;
			}
		}
		
		// 노드 삭제하기
		list.set(removeNode, -100);
		
		// 리프노드 개수 구하기
		Queue<Integer> que = new LinkedList<>();
		if(removeNode != root) {
			// 루트노트에서 시작한다.
			que.add(root);
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			boolean isLeaf = true;
			
			for(int i=0; i<list.size(); i++) {
				// 현재 노드를 부모로 가지는 노드 찾기
				if(list.get(i) == cur) {
					que.add(i);
					
					// 현재 노드가 자식을 갖기 때문에 리프노드가 아니다.
					isLeaf = false;
				}
			}
			
			if(isLeaf) {
				count++;
			}
		}
		
		System.out.println(count);
		
		br.close();
	}
}
