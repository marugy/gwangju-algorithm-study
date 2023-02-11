package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13335_트럭 {
	
	public static void main(String[] args) throws IOException{
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		//값 입력받기
		int n = Integer.parseInt(st.nextToken()); // 트럭 수
		int w = Integer.parseInt(st.nextToken()); // 다리 길이
		int L = Integer.parseInt(st.nextToken()); // 다리 하중
		
		int time = 0; // 총 시간
		int bridgeWeight = 0; // 다리에 올라와있는 무게
		
		Queue<Integer> truck = new LinkedList<>();
		Queue<Integer> bridge = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		
		//트럭 정보 입력받기
		for(int i = 0; i < n; i++) {
			truck.add(Integer.parseInt(st.nextToken()));
		}
		//트럭 이동을 위해 다리에 0 채우기
		for(int i = 0; i < w; i++) {
			bridge.add(0);
		}
		
		while(!bridge.isEmpty()) { // 트럭이 비어도 다리에 트럭이 있을 수 있으므로 bridge가 빌때까지
			time++; // 1초 증가
			bridgeWeight-=bridge.peek(); //나가는 트럭만큼 하중 감소
			bridge.remove();
			if(!truck.isEmpty()) { //트럭이 남아 있으면
				if(bridgeWeight + truck.peek() <= L) { // 트럭이 들어갈 수 있는 하중 상태이면
					bridgeWeight+=truck.peek(); //하중 더하고
					bridge.add(truck.peek()); // 다리에 집어넣기
					truck.remove();
				}else { //트럭이 남아있지만 들어갈 수 없으면 0 집어넣기
					bridge.add(0);
				}
			}
		}
		System.out.println(time);
	}
}