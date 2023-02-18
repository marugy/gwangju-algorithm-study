/*
 * https://www.acmicpc.net/problem/
 * 백준 1339번, 단어수학 
 */
import java.util.*;
import java.io.*;


public class BJ_1339_단어수학_김하영{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] in_Str = new String[N];
        int [][] arr = new int['Z'-'A'+1][2];
        HashMap <Character, Integer> dict = new HashMap<>();

        for(int i =0; i< N; i++){
            in_Str[i] = br.readLine();
        }
        Arrays.sort(in_Str, (a,b) -> b.length()-a.length());
        int len = in_Str[0].length();
        for(int i =0; i < 'Z'-'A'+1; i++){
            arr[i][1] = i+'A';
        }

        for(int i =0; i< N; i++){
            int value = 1;
            for(int j = in_Str[i].length()-1; j>=0; j--){
                arr[in_Str[i].charAt(j)-'A'][0] += value;
                value*=10;
            }
        }

        Arrays.sort(arr, (o1,o2)->o2[0]-o1[0]);
        int Start = 9;
        for(int i =0; i< arr.length; i++){
            if(arr[i][0] ==0) break;
            dict.put((char)arr[i][1], Start);
            Start--;
        }
        int result = 0;
        for(int i =0; i< N; i++){
            int temp = 0;
            for(int j = 0; j <  in_Str[i].length(); j++){
                temp*=10;
                temp += dict.get(in_Str[i].charAt(j));
                
            }
            result+=temp;
        }
        System.out.println(result);
    }
}