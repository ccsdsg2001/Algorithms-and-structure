package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args){
        //完成一个中缀表达式转变成后缀表达式的功能
//        1.1+(2+3)*4)-5 > 转成1 2 3 +4 * + 5 -.2.因为对str进行操作,先将1+(2+3)*4)-5中缀表达式对应的List.3.将得到中缀表达式对应的List>后缀对应的List
        String expression ="1+(2+3)*4)-5";//注意表达式
        List<String>infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String>suffixExpreesionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpreesionList);
        System.out.println(calclulate(suffixExpreesionList));
        //先定义给逆波兰表达式
        //(30 + 4 ) *5-6 = 30 4 + 5 * 6 - = 165
        //4 * 5 - 5 +60 +8 /2 =4 5 * 8 - 60 + 8 2 /+
        //说明方便.逆波兰表达式打的数字和符号使用空格隔开
        //String suffixExpreesion = "30 4 + 5 * 6 -"
        String  suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
        //思路:1先将"3 4 + 5 * 6 -"放入到ArrayList中 2.将ArrayList传递给一个方法,遍历ArrayList配合栈完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        int res = calclulate(list);
        System.out.println(res);
    }


    //方法:将得到的中缀表达式对应的List > 后缀表达式对应的List
    public static List<String>parseSuffixExpressionList(List<String>ls){
        //定义两个栈
        Stack<String>s1 = new Stack<String>();//符号栈
        //由于s2栈在整个转换过程中没有pop操作,后面还需要逆序输出.所以不使用Stack<String>而使用List<String>s2
        //Stack<String> s2 =new Stack<String>();存储在中间结果的栈s2
        List<String> s2 =new ArrayList<String>();//存储在中间结果的Lists2

        //遍历ls
        for(String item:ls){
            //如果是一个数,加入S2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号")",则依次弹出s1栈顶的运算符,并压入s2,直到遇到左括号为止,此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将(弹出s1栈,消除小括号
            }else{
                //当item的优先级小于s1栈顶运算符,将s1栈顶运算符弹出并加入s2中,再次转到(4.1)与s1中新的栈顶运算符进行比较
                while (s1.size()!= 0 && Operation.getValue(s1.peek())>= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余运算符依次弹出加入s2
        while(s1.size()!= 0){
            s2.add(s1.pop());
        }
        return s2;//存放到list,按顺序输出对应的后缀表达式对应List

    }

    //方法:将中缀表达式转成对应List
    // s ="1+((2+3)*4)-5"
    public static List<String>toInfixExpressionList(String s){
        //定义一个List,存放中缀表达式对应的内容
        List<String>ls = new ArrayList<String>();
        int i =0;//一个指针,遍历中缀表达式字符串
        String srt;//对多位数的拼接
        char c;//每遍历到一个字符 ,就放入到c
        do{
            //如果c是一个非数字,加入到ls
            if((c =s.charAt(i))< 48 || (c=s.charAt(i))>57){
                ls.add(""+c);
                i++;//i需要后移
            }else {//如果是一个数,则需要考虑多位数
                srt= "";//x先将str置成"0[48]>9[57]"
                while (i<s.length() && (c= s.charAt(i))>= 48 && (c = s.charAt(i)) <= 57){
                    srt += c;//拼接
                    i++;
                }
                ls.add(srt);
            }
        }
        while (i <s.length());
            return ls;
    }

    //将一个逆波兰表达式,依次将数据和运算符放入到ArrayList中
    public static List<String>getListString(String suffixExpression){
        //将suffixExpression分割
        String[] spilt =suffixExpression.split(" ");
        List<String> list =new ArrayList<String>();
        for(String ele:spilt){
            list.add(ele);
        }
        return list;
    }


    //完成对逆波兰表达式的运算
    /*1 从左到右扫描，将3和4压入堆栈
    2 遇到+运算符，弹出4和3，计算出3+4的值，得7，再将7入栈
    3 将5入栈
    4 接下来*运算符，弹出5和7，计算出7*5=35，将35入栈
    5 将6入栈
    6 最后-运算符，计算出35-6值，得出29最终结果*/
    public static int calclulate(List<String>ls){
        //创建给栈,只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for(String item:ls){
            //使用正则表达式来取出数
            if(item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }
            else {
                //pop出两个数,并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res  =num1/num2;
                }else{
                    throw new RuntimeException("false");
                }
                //把res入栈
                stack.push(""+res);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation可返回一个运算符对应的优先级
class Operation{
    private static int ADD =1;
    private static int SUB =1;
    private static int MUL =2;
    private static int DIV= 2;

    //写一个方法,返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result =ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case"/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}





