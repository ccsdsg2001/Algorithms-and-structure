package search;

import java.util.ArrayList;
import java.util.List;
//建立在数组是有序的基础上.利用递归
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 40, 233, 443, 5650};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 5650);
        System.out.println(resIndex);
//        List<Integer>resIndexList=binarySearch2(arr,0,arr.length-1,40);
//        System.out.println(resIndexList);
    }


    //二分查找算法
    /*arr 数组
     * left 左边的索引
     * right 右边的索引
     * findval 要查找的值
     * return 找到就返回下标,没有找到就返回-1*/
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right,说明递归整个数组,但没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    //查找重复的数据
    //思路分析:1.在找到mid索引值,不要马上返回
    //2.向mid索引值的左边扫描,将所有满足条件元素的下标加入到集合arraylist
    //3.向mid索引值的右边扫描,将所有满足条件元素的下标加入到集合arraylist
    //4.将arraylisy返回
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left>right,说明递归整个数组,但没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            List<Integer>resIndexList= new ArrayList<Integer>();
            int temp =mid -1;
            while (true){
                if(temp<0||arr[temp] != findVal){//退出
                    break;
                }
                //否则,将temp放入到resIndexList
                resIndexList.add(temp);
                temp -= 1;//temp左移
            }
            resIndexList.add(mid);

            temp =mid +1;
            while (true){
                if(temp<0||arr[temp] != findVal){//退出
                    break;
                }
                //否则,将temp放入到resIndexList
                resIndexList.add(temp);
                temp += 1;//temp左移
            }
            return resIndexList;
        }

    }

}
