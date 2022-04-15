package linkedlist;

import com.sun.source.tree.NewArrayTree;
import org.apache.commons.math3.util.ArithmeticUtils;

public class list {
    public static void main(String[] args){


//        进行测试
        Heronode hero1 =new Heronode(1,"2","3");
        Heronode hero2 =new Heronode(2,"2","3");
        Heronode hero3 =new Heronode(3,"2","3");
        Heronode hero4 =new Heronode(4,"2","3");
//        给到链表
        SingleLinkedList singleLinkedList =new SingleLinkedList();
//        加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        reverseLise(singleLinkedList.getHead());
        singleLinkedList.list();


        singleLinkedList.addByorder(hero1);
        singleLinkedList.addByorder(hero4);
        singleLinkedList.addByorder(hero3);
        singleLinkedList.addByorder(hero2);
        singleLinkedList.list();

        Heronode newheronode = new Heronode(2, "5", "3");
        singleLinkedList.update(newheronode);
        singleLinkedList.list();
        singleLinkedList.del(3);
        singleLinkedList.del(2);
        singleLinkedList.list();
        System.out.println(getLength(singleLinkedList.getHead()));
    }
//   题目1.求单链表中有效节点的个数
    /*方法：找到单链表节点的个数
    * head 链表的头节点
    * return 返回的就是有效节点的个数*/
    public static int getLength(Heronode head){
        if(head.next==null){//空链表
            return 0;
        }
        int length =0;
//        定义一个辅助变量，不统计头节点
        Heronode cur =head.next;
        while (cur != null){
            length++;
            cur =cur.next;//遍历
        }
        return length;
    }

//    题目2:查找单链表中的倒数第K个节点
    /*思路
    * 1.编写一个方法，接受head节点，同时接受一个index
    * 2.index表示是倒数第index个节点
    * 3.先从链表从头到尾遍历，得到链表总长度getlength
    * 4.得到size后，从链表中第一个开始遍历(size-index)个，就可以得到
    * 5.如找到就返回节点，否则就返回null*/
    public static Heronode findLastIndexNode(Heronode head,int index) {
//        如果链表为空，则放回null
        if (head.next == null) {
            return null;//没有找到
        }
//        第一个遍历得到链表的长度（节点个数）
        int size = getLength(head);
//        第二次遍历 size-index位置，就是倒数第k个节点
//        先做一个index校验
        if (index <= 0 || index > size) {
            return null;
        }
//        定义辅助遍历，for循环定位倒数的index
        Heronode cur =head.next;
        for(int i =0;i<size -index;i++){
            cur =cur.next;
        }
        return cur;
    }
//    题目3：单链表的反转
    /*思路：1.先定义一个节点reverserhead =new Heronode（）
    * 2.从头遍历到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reversehead的最前端
    * 3原来的链表head.next =reverseHead.next*/
    public static void reverseLise(Heronode head){
//        如果当前列表为空，或只有一个节点，无需反转，直接返回
        if(head.next ==null||head.next.next ==null){
            return;
        }
//        定义一个辅助的指针（变量），遍历原来的链表
        Heronode cur = head.next;
        Heronode next =null;//指向当前节点【cur】的下一个节点
        Heronode reverseHead =new Heronode(0, "", "");
//        遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur !=null){
            next =cur.next;//先暂时保存当前节点的下一个节点
            cur.next =reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next=cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
//        将head.next指向reversehead。next，实现单链表的反转
        head.next=reverseHead.next;
    }

}


class SingleLinkedList {
    //    先定义化一个头节点，头节点不作动作，不存放具体数据
    private Heronode head = new Heronode(0, "", "");

    public Heronode getHead(){
        return head;
    }

    //    添加节点到单向链表。1.找到当前链表的最后节点。2.将最后节点的next指向新的节点
    public void add(Heronode heronode) {

//        定义一个辅助遍历temp
        Heronode temp = head;
//         遍历链表，找到最后
        while (true) {
//            找到链表的最后
            if (temp.next == null) {
                break;
            }
//            如果没有找到，将temp后移
            temp = temp.next;
        }
//        当退出while循环，temp就指向链表的最后，将最后节点的next指向新的节点
        temp.next = heronode;
    }

    //    按照排名将英雄插入到指定位置
    public void addByorder(Heronode heronode) {
//        因为头节点不能动，通过一个辅助变量找到添加的位置，因为单链表，temp为添加位置前一个节点，否则插入不了
        Heronode temp = head;
        boolean flag = false;//flag标志添加的标号是否存在
        while (true) {
            if (temp.next == null) {//说明temp已在链表的最后
                break;
            }
            if (temp.next.no > heronode.no) {//位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == heronode.no) {//说明希望添加的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
//        判断flag的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("%dcunzai", heronode.no);
        } else {
            //插入在链表中，temp的后面
            heronode.next = temp.next;
            temp.next = heronode;
        }
    }

    //    修改节点的消息，根据no编号来修改.1,根据newheronode的no来修改
    public void update(Heronode newHeronode) {
//    判断是否为空
        if (head.next == null) {
            System.out.println("kong");
            return;
        }
//        找到需要修改节点，根据no编号。定义辅助变量
        Heronode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeronode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
//        根据flag判断找到是否需要修改的节点
        if (flag) {
            temp.name = newHeronode.name;
            temp.nickname = newHeronode.nickname;
        } else {//没有找到
            System.out.println(newHeronode.no);
        }
    }


    //    删除节点思路。1。head不能动，需要temp辅助节点找到删除节点前一个节点。2、说明我们在比较，是temp.next.no和需要删除节点no比较
    public void del(int no) {
        Heronode temp = head;
        boolean flag = false;//表示是否找到删除节点的
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
//                找到删除节点前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
//        判断flag
        if (flag) {//找到，可以删除
            temp.next = temp.next.next;
        } else {
            System.out.println(no);
        }
    }
//    显示链表 遍历
    public void list(){
//        判断列表是否为空
        if(head.next==null){
            System.out.println("kong");
            return;
        }
//        用辅助变量来遍历
        Heronode temp =head.next;
        while (true){
//            判断是否链表最后
            if(temp == null){
                break;
            }
//            输出节点信息
            System.out.println(temp);
//            将temp后移
            temp =temp.next;
        }
    }
}

//定义Heronode，每个对象就是一个节点
class Heronode{
    public int no;
    public String name;
    public String nickname;
    public Heronode next;
//    构造器
    public Heronode(int no,String name,String nickname){
        this.name=name;
        this.no =no;
        this.nickname = nickname;
    }
//    为显示方法，tostring
    @Override
    public String toString() {
        return "Heronode{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + ", next=" + next + '}';
    }
}