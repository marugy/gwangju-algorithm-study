package 과제_실습._03_29;

import java.util.*;
import java.io.*;

public class Main_1010_김하영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = 0, M = 0;
        StringTokenizer st = null;
        for (int i = 0; i < T; i++) { // 조합을 이용해서 해결하면 됌
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int[][] arr = new int[M + 1][N + 1];
            /* 오늘 배운 조합 구하기 식을 통해 해결해보기 */
            for (int j = 0; j <= M; j++) {
                for (int k = 0, end = Math.min(j, N); k <= end; k++) {
                    if (k == 0 || k == j)
                        arr[j][k] = 1;
                    else
                        arr[j][k] = arr[j - 1][k - 1] + arr[j - 1][k];
                }
            }
            System.out.println(arr[M][N]);
        }
    }
}
