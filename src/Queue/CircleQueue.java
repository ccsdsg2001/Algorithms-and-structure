package Queue;

import java.io.DataInputStream;
import java.util.Scanner;

public class CircleQueue {
    public static void main(String[] args){
        System.out.println("测试");
        cyqueue queue  = new cyqueue(4);
        char key =' ';
        Scanner in =new Scanner(System.in);
        boolean loop =true;
        while (loop){
            System.out.println("show");
            System.out.println("exit");
            System.out.println("add");
            System.out.println("get");
            System.out.println("head");
            key = in.next().charAt(0);
            switch (key){
                case's':
                    queue.showQueue();
                    break;
                case 'a':
                    int value =in.nextInt();
                    queue.addQueue(value);
                    break;
                case'g':
                    try {
                        int res =queue.getQueue();
                        System.out.printf("%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res =queue.headQueue();
                        System.out.println(res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    in.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}
class cyqueue {
    private int maxsize;//表示数组最大容量
    private int front;//front指向队列第一个元素
    private int rear;//rear指向队列最后一个元素的后一个位置，空出一个空间
    private int[] arr;//该数据用于存放数据，模拟队列

    //  构造函数
    public cyqueue(int arrMaxsize) {
        maxsize = arrMaxsize;
        arr = new int[maxsize];
    }

    //    判断是否满
    public boolean isFull() {
        return (rear + 1)%maxsize == front;
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
        arr[rear] =n;//直接将数据加入
        rear =(rear +1) %maxsize;//将rear后移,考虑取模
    }

    //    获取队列的数据,出队列
    public int getQueue(){
//        判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列空");
        }
//        分析出front指向队列的第一个元素
        int value =arr[front];//把front对应的值保留到一个临时变量
        front =(front +1)%maxsize;//将front后移,考虑取模
        return value;//将临时保存的变量返回
    }

    //    显示队列的所有数据
    public void showQueue(){
//        遍历
        if(isEmpty()){
            System.out.println("队列空");
            return;
        }
        //从front开始遍历
        for (int i =0;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxsize,arr[i%maxsize]);
        }
    }
//        求出当前队列中有效数据的个数
    public int size(){
        return (rear+maxsize-front)%maxsize;
    }

    //    显示队列的头数据
    public int headQueue(){
//        判断
        if(isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }
}