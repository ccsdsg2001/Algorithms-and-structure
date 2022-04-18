package linkedlist;

public class josephu {
    public static void main(String[] args){
        CircleSingledLinkedList circleSingledLinkedList =new CircleSingledLinkedList();
        circleSingledLinkedList.addBoy(125);
        circleSingledLinkedList.showBoy();

        circleSingledLinkedList.countBoy(10, 20, 125);
    }
}

//创建一个环形单向链表
class CircleSingledLinkedList {
    //    创建一个first节点，当前没有编号
    private Boy first = null;

    //    添加节点，构建成一个环形链表
    public void addBoy(int nums) {
//        nums做一个数据校验
        if (nums < 1) {
            System.out.println("false");
            return;
        }
        Boy curBoy = null;//辅助指针，构建环形链表
        //使用for创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建节点
            Boy boy = new Boy(i);
            //如果第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //    遍历当前循环链表
    public void showBoy() {
//        判断链表是否为空
        if (first == null) {
            System.out.println("null");
            return;
        }
//        first不能动，需要一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy.getNo());
            if (curBoy.getNext() == first) {//遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curboy后移
        }
    }

    //根据输入，输出队列
    /*startno表示从几个开始数
    * countNum表示数几下
    * nums最初有多少小孩在圈中*/
    public void countBoy(int startNo,int countNum,int nums){
        //对数据进行校验
        if(first == null || startNo <1 || startNo >nums){
            System.out.println("cuowu");
            return;
        }
//        创建辅助指针
        Boy helper = first;
        //创建一个辅助指针变量helper，事先指向环形链表最后节点
        while (true){
            if(helper.getNext() == first){//说明helper指向最后节点
                break;
            }
            helper = helper.getNext();
        }
        //开始前，先让first和helper移动k-1次
        for(int j =0;j<startNo -1;j++){
            first = first.getNext();
            helper =helper.getNext();
        }
        //当开始后，让first和helper指针同时移动m-1次。然后出列
        //这是一个循环操作，知道只有一个节点
        while (true){
            if(helper == first){//说明圈中只有一个节点
                break;
            }
//            让first和helper指针同时移动countNum-1
            for(int j =0;j<countNum -1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指向节点，就是小孩出圈节点
            System.out.println(first.getNo());
            //将first节点出列
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getNo());
    }
}




//创建一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点

    public Boy(int no){
        this.no=no;
    }

    public int getNo(){
        return no;
    }

    public void setNo(int no){
        this.no = no;
    }

    public Boy getNext(){
        return next;
    }

    public void setNext(Boy next){
        this.next =next;
    }
}