package SortTest;

import org.junit.Test;
import sort.QuickSort;

import javax.swing.*;

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
      int insertValue=0;
      int indexVal=0;
      for(int i=0;i<arr.length;i++){
          insertValue=arr[i];
          indexVal=i-1;
          while (insertValue>=0&&insertValue<arr[indexVal]){
              arr[indexVal+1]=arr[indexVal];
              indexVal--;
          }
          if(indexVal+1!=i){
              arr[indexVal]=insertValue;
          }
      }
    }

    @Test
    void shellSort(int[] arr){
        int temp=0;
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
        }
}

    @Test
    void shellsort2(int[] arr) {
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int j=i;
                int temp=arr[i];
                if(arr[j]>arr[j-gap]){
                    while (j-gap>=0&&temp<arr[j-gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=temp;
                }
            }
        }
    }

    @Test
    void quickSort(int[] arr,int left,int right) {
        int l=left;
        int r=right;
        int pivot=arr[(right+left)/2];
        int temp=0;
        while (l<r){
            while (arr[l]<pivot){
                l+=1;
            }
            while (arr[r]>pivot){
                r-=1;
            }
            if(l>=r){
                break;
            }
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            if(arr[l]==pivot){
                r-=1;
            }
            if(arr[r]==pivot){
                l+=1;
            }
        }

        if(l==r){
            l+=1;
            r-=1;
        }
        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}






