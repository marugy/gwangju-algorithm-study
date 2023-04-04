import java.io.*;
import java.util.*;

public class BJ_20057_마법사상어와토네이도 {
	static int N;
	static int field[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		field = new int[N][N];
		for(int y=0; y<N; y++) {
			st =  new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				field[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 토네이도를 시전하기 전 필드의 모래개수 구하기
		int send1 = getSendCount();
		// 토네이도
		tornado();
		// 토네이도를 시전한 후 필드의 모래개수 구하기
		int send2 = getSendCount();

		// 필드 밖의 모래개수 = 토네이도 전 필드의 모래개수 - 토네이도 후 필드의 모래개수
		System.out.println(send1 - send2);
		
		br.close();
	}
	
	// 토네이도
	static void tornado() {
		// 초기위치
		int x = N/2;
		int y = N/2;

		// 설정
		int d = 0;
		int count = 0;
		int target = 1;
		
		// 이동
		while(true) {
			if(x == 0 && y == 0) break;
			
			// 토네이도가 이동하면서 모래가 흩날린다.
			spread(x, y, d);
			
			x = x + dx[d%4];
			y = y + dy[d%4];
			
			count++;
			if(count == target) {
				d++;
				if(d%2 == 0) {
					target++;
				}
				
				count = 0;
			}
		}
	}
	
	// 모래 흩날리기
	static void spread(int x, int y, int d) {
		double next = field[y+dy[d%4]][x+dx[d%4]]; // 다음 위치 모래개수
		double percentage = next/100; // 다음 위치 모래개수의 1%
		int spread = 0; // 비율만큼 이동한 모래의 개수
		field[y+dy[d%4]][x+dx[d%4]] = field[y][x]; // 다음 위치 모래개수는 현재 위치의 모래개수로 덮어씌운다.
		
		// 각 비율만큼 흩날리는 모래
		if(isInArray(x+dx[d%4]*3, y+dy[d%4]*3)) {
			field[y+dy[d%4]*3][x+dx[d%4]*3] += (int)(percentage*5);
		}
		spread += (int)(percentage*5);
		if(isInArray(x+dx[d%4]*2+dx[(d+1)%4], y+dy[d%4]*2+dy[(d+1)%4])) {
			field[y+dy[d%4]*2+dy[(d+1)%4]][x+dx[d%4]*2+dx[(d+1)%4]] += (int)(percentage*10);
		}
		spread += (int)(percentage*10);
		if(isInArray(x+dx[d%4]*2+dx[(d+3)%4], y+dy[d%4]*2+dy[(d+3)%4])) {
			field[y+dy[d%4]*2+dy[(d+3)%4]][x+dx[d%4]*2+dx[(d+3)%4]] += (int)(percentage*10);
		}
		spread +=(int)(percentage*10);
		if(isInArray(x+dx[d%4]+dx[(d+1)%4], y+dy[d%4]+dy[(d+1)%4])) {
			field[y+dy[d%4]+dy[(d+1)%4]][x+dx[d%4]+dx[(d+1)%4]] += (int)(percentage*7);
		}
		spread += (int)(percentage*7);
		if(isInArray(x+dx[d%4]+dx[(d+3)%4], y+dy[d%4]+dy[(d+3)%4])) {
			field[y+dy[d%4]+dy[(d+3)%4]][x+dx[d%4]+dx[(d+3)%4]] += (int)(percentage*7);
		}
		spread += (int)(percentage*7);
		if(isInArray(x+dx[d%4]+dx[(d+1)%4]*2, y+dy[d%4]+dy[(d+1)%4]*2)) {
			field[y+dy[d%4]+dy[(d+1)%4]*2][x+dx[d%4]+dx[(d+1)%4]*2] += (int)(percentage*2);
		}
		spread += (int)(percentage*2);
		if(isInArray(x+dx[d%4]+dx[(d+3)%4]*2, y+dy[d%4]+dy[(d+3)%4]*2)) {
			field[y+dy[d%4]+dy[(d+3)%4]*2][x+dx[d%4]+dx[(d+3)%4]*2] += (int)(percentage*2);
		}
		spread += (int)(percentage*2);
		if(isInArray(x+dx[(d+1)%4], y+dy[(d+1)%4])) {
			field[y+dy[(d+1)%4]][x+dx[(d+1)%4]] += (int)(percentage);
		}
		spread += (int)(percentage);
		if(isInArray(x+dx[(d+3)%4], y+dy[(d+3)%4])) {
			field[y+dy[(d+3)%4]][x+dx[(d+3)%4]] += (int)(percentage);
		}
		spread += (int)(percentage);
		
		// alpha위치에는 비율만큼 흩날리고 남은 모래가 이동한다.
		double alpha = next - spread;
		if(isInArray(x+dx[d%4]*2, y+dy[d%4]*2)) {
			field[y+dy[d%4]*2][x+dx[d%4]*2] += alpha;
		}
	}
	
	// 배열에 좌표가 존재하는지 확인하기
	static boolean isInArray(int x, int y) {
		if(0 <= x && x < N && 0 <= y && y < N) {
			return true;
		} else {
			return false;
		}
	}
	
	// 필드에 남은 모래개수 구하기
	static int getSendCount() {
		int count = 0;
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				count += field[y][x];
			}
		}
		
		return count;
	}
}
