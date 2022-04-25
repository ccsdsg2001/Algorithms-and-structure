package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 39, 49, 142,-1,35,20};
        int[] arr = new int[80000];
        for(int i =0;i<arr.length;i++){
            arr[i] =(int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        Sort(arr);//测试冒泡排序

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);

//        System.out.println(Arrays.toString(arr));


    }

    public static void Sort(int[] arr) {
        //用for来解决,选择排序的时间复杂度是O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minindex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的最小值,并不是最小
                    min = arr[j];//重置min
                    minindex = j;//重置minindex
                }
            }
            //将最小值,放在arr[0],即交换
            if (minindex != i) {
                arr[minindex] = arr[i];
                arr[i] = min;
            }

//            System.out.println(Arrays.toString(arr));
        }
    }
}

        //算法先简单>后做复杂,把一个复杂算法拆分成简单的问题>逐步解决
//        int minindex = 0;
//        int min = arr[0];
//        for(int j=0 + 1;j<arr.length; j++){
//            if(min > arr[j]){//说明假定的最小值,并不是最小
//                min =arr[j];//重置min
//                minindex =j;//重置minindex
//        }
//    }
//        //将最小值,放在arr[0],即交换
//        if(minindex !=0){
//            arr[minindex]=arr[0];
//            arr[0] =min;
//        }
//
//        System.out.println(Arrays.toString(arr));
//
//        //第二轮
//        minindex = 1;
//        min = 1;
//        for(int j=1 + 1;j<arr.length; j++){
//            if(min > arr[j]){//说明假定的最小值,并不是最小
//                min =arr[j];//重置min
//                minindex =j;//重置minindex
//            }
//        }
//        //将最小值,放在arr[0],即交换
//        if(minindex !=1){
//            arr[minindex]=arr[1];
//            arr[1] =min;
//        }
//
//        System.out.println(Arrays.toString(arr));







