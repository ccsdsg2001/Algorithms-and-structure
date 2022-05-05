package tree;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for(int i =0;i<arr.length;i++){
            arr[i] =(int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        String date1 = simpleDateFormat.format(date);
        System.out.println(date1);

        heapSort(arr);//测试冒泡排序

        Date date2 = new Date();
        String date13 = simpleDateFormat.format(date2);
        System.out.println(date13);

    }


    //编写一个堆排序的方法
    public static void heapSort(int[] arr){
        int temp=0;

        //将无序代码构建成一个堆,根据升序降序需求选择大顶堆或小顶堆
        for(int i = arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        //将栈顶元素与末尾元素交换,将最大元素沉到数组末端
        //重新调整结构,使其满足堆定义,继续交换堆顶元素与当前末尾元素,反复执行调整+交换步骤,直到整个序列有序
        for(int j =arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j] =arr[0];
            arr[0] =temp;
            adjustHeap(arr,0,j);
        }
    }
    //将一个数组(二叉树),调整成一个大顶堆
    //功能:完成将以i对应的非叶子结点的树调整成大顶堆 arr待调整的数组 i表示非叶子结点在数组中索引 length 表示对多少个元素继续调整,length逐渐减少
    public static void adjustHeap(int[] arr,int i,int length) {
        int temp = arr[i];//先取出当前元素值,保存在临时变量
        //k=i*2 +1 k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子结点的值小于右子结点的值
                k++;//k指向右子结点
            }
            if (arr[k] > temp) {//如果左子结点大于父结点
                arr[i] = arr[k];//把较大的值赋给当前结点
                i = k;//i指向k,k继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后,将i为父节点的树的最大值,放在了最顶部
        arr[i] = temp;//将temp值放到调整后的位置
    }
    }
