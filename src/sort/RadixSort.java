package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr={23,1,422,523,15};
//        radixSort(arr);
        int[] arr = new int[80000];
        for(int i =0;i<arr.length;i++){
            arr[i] =(int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        radixSort(arr);

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);
    }

    public static void radixSort(int[] arr) {

        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一位就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        //1.二维数组包含10个一维数组
        //2.为防止在放入数的时候,数据溢出,则每个一维数组.大小定义为arr.length
        //3.基数排序是使用空间换时间的算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶,存放多少数据,定义一维数组来记录每个桶每次放入的数据
        //bucketElementCounts[0] 记录的就是bucker[0]桶的放入数据的个数
        int[] buckElementCounts = new int[10];

        //使用循环将代码处理
        for(int i =0,n=1;i<maxLength;i++,n*=10){
            //依次对元素位数进行处理,第一次是个位,其次是十位等等
            //第一轮(针对元素的个位进行排序处理)
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][buckElementCounts[digitOfElement]] = arr[j];
                buckElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序依次取出数据,放入原来数组
            int index = 0;
            //遍历每一桶,并将桶中是数据,放入到原数组
            for (int k = 0; k < buckElementCounts.length; k++) {
                //如果桶中有数据,才放入到原数组
                if (buckElementCounts[k] != 0) {
                    //循环该桶即第k个桶(第k个一维数组),放入
                    for (int l = 0; l < buckElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第一轮处理后,将每个bucketElementCounts[k]=0
                buckElementCounts[k] = 0;
            }
//            System.out.println(Arrays.toString(arr));

        }
/*
        //第一轮(针对元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素个位的值
            int digitOfElement = arr[j] / 1 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][buckElementCounts[digitOfElement]] = arr[j];
            buckElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序依次取出数据,放入原来数组
        int index = 0;
        //遍历每一桶,并将桶中是数据,放入到原数组
        for (int k = 0; k < buckElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组
            if (buckElementCounts[k] != 0) {
                //循环该桶即第k个桶(第k个一维数组),放入
                for (int l = 0; l < buckElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮处理后,将每个bucketElementCounts[k]=0
            buckElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));

        //第一轮(针对元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素个位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][buckElementCounts[digitOfElement]] = arr[j];
            buckElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序依次取出数据,放入原来数组
        index = 0;
        //遍历每一桶,并将桶中是数据,放入到原数组
        for (int k = 0; k < buckElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组
            if (buckElementCounts[k] != 0) {
                //循环该桶即第k个桶(第k个一维数组),放入
                for (int l = 0; l < buckElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮处理后,将每个bucketElementCounts[k]=0
            buckElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));

        //第一轮(针对元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素个位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][buckElementCounts[digitOfElement]] = arr[j];
            buckElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序依次取出数据,放入原来数组
        index = 0;
        //遍历每一桶,并将桶中是数据,放入到原数组
        for (int k = 0; k < buckElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组
            if (buckElementCounts[k] != 0) {
                //循环该桶即第k个桶(第k个一维数组),放入
                for (int l = 0; l < buckElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮处理后,将每个bucketElementCounts[k]=0
            buckElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr)); */



    }
}