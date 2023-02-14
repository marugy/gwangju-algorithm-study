package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1026_보물 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N]; //A 배열
		int[] B = new int[N]; // B 배열
		int[] bNum = new int[101]; // B배열의 숫자가 몇개있는지 저장할 배열
		
		//A배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		//B배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			bNum[B[i]]++; //입력 받은 수의 갯수 증가
		}
		
		Arrays.sort(A); //오름차순 정렬
		int sum =0;
		for(int i=N-1; i>=0; i--) { //A의 큰 수부터
			for(int j=0; j<101;j++) { //B의 작은 값과 곱해줌
				if(bNum[j]>0) { //갯수가 남아있다면
					sum+=A[i]*j;
					bNum[j]--;
					break;
				}
			}
		}
		System.out.println(sum);
	}
}
