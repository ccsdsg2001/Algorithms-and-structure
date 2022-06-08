package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class shellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);
        int[] arr =new int[80];

        for(int i =0;i<arr.length;i++){
            arr[i] =(int) (Math.random()*100);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        shellsort2(arr);//测试冒泡排序

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        //循环处理
        for (int gap = arr.length/2;gap>0;gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共gap组,每组有个元素),步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素,说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
    //进行优化==>位移法
    public static void shellsort2(int[] arr){
        //增量gap,并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素,逐个对其所在的组进行直接插入排序
            for(int i=gap;i<arr.length;i++){
                int j =i;
                int temp = arr[j];
                if(arr[j] > arr[j-gap]){
                    while (j-gap >=0 && temp <arr[j-gap]){
                        //移动
                        arr[j] =arr[j-gap];
                        j-=gap;
                    }
                    //当退出while之后,就给temp找到插入的位置
                    arr[j] =temp;
                }
            }
        }
    }
}
//            System.out.println(Arrays.toString(arr));
        /*
        //第一轮排序,将十个数据分成了5组
        for(int i =5;i<arr.length;i++){
            //遍历各组中所有的元素,步长5
            for(int j  =i-5;i>= 0;j-= 5){
                //如果当前元素大于步长后的那个元素,说明交换
                if(arr[j] > arr[j+5]){
                    temp = arr[j];
                    arr[j] =arr[j+5];
                    arr[j+5] =temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //第2轮排序,将5个数据分成了2组
        for(int i =2;i<arr.length;i++){
            //遍历各组中所有的元素,步长5
            for(int j  =i-2;i>= 0;j-= 2){
                //如果当前元素大于步长后的那个元素,说明交换
                if(arr[j] > arr[j+2]){
                    temp = arr[j];
                    arr[j] =arr[j+2];
                    arr[j+2] =temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        //第3轮排序,将2个数据分成了1组
        for(int i =1;i<arr.length;i++){
            //遍历各组中所有的元素,步长5
            for(int j  =i-1;i>= 0;j-= 1){
                //如果当前元素大于步长后的那个元素,说明交换
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] =arr[j+1];
                    arr[j+1] =temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    } */