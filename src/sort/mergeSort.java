package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mergeSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];

        for(int i = 0; i < 8000000; ++i) {
            arr[i] = (int)(Math.random() * 8000000.0D);
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid =(left+right)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left, mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left, mid,right,temp);

        }
    }




    //合并的方法
    /*arr 排序的原始数组
     * left 左边有序序列的初始索引
     * mid 中间索引
     * right 右边索引
     * temp 做中转的数组*/
    public static void merge(int[] arr,int left,int mid,int right,int[] temp) {
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //先把左右两边有序的数据按照填充到temp数组，直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素,小于等于右边有序序列的当前元素,将左边的当前元素填充到temp数组,然后t++,i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                ++t;
                ++i;
            } else {//反之,将右边有序序列的当前元素,填充temp数组
                temp[t] = arr[j];
                ++t;
                ++j;
            }
        }

        //把有剩余数据的一边的数据依次填充到temp
        while (i<= mid) {//左边 的有序序列还有剩余的元素,全部填充到temp
            temp[t] = arr[i];
            ++t;
            ++i;
        }

        while (j<= right){//右边的有序序列还有剩余的元素填充到temp
            temp[t] = arr[j];
            ++t;
            ++j;
        }

        //将temp数组的元素拷贝到arr.并不是每次都拷贝所有
        t = 0;
        //第一次合并 tempLeft =0,right =1//tempLeft =2 right =3//tL = 0 ri =3
        //最后依次tempLeft =0 right =7
        for(int tempLeft = left; tempLeft <= right; ++tempLeft) {
            arr[tempLeft] = temp[t];
            ++t;
        }
    }

}
