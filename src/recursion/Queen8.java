package recursion;

import java.io.PrintWriter;

public class Queen8 {
//定义一个max表示有多少个皇后
    int max =8;
    //定义数组中array,保存皇后放置位置的结果
    int[] array=new int[max];
    static int count =0;
    static int judgecount=0;
    public static void main(String[] args){
        Queen8 queen8 =new Queen8();
        queen8.check(0);
        System.out.println(count);
    }



    //编写一个方法,放置第n个皇后
    //check是每一次递归时,进入到check中都有for(int i =0;i<max;i++)所以会有回溯
    private void check(int n) {
        if (n == max) {//n=8,8个皇后放好
            print();
            return;
        }

        //依次放入皇后,判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时,是否冲突
            if (judge(n)) {//不冲突
                //接着放n+1个皇后,开始递归
                check(n + 1);
            }
            //如果冲突,继续执行array[n] =i;第n个皇后,放置在本行后移的一个位置
        }
    }


        //查看我们去放置第n个皇后,就去检测皇后是否和前面已经摆放的皇后是否已经冲突
        /*
         * n表示第n个皇后*/
        private boolean judge(int n){
            judgecount++;
            for (int i = 0; i < n; i++) {
                /*1.array[i]=array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
                 * 2.Math.abs(n-i) ==Math.abs(array[n] -array[i])表示判断第n个皇后是否和第i皇后是否在同一斜线
                 * n=1 放置第二列 n=1 array[1] =1
                 * Math.abs(1-) ==1 Math.abs(array[n] -array[i])=Math.abs(1-0) =1
                 * 3.判断是否在同一行,n每次在递增*/
                if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                    return false;
                }
            }
            return true;
        }

        //输出皇后摆放位置
        private void print () {
            count++;
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
            }
            System.out.println();
        }
    }



