package 과제_실습._03_29;

import java.util.*;
import java.io.*;

public class Main_10971_김하영 {
    static int result = Integer.MAX_VALUE;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            Find((0 | (1 << i)), i, i, 1, 0, N);
        }
        System.out.println(result);
    }

    public static void Find(int bit, int idx, int st, int cnt, int dist, int N) {
        if (cnt == N) {
            if (arr[idx][st] == 0) { // 경로 이어져 있지 않으면 종료
                return;
            }
            dist += arr[idx][st];
            result = Math.min(result, dist);
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) != 0 || arr[idx][i] == 0) { // 경로가 안이어져 있을때
                continue;
            }
            Find((bit | (1 << i)), i, st, cnt + 1, dist + arr[idx][i], N);
        }
    }
}
