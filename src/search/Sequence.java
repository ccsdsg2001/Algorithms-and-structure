package search;

public class Sequence {
    public static void main(String[] args) {
        int[] arr={1,9,11,-1,34,56};//没有顺序的数组
        int index=seqSearch(arr,-11);
        if(index ==-1){
            System.out.println("没有找到");
        }else {
            System.out.println(index);
        }
    }
    //线性查找 找到一个满足的值,就返回
    public static int seqSearch(int[] arr,int value){
        //线性查找是逐一对比,发现有相同值,就返回下标
        for(int i =0;i<arr.length;i++){
            if(arr[i] ==value){
                return i;
            }
        }
        return -1;
    }
}
