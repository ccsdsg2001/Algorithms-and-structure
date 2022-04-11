package databasestructures;

import java.security.PublicKey;
import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args){
        queue que =new queue(3);
        char key =' ';
        Scanner in =new Scanner(System.in);
        boolean loop = true;
//        测试过程略


    }
}

class queue {
    private int maxsize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列

//  构造函数
    public queue(int arrMaxsize) {
        maxsize = arrMaxsize;
        arr = new int[maxsize];
        front = -1;//指向队列头部，分析出front是队列头前一个位置
        rear = -1;//指向队列尾，指向队列尾的数据
    }

    //    判断是否满
    public boolean isFull() {
        return rear == maxsize - 1;
    }

//    判断队列是否为空
    public boolean isEmpty(){
        return rear ==front;
    }

//    添加数据到队列
    public void addQueue(int n){
//        判断队列是否满
        if(isFull()){
            System.out.println("man");
            return;
        }
        rear ++;//让队列后移
        arr[rear] =n;
    }

//    获取队列的数据,出队列
    public int getQueue(){
//        判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列空");
        }
        front++;
        return arr[front];
    }

//    显示队列的所有数据
    public void showQueue(){
//        遍历
        if(isEmpty()){
            System.out.println("队列空");
            return;
        }
        for (int i =0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

//    显示队列的头数据
    public int headQueue(){
//        判断
        if(isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front +1];
    }
}

//问题：数据无法重复使用，数组队列