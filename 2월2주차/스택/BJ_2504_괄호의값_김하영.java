package Study;
/*
 * https://www.acmicpc.net/problem/2504
 * 백준 2504번 괄호의 값, 
 */
import java.util.*;
import java.io.*;

public class BJ_2504_괄호의값_김하영{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in_str = br.readLine();
        HashMap <Character, Character> dict =new HashMap<>();
        dict.put(')', '(');
        dict.put(']', '[');
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int mul = 1;
        boolean check = false; 
        for(int i = 0; i< in_str.length(); i++){
            char c = in_str.charAt(i);
            if(c == '[' ){
                mul *= 3;
                stack.push(c);
                check = true; 
            }else if(c == '('){
                mul *= 2;
                stack.push(c);
                check = true; 
            }else if(c == ']'){
                
                if(stack.empty() || !stack.pop().equals(dict.get(c))){
                    System.out.println(0);
                    return;
                }
                if(check)result+=mul;
                mul /= 3;
                check = false;
            }
            else if(c == ')'){
                if(stack.empty()  || !stack.pop().equals(dict.get(c))){
                    System.out.println(0);
                    return;
                }
                if(check)result+=mul;
                mul /= 2;
                check = false;
            }
        }
        if(!stack.empty() )System.out.println(0);
        else System.out.println(result);
    }

}