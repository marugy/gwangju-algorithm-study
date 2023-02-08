/*
 * https://www.acmicpc.net/problem/10974
 * 백준 10974번, 모든 순열
 */
import java.util.*;
import java.io.*;

public class Main{
    public static int N;
    public static StringBuilder sb = new StringBuilder();
    public static boolean [] visited ;
    public static int [] arr;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        arr = new int[N];
        Combi(0);
    }

    public static void Combi(int len){
        if(len == N){
            for(int i = 0; i < N; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            arr[len] = i+1;
            Combi(len+1);
            visited[i] = false;
        }
    }
}