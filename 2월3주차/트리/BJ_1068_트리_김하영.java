/*
 * https://www.acmicpc.net/problem/1068
 * 백준 1068번, 트리
 */
import java.util.*;
import java.io.*;

public class BJ_1068_트리_김하영{
    public static HashMap <Integer, ArrayList<Integer>> tree;
    public static int result = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //int parent = 0;
        ArrayList <Integer> parent = new ArrayList<>();
        for(int i = 0; i< N; i++){
            tree.put(i, new ArrayList<Integer>());
        }
        for(int i = 0; i< N; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == -1){
                parent.add(i);
                continue;
            }
            tree.get(temp).add(i);
        }
        int Not = Integer.parseInt(br.readLine());
        for(int i : parent){
            Count(i, Not);
        }
        //Count(parent, Not);
        System.out.println(result);

    }
    public static void Count(int parent, int Not){
        Deque <Integer> stack = new ArrayDeque<>();
        stack.addLast(parent);
        while(!stack.isEmpty()){
            int cur = stack.pollLast();
            if(cur == Not)continue;
            if(tree.get(cur).size() == 0){
                result++;
            }else if(tree.get(cur).size()==1 && (tree.get(cur).get(0)==Not)){
                result++;
            }
            else{
                for(int i: tree.get(cur)){
                    stack.add(i);
                }
            }
            
            
        }
    }
}