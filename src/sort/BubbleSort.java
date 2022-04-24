package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        //测试冒泡排序速度
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }//生成数

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        bubbleSort(arr);//测试冒泡排序

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);
    }



        //
//        int[] arr = {3, 9, -1, 10, 20};
//        int temp = 0;
//        for (int j = 0; j < arr.length - 1; j++) {
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        for (int j = 0; j < arr.length - 2; j++) {
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        for (int j = 0; j < arr.length - 3; j++) {
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        for (int j = 0; j < arr.length - 4; j++) {
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
//    }

        public static void bubbleSort(int[] arr){
            //冒泡排序时间复杂度O(n^2)
            int temp = 0;//临时变量
            boolean flag = false;//标识变量，表示进行过交换
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    //如果前面数比后面数大，则交换
                    if (arr[j] > arr[j + 1]) {
                        flag = true;
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }

                if (!flag) {//在一趟排序中一次交换没有发生过
                    break;
                } else {
                    flag = false;//重置flag,进行下次判断
                }
            }
        }
    }



