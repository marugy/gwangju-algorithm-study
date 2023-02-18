package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1068_트리 {
	static int N;
	static int root; //루트idx
	static int []tree;//트리정보
	static boolean []isUse;//사용괬는 홧라
	static int count;//나뭇잎수
    
	public static void removeNode(int d) {//d번노드삭제
		tree[d] = -2; //삭제된 노드 -2로 표시
        for(int i = 0; i < N; i++) {
            if(tree[i] == d) {//d인 노드 삭제하고 d의 자식들을 없어기 위해 재귀
            	removeNode(i);
            }
        }
	}
	//리프 노드 수 세기
	public static void countLeaf(int s) {//시작은 루트노드부터
        boolean isLeaf = true;//리프노드인지
        isUse[s] = true;//사용한것으로
        
        if(tree[s] != -2) {//삭제되지 않은 노드일때
            for(int i = 0; i < N; i++) {//순회
                if(tree[i] == s && isUse[i] == false) {//아직 방문안한 자식 노드가 있다면
                    countLeaf(i);//그 노드로 재귀
                    isLeaf = false;
                }
            }
            if(isLeaf) count++;//자식노드로 재귀를 안했다면 리프노드니까 갯수 추가
        }
    }
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//노드 수
		N = Integer.parseInt(br.readLine());
		tree = new int[N];
		isUse = new boolean[N];
		
		//트리 정보 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0; i<N; i++) {
			tree[i]=Integer.parseInt(st.nextToken());
			if(tree[i]==-1) {
				root = i;
			}
		}
        //삭제할 노드번호
		int D = Integer.parseInt(br.readLine());
		removeNode(D);
		
		count = 0;
        //나뭇잎 세기
        countLeaf(root);//루트부터 순회
        //결과 출력
        System.out.println(count);
	}
}