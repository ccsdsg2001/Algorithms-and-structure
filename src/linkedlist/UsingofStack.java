package linkedlist;

import java.util.Stack;

public class UsingofStack {
    public static void main(String[] args){
        Stack<String> stack =new Stack();
//        入栈
        stack.add("jacl");
        stack.add("");
        stack.add("");

//        出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//将栈顶的值取出
        }
    }
}
