package Stack;

import com.sun.jdi.CharType;

import java.nio.channels.Pipe;

public class CalculatorofStack {
    public static void main(String[] args){
        String expression = "7*2*2-5+1-5";
        //创建两个栈,数栈,一个符号栈
        Stack2 numStack = new Stack2(10);
        Stack2 operStack = new Stack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1  = 0;
        int num2 = 0;
        int oper = 0;
        int res =0;
        char ch = ' ';//将每次扫描得到char保存到ch
        String keepnum ="";//用于拼接多位数
        ///开始while循环的扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么,然后做相应的处理
            if(operStack.isOper(ch)){
                //如果是运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果符号栈有操作符,就进行比较,如果当前的操作符优先级小于,就需要从数栈中pop出两个数
                    //在从符号栈中pop出一个符号,进行运算,得到结果,入数栈,然后将当前符号入符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res =numStack.cal(num1, num2, oper);
                        //吧运算结果如数栈
                        numStack.push(res);
                        //将当前的操作符号入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符优先级大于栈中的操作符,则直接入符号栈
                        operStack.push(ch);
                    }
                    }
                else{
                    //如果为空直接入符号栈
                    operStack.push(ch);//1+3
                }
            }
            else{
                //如果是数,则直接入数栈
//                numStack.push(ch-48);//?"1+3"'1' =>1
                /*分析思路
                * 1.当处理多位数时,不能发现时一个数就立即入栈,可能是多位数
                * 2.在处理数,需要向erpression的表达式的index后再看一位,如果是数就进行扫描
                * 3.需要定义一个变量,用于拼接*/
                //处理多位数
                keepnum +=ch;
                //如果ch是expression的最后一位,就直接入栈
                if(index == expression.length() -1){
                    numStack.push(Integer.parseInt(keepnum));
                }else {
                    //判断下一个字符是不是数字,如果是数字,就继续扫描.如果是运算符,则入栈
                    //注意看最后一位,不是index++
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符,则入栈keepnum = "1" 或者"123"
                        numStack.push(Integer.parseInt(keepnum));
                        //keepnuM清空
                        keepnum = "";
                    }
                }
            }

            //让index+1,判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号,并运行
        while (true){
            //如果符号栈为空,则计算到最后的结果,数栈中只有一个数字[结果]
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res  =numStack.cal(num1, num2, oper);
            numStack.push(res);//入栈
        }
        //将数栈的最后数,pop出,就是结果
        int res2 =numStack.pop();
        System.out.println(res2);

    }


}

class Stack2{
    private  int maxsize;//栈的大小
    private  int[] stack;//数组,数组模拟栈,数据放在该数组
    private  int top = -1;//top表示栈顶,初始化为-1

    //构造器
    public  Stack2(int maxsize){
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }
    //增加一个方法,返回当前栈顶的值,但不是真正的pop
    public int peek(){
        return stack[top];
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

    //返回运算符的优先级,优先级由程序员来确定,优先级使用数字来表示
    //数字越大,优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/') {
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;//假定目前表达式只有+,-,*,/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val =='-'||val == '*' || val == '/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res =0;//res用于存放计算的结果
        switch (oper){
            case'+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res =num1 * num2;
                break;
            case'/':
                res = num2 /num1;
                break;
            default:
                break;
        }
        return res;

    }































}