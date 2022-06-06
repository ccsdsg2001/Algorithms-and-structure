package SortTest;

import org.junit.Test;

/**
 * @author cc
 * @date 2022年05月31日 20:39
 */
public class Sort {



    @Test //
    void bubbleSort(int[] arr) {
        int temp =0;
        boolean flag= false;
        for(int i =0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    temp =arr[j];
                    flag=true;
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if(!flag){
                break;
            }else {
                flag=true;
            }
        }
    }

    @Test
    void SelectSort(int[] arr) {
            for(int i =0;i<arr.length-1;i++){
                int minindex=i;
                int min=arr[i];
                for(int j=0;j<arr.length;j++){
                    if(min>arr[j]){
                        min=arr[j];
                        arr[minindex]=j;
                    }
                }
                if(minindex!=i){
                    arr[minindex]=arr[i];
                    arr[i]=min;

                }
            }
        }

    @Test
    void insertsort(int[] arr) {
        int insertVal = 0;
        int indexVal = 0;
        for (int i = 0; i < arr.length; i++) {
            insertVal = arr[i];
            indexVal = i - 1;
            while (insertVal >= 0 && insertVal < arr[indexVal]) {
                arr[indexVal + 1] = arr[indexVal];
                indexVal--;
            }
            if (indexVal + 1 != i) {
                arr[indexVal] = insertVal;
            }
        }


    }
}





