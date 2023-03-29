package 과제_실습._03_29;

import java.util.*;
import java.io.*;

public class Main_17070_김하영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] arr = new int[N][N][3];
        int[][] Map = new int[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        arr[0][1][0] = 1; // 시작 위치 설정

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && (j == 0 || j == 1))
                    continue;
                /* 현재 위치로 가로 이동으로 왔을 때 */
                if (garo(i, j, Map) && arr[i][j - 1][0] > 0) { // 가로에서 옴
                    arr[i][j][0] += arr[i][j - 1][0];
                }
                if (garo(i, j, Map) && arr[i][j - 1][2] > 0) { // 대각선에서 옴
                    arr[i][j][0] += arr[i][j - 1][2];
                }
                /* 현재 위치로 세로 이동으로 왔을 때 */
                if (sero(i, j, Map) && arr[i - 1][j][1] > 0) {
                    arr[i][j][1] += arr[i - 1][j][1];
                }
                if (sero(i, j, Map) && arr[i - 1][j][2] > 0) {
                    arr[i][j][1] += arr[i - 1][j][2];
                }
                /* 현재 위치로 대각선 이동으로 왔을 때 */
                if (daegak(i, j, Map) && arr[i - 1][j - 1][0] > 0) { // 가로에서 옴
                    arr[i][j][2] += arr[i - 1][j - 1][0];
                }
                if (daegak(i, j, Map) && arr[i - 1][j - 1][1] > 0) { // 가로에서 옴
                    arr[i][j][2] += arr[i - 1][j - 1][1];
                }
                if (daegak(i, j, Map) && arr[i - 1][j - 1][2] > 0) { // 가로에서 옴
                    arr[i][j][2] += arr[i - 1][j - 1][2];
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += arr[N - 1][N - 1][i];
        }
        System.out.println(result);
    }

    public static boolean garo(int X, int Y, int[][] Map) {
        if (Y - 1 < 0 || Map[X][Y] != 0)
            return false;
        else
            return true;
    }

    public static boolean sero(int X, int Y, int[][] Map) {
        if (X - 1 < 0 || Map[X][Y] != 0)
            return false;
        else
            return true;
    }

    public static boolean daegak(int X, int Y, int[][] Map) {
        if (X - 1 < 0 || Y - 1 < 0 || Map[X][Y] != 0 || Map[X - 1][Y] != 0 || Map[X][Y - 1] != 0)
            return false;
        else
            return true;
    }
}
