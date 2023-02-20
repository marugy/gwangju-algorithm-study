package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1987_알파벳 {

	static int R;
	static int C;
	static int maxMove = Integer.MIN_VALUE;
	static char[][] board;
	static boolean[] alpha=new boolean[26];
	static int [][]dir = {{1,0},{-1,0},{0,1},{0,-1}};
	
	
	public static void checkBoard(int i, int j, int move) {
		if(!canMove(i,j)) {
			if(maxMove < move)
				maxMove = move;
		}
		for(int d=0;d<4;d++) {
			int xx = i +dir[d][0];
			int yy = j +dir[d][1];
			//아직 방문하지 않은 범위내에 있는 같은 정보의 경우
			if(0<=xx && xx<R && 0<=yy && yy<C && alpha[board[xx][yy]-'A']==false) {
				alpha[board[xx][yy]-'A']=true;
				checkBoard(xx, yy,move+1);
				alpha[board[xx][yy]-'A']=false;
			}
		}
	}
	
	public static boolean canMove(int i, int j) {
		boolean pos = false;
		for(int d=0;d<4;d++) {
			int xx = i +dir[d][0];
			int yy = j +dir[d][1];
			//아직 방문하지 않은 범위내에 있는 같은 정보의 경우
			if(0<=xx && xx<R && 0<=yy && yy<C && alpha[board[xx][yy]-'A']==false) {
				pos=true;
			}
		}
		return pos;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//값 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];//보드 정보를 담을 배열
		
		//보드 입력받기
		for(int i=0;i<R;i++) {
			st=new StringTokenizer(br.readLine());
			board[i]=st.nextToken().toCharArray();
		}
		alpha[board[0][0]-'A']=true;
		checkBoard(0,0,1);
		System.out.println(maxMove);
	}
}
