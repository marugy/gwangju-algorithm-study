package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10830_행렬제곱 {
	
	static int N;
	static long B;
	
	//행렬 제곱을 해주는 함수
	public static int[][]powerMatrix(int[][]arr, long B){
		//1이면 그냥 반환
		if(B==1) {
			return arr;
		}
		//B의 절반의 제곱
		int[][] matrix = powerMatrix(arr, B/2);
		//짝수면 절반 * 절반
		matrix = mulMatrix(matrix, matrix);
		//홀수면 절반 * 절반에 원래 배열 하나 더
		if(B%2==1) {
			matrix = mulMatrix(matrix,arr);
		}
		return matrix;
	}
	
	//A와 B행렬 곱하기
	public static int[][] mulMatrix(int[][]A, int[][]B){
		//곱 결과를 담을 배열
		int[][] tmp = new int[N][N];
		//i,j에 i행 j열을 곱해서 더하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					//1000이하 자리까지만 출력하니까 나머지로만 연산
					tmp[i][j] += A[i][k]%1000 * B[k][j]%1000;
					tmp[i][j] %= 1000;
				}
			}
		}//결과 반환
		return tmp;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//값 입력
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		//초기 배열 입력 받기
		int [][]arr = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken())%1000;
			}
		}
		//결과행렬 담기
		int[][] resultArr = powerMatrix(arr,B);
		//결과 출력하기
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				System.out.print(resultArr[i][j]%1000+" ");
			System.out.println();
		}
	}
}
