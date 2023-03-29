// package 과제_실습._03_29;
package _03_29;

import java.util.*;
import java.io.*;

public class Main_1600_김하영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] Map = new int[H][W][2];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                Map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(Find(Map, K, H, W));
        return;
    }

    static int Find(int[][][] Map, int K, int H, int W) {
        int[] h_dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
        int[] h_dy = { -2, -1, 1, 2, 2, 1, -1, -2 };

        int[] m_dx = { 0, 1, 0, -1 };
        int[] m_dy = { -1, 0, 1, 0 };

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] { 0, 0, 0, K });

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int X = temp[0];
            int Y = temp[1];
            int depth = temp[2];
            int uK = temp[3];
            if (X == H - 1 && Y == W - 1) {
                System.out.println(Arrays.deepToString(Map));
                return depth;
            }
            if (uK > 0) {
                for (int i = 0; i < 8; i++) {
                    int new_x = X + h_dx[i];
                    int new_y = Y + h_dy[i];
                    if (new_x < 0 || new_x >= H || new_y < 0 || new_y >= W) {
                        continue;
                    }

                    if (Map[new_x][new_y][0] > 0) {
                        if (Map[new_x][new_y][1] < uK) {

                        } else {
                            continue;
                        }
                    }
                    queue.add(new int[] { new_x, new_y, depth + 1, uK - 1 });
                    Map[new_x][new_y][0] = depth;
                    Map[new_x][new_y][1] = uK - 1;

                }
            }
            for (int i = 0; i < 4; i++) {
                int new_x = X + m_dx[i];
                int new_y = Y + m_dy[i];
                if (new_x < 0 || new_x >= H || new_y < 0 || new_y >= W) {
                    continue;
                }

                if (Map[new_x][new_y][0] > 0) {
                    if (Map[new_x][new_y][1] < uK) {

                    } else {
                        continue;
                    }
                }
                queue.add(new int[] { new_x, new_y, depth + 1, uK });
                Map[new_x][new_y][0] = depth;
                Map[new_x][new_y][1] = uK - 1;
            }
        }

        return -1;
    }
}
