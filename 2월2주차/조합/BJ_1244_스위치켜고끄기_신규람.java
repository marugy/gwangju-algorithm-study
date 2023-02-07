package bj;

import java.util.Scanner;

public class BJ_1244_스위치켜고끄기_신규람 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//n 입력
		int n =sc.nextInt();
		int[]arr= new int[n+1];
		
		//스위치 입력 받기
		for(int i=1;i<=n;i++) {
			arr[i]=sc.nextInt();
		}
		
		//학생 수
		int stu = sc.nextInt();
		
		for(int i=0;i<stu;i++) {
			//성별
			int gender = sc.nextInt();
			//위치
			int pos = sc.nextInt();
			
			
			//남자일때
			if(gender == 1) {
				int num = 1;
				//배수만큼 반대로
				while(pos*num<=n) {
					if(arr[pos*num]==1)
						arr[pos*num]=0;
					else
						arr[pos*num]=1;
					num++;
				}
			}
			//여자일때
			else {
				//현재 위치 반대로
				if(arr[pos]==1)
					arr[pos]=0;
				else
					arr[pos]=1;
				
				int t = pos-1;
				pos++;
				//현재위치를 기준으로 좌우가 동일하면 스위치 변경
				while(0<t && pos<=n) {
					if(arr[t]==arr[pos]) {
						if(arr[t]==1)
							arr[pos]=arr[t]=0;
						else
							arr[pos]=arr[t]=1;
						t--;
						pos++;
					}
					//동일하지 않으면 중단
					else {
						break;
					}

				}
			}
		}
		//결과 출력
		for(int i=1;i<=n;i++) {
			System.out.print(arr[i]+" ");
			//20개씩 엔터
			if(i%20==0)
				System.out.println();
		}

	}
}
