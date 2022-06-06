package sort;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr =new int[80];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }//生成数
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        insersort(arr);//测试冒泡排序

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);
    }


    //插入排序
    public static void insersort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;//即arr[1]的前面这个数的下标
        //使用for循环将代码简化
        for (int i = 1; i < arr.length; i++) {
            //使用逐步推导的方式来讲解,便利理解
            //第一轮 {101,34,119,1} ==> {34,101,119,1}
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertval找到插入的位置
            //说明 1.insertIndex >= 0 保证在给insertval找插入位置,不越界.2.insertval<arr[insertIndex]待插入的数,还没有找到插入位置
            //3.将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时,说明插入的位置找到,insertIndex+1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;

            }
//            System.out.println(Arrays.toString(arr));
        /*
        //使用逐步推导的方式来讲解,便利理解
        //第一轮 {101,34,119,1} ==> {34,101,119,1}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1-1;//即arr[1]的前面这个数的下标

        //给insertval找到插入的位置
        //说明 1.insertIndex >= 0 保证在给insertval找插入位置,不越界.2.insertval<arr[insertIndex]待插入的数,还没有找到插入位置
        //3.将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal <arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex +1]=insertVal;
        System.out.println(Arrays.toString(arr));



        //第二轮.
        insertVal = arr[2];
        insertIndex = 2-1;//即arr[1]的前面这个数的下标

        //给insertval找到插入的位置
        //说明 1.insertIndex >= 0 保证在给insertval找插入位置,不越界.2.insertval<arr[insertIndex]待插入的数,还没有找到插入位置
        //3.将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal <arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex +1]=insertVal;
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 3-1;//即arr[1]的前面这个数的下标

        //给insertval找到插入的位置
        //说明 1.insertIndex >= 0 保证在给insertval找插入位置,不越界.2.insertval<arr[insertIndex]待插入的数,还没有找到插入位置
        //3.将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal <arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex +1]=insertVal;
        System.out.println(Arrays.toString(arr));
*/
        }
    }
}
