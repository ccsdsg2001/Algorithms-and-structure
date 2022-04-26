package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr ={-3,-58,2,33,424,12};
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 800000);
//        }//生成数
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        quickSort(arr,0,arr.length-1);//
        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);
    }
    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r= right;//右下标
        //pivot中轴值
        int piviot =arr[(left+right)/2];
        int temp = 0;//临时变量作交换时使用
        //while循环目的是让比pivot值小的放到左边,比pivot值大的放到右边
        while (l<r){
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l]<piviot){
                l+=1;
            }
            //在pivot右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > piviot){
                r -=1;
            }
            //如果l>=r说明pivot左右两的值,已经按照左边全部是小于等于pivot值,右边全部大于等于pivot值
            if (l>=r){
                break;
            }
            //交换
            temp =arr[l];
            arr[l] =arr[r];
            arr[r] = temp;

            //如果交换完后,发现这个arr[l]==pivot值 相等r-- 前移
            if(arr[l] == piviot){
                r-=1;
            }
            //如果交换完后,发现这个arr[r] == pivot值,相等l++ 后移
            if(arr[r] ==piviot){
                l+=1;
            }
        }

        //如果l==r,必须l++,r-- 否则为栈溢出
        if(l ==r){
            l+=1;
            r-=1;
        }

        //向左递归
        if(left <r){
            quickSort(arr,left, r);
        }

        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}
