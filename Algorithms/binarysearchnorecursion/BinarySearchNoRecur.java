package binarysearchnorecursion;

/**
 * @author cc
 * @date 2022年05月17日 18:41
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int arr[] ={1,3,4,5,6,13,23,44,53,63,131};
        int index =binarysearch(arr,23);
        System.out.println(index);
    }

    //二分查找非递归实现
    /**
     * @Author cc
     * @Description //TODO
     * @Date 18:44 2022/5/17
 * @param arr 待查找的数组,arr是升序降序
 * @param target 需要查找的数
 * @return int 返回对应下标,-1表示没有找到
     **/

    public static int binarysearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){//说明继续查找
            int mid =(left+right)/2;
            if(arr[mid] ==target){
                return  mid;
            }else if(arr[mid]>target){
                right=mid-1;//需要项左边查找
            }else {
                left=mid+1;//需要项右边查找
            }
        }
        return -1;
    }
}
