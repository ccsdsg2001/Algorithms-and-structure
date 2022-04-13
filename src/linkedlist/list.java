package linkedlist;

import com.sun.source.tree.NewArrayTree;

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
    }
}


class SingleLinkedList {
    //    先定义化一个头节点，头节点不作动作，不存放具体数据
    private Heronode head = new Heronode(0, "", "");

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