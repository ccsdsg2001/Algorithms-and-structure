package Stack;

import java.util.Scanner;

public class ArrayStack {
    public static void main(String[] args){
        Stack stack = new Stack(4);
        String key = "";
        boolean loop =true;
        Scanner in = new Scanner(System.in);
        while (loop){
        }

        key = in.next();
        switch (key){
            case"show":
                stack.list();
                break;
            case"push":
                int value =in.nextInt();
                stack.push(value);
                break;
            case"pop":
                try {
                    int res =stack.pop();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case"exit":
                in.close();
                loop =false;
                break;
            default:
                break;


        }

    }
}


//定义一个ArrayStack表示栈
class Stack{
    private  int maxsize;//栈的大小
    private  int[] stack;//数组,数组模拟栈,数据放在该数组
    private  int top = -1;//top表示栈顶,初始化为-1

    //构造器
    public  Stack(int maxsize){
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }

    //栈满
    public boolean isFull(){
        return top == maxsize -1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈-push
    public void push(int value){
        //先判断栈顶满
        if(isFull()){
            System.out.println("full");
            return;
        }
        top++;
        stack[top] =value;
    }
    //出栈pop,将栈顶数据返回
    public  int pop(){
        //先判断栈顶是否空
        if(isEmpty()){
            throw new RuntimeException("empty");
        }
        int value = stack[top];
        top --;
        return value;
    }

    //显示栈的情况[遍历栈],遍历时,从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("empty");
            return;
        }
        //需要从栈顶显示数据
        for(int i =top;i >=0;i--){
            System.out.println(i);
            System.out.println(stack[i]);

        }
    }
}