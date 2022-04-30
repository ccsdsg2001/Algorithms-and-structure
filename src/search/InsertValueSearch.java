package search;

public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr ={1,4,5,67,123,322};
        int index = insertValueSearch(arr,0,arr.length-1,4);
        System.out.println(index);
    }

    /*arr 数组
    * left 左边索引
    * right 右边索引
    * findval 查找值
    * return 如找到,返回对应下标,没有找到,返回-1*/
    //编写插值查找算法.算法也是要求数组为有序
    public static int insertValueSearch(int[] arr,int left,int right,int findval){
        //注意findVal<arr[0]和findVal >arr[arr.length-1]必须需要,否则得到的mid可能越界
        if(left > right||findval <arr[0] || findval > arr[arr.length -1]){
            return -1;
        }
        //求出mid,自适应
        int mid =left + (right - left)*(findval - arr[left]) / (arr[right] -arr[left]);
        int midVal = arr[mid];
        if(findval > midVal){//说明向右递归
            return insertValueSearch(arr,mid+1,right,findval);
        }else if(findval < midVal){//说明向左递归
            return insertValueSearch(arr,mid-1,right,findval);
        }else{
            return mid;
        }
    }
}
