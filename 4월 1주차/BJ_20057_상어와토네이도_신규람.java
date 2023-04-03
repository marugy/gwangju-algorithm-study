package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20057_상어와토네이도 {

	static int N;
	static int nowX;
	static int nowY;
	static int[][] map;
	static int[][] spread = {};
	static int outSend=0;

	static void tornado() {
		int move = 1;
		int dirNum = 0; // 좌, 하, 우, 상
		int dirY = -1;
		int dirX = 1;

		while (true) {
			// 좌우 이동
			for (int i = 0; i < move; i++) {
				nowY += dirY;// 이동
				if (!(0 <= nowY && nowY < N)) {// 범위 확인
					nowY -= dirY;
					break;
				}
				if (map[nowX][nowY] != 0) {
					spreadSend(nowX, nowY, dirX, dirY, dirNum);
				}
				if(nowX==0&&nowY==0)
					return;
			}
			dirNum++;
			// 상하 이동
			for (int i = 0; i < move; i++) {
				nowX += dirX;// 이동
				if (!(0 <= nowX && nowX < N)) {// 범위 체크
					nowX -= dirX;
					break;
				}
				if (map[nowX][nowY] != 0) {
					spreadSend(nowX, nowY, dirX, dirY, dirNum);
				}
				if(nowX==0&&nowY==0)
					return;
			}
			dirNum++;
			move++;
			dirY *= -1;
			dirX *= -1;
			dirNum %= 4;
		}
	}

	static void spreadSend(int nowX, int nowY, int dirX, int dirY, int dirNum) { // dir

		int send = map[nowX][nowY]; //원래 send
		int useSend = 0;
		
		
		if(dirNum==0) {
			if (inMap(nowX - 2, nowY)) {
				map[nowX - 2 ][nowY] += send * 0.02;
				useSend+=send * 0.02;
			} else {
				outSend+=send * 0.02;
				useSend+=send * 0.02;
			}
			if (inMap(nowX - 1, nowY - 1)) {
				map[nowX - 1][nowY - 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX - 1, nowY)) {
				map[nowX - 1][nowY] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX - 1, nowY + 1)) {
				map[nowX - 1][nowY + 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX, nowY - 2)) {
				map[nowX][nowY - 2] += send * 0.05;
				useSend+=send * 0.05;
			}else {
				outSend+=send * 0.05;useSend+=send * 0.05;
			}
			if (inMap(nowX + 1, nowY - 1)) {
				map[nowX + 1][nowY - 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX + 1, nowY)) {
				map[nowX + 1][nowY] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX + 1, nowY + 1)) {
				map[nowX + 1][nowY + 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX + 2, nowY)) {
				map[nowX + 2][nowY] += send * 0.02;
				useSend+=send * 0.02;
			}else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX, nowY - 1)) {
				map[nowX][nowY - 1] += send-useSend;
			}else {
				outSend+=send-useSend;
			}
		}else if (dirNum == 1) {
			if (inMap(nowX, nowY - 2)) {
				map[nowX][nowY - 2] += send * 0.02;
				useSend+=send * 0.02;
			} else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX + 1, nowY - 1)) {
				map[nowX + 1][nowY - 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX, nowY - 1)) {
				map[nowX][nowY - 1] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX - 1, nowY - 1)) {
				map[nowX - 1][nowY - 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX+2, nowY)) {
				map[nowX+2][nowY] += send * 0.05;
				useSend+=send * 0.05;
			}else {
				outSend+=send * 0.05;useSend+=send * 0.05;
			}
			if (inMap(nowX + 1, nowY + 1)) {
				map[nowX + 1][nowY + 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX, nowY + 1)) {
				map[nowX][nowY + 1] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX - 1, nowY + 1)) {
				map[nowX - 1][nowY + 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX, nowY+2)) {
				map[nowX][nowY+2] += send * 0.02;
				useSend+=send * 0.02;
			}else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX+1, nowY)) {
				map[nowX+1][nowY] += send-useSend;
			}else {
				outSend+=send-useSend;
			}
		} else if (dirNum == 2) {
			if (inMap(nowX - 2, nowY)) {
				map[nowX - 2 ][nowY] += send * 0.02;
				useSend+=send * 0.02;
			} else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX - 1, nowY + 1)) {
				map[nowX - 1][nowY + 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX - 1, nowY)) {
				map[nowX - 1][nowY] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX - 1, nowY - 1)) {
				map[nowX - 1][nowY - 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX, nowY + 2)) {
				map[nowX][nowY + 2] += send * 0.05;
				useSend+=send * 0.05;
			}else {
				outSend+=send * 0.05;useSend+=send * 0.05;
			}
			if (inMap(nowX + 1, nowY + 1)) {
				map[nowX + 1][nowY + 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX + 1, nowY)) {
				map[nowX + 1][nowY] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX + 1, nowY - 1)) {
				map[nowX + 1][nowY - 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX + 2, nowY)) {
				map[nowX + 2][nowY] += send * 0.02;
				useSend+=send * 0.02;
			}else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX, nowY + 1)) {
				map[nowX][nowY + 1] += send-useSend;
			}else {
				outSend+=(send-useSend);
			}
		} else if (dirNum == 3) {
			if (inMap(nowX, nowY - 2)) {
				map[nowX][nowY - 2] += send * 0.02;
				useSend+=send * 0.02;
			} else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX - 1, nowY - 1)) {
				map[nowX - 1][nowY - 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX, nowY - 1)) {
				map[nowX][nowY - 1] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX + 1, nowY - 1)) {
				map[nowX + 1][nowY - 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX-2, nowY)) {
				map[nowX-2][nowY] += send * 0.05;
				useSend+=send * 0.05;
			}else {
				outSend+=send * 0.05;useSend+=send * 0.05;
			}
			if (inMap(nowX - 1, nowY + 1)) {
				map[nowX - 1][nowY + 1] += send * 0.1;
				useSend+=send * 0.1;
			}else {
				outSend+=send * 0.1;useSend+=send * 0.1;
			}
			if (inMap(nowX, nowY + 1)) {
				map[nowX][nowY + 1] += send * 0.07;
				useSend+=send * 0.07;
			}else {
				outSend+=send * 0.07;useSend+=send * 0.07;
			}
			if (inMap(nowX + 1, nowY + 1)) {
				map[nowX + 1][nowY + 1] += send * 0.01;
				useSend+=send * 0.01;
			}else {
				outSend+=send * 0.01;useSend+=send * 0.01;
			}
			if (inMap(nowX, nowY+2)) {
				map[nowX][nowY+2] += send * 0.02;
				useSend+=send * 0.02;
			}else {
				outSend+=send * 0.02;useSend+=send * 0.02;
			}
			if (inMap(nowX-1, nowY)) {
				map[nowX-1][nowY] += send-useSend;
			}else {
				outSend+=send-useSend;
			}
		}
		map[nowX][nowY] = 0;
	}

	static boolean inMap(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		nowX = N / 2;
		nowY = N / 2;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		tornado();

		System.out.println(outSend);
	}
}
