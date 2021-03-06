package search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxsize = 20;

    public static void main(String[] args) {
        int [] arr={1,2,4,56,232,434};
        System.out.println(fibSearch(arr,4));
    }


    //获取到一个斐波那契数列,采用非递归方法
    public static int[] fib() {
        int[] f = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxsize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法,采用非递归的方法.
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表达斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于a的长度,需要arrays类,构造一个新的数组,并指向temp[],不足的部分使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需求使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while来循环处理,找到我们的数key
        while (low <= high) {//只要这个条件满足,就可以找
            mid = low + f[k - 1] + 1;
            if (key < temp[mid]) {//向数组的前面查找(左边)
                high = mid - 1;
                k--;//为什么是k--,全部元素=前面的元素+后边元素. 2.f[k] = f[k-1] +f[k-2]因为前面有f[k-1]个元素,所以可以继续拆分f[k-1] =f[k-2]+f[k-3],在f[k-1]前面继续查找k--,下次循环mid=f[k-1-1]-1
            } else if (key > temp[mid]) {//向数组的后面查找右边
                low = mid + 1;
                k -= 2;//为什么是k-=2;f[k] =f[k-1]+f[k-2] 因为后面有f[k-2] 所以继续拆分 f[k-1] =f[k-3] +f[k-4] 在f[k-2]的前面继续查找k-=2.下次循环mid=f[k-1-2]-1
            } else {//找到
                //需要确定,返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
