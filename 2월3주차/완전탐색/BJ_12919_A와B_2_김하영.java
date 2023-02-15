
/*
 * https://www.acmicpc.net/problem/12919
 * 백준 12919번, A와 B 2 
 */
import java.util.*;
import java.io.*;

public class BJ_12919_A와B2_김하영{
    public static int C_A, C_B, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();
        
        Check(S, T);
        System.out.println(result);

    }
    public static String AddA(String in_str){
        return in_str.substring(0,in_str.length()-1);
    }
    public static String AddB(String in_str){
        StringBuffer sb = new StringBuffer(in_str.substring(1));
        return sb.reverse().toString();
    }
    public static void Check(String S, String toChange){
        if(toChange.length() == S.length()){
            if(toChange.equals(S))result=1;
            return;
        }
        if(toChange.charAt(toChange.length()-1) == 'A'){
            Check(S, AddA(toChange));
        }
        if(toChange.charAt(0) == 'B'){
            Check(S, AddB(toChange));
        }

    }
}