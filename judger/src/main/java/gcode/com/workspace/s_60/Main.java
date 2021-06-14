package gcode.com.workspace.s_60;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(System.in);

//        String s=  in.nextLine();
        String s = args[0];
//        System.out.println(s);
        HashMap<Character, Integer> map = new HashMap();
        map.put('(',-1);
        map.put(')',1);
        map.put('[',-2);
        map.put(']',2);
        map.put('{',-3);
        map.put('}',3);
        Stack<Integer> stack = new Stack();
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if(stack.isEmpty())
                stack.push(map.get(cur));
            else if(map.get(cur) < 0)
                stack.push(map.get(cur));
            else{
                if(stack.peek() == -map.get(cur))
                    stack.pop();
                else stack.push(map.get(cur));
            }
        }

        System.out.println(stack.isEmpty());
    }
}