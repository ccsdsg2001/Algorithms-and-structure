package linkedlist;

public class DoubleLinkerListDemo {
    public static void main(String[] args){

//        给到链表
       DoubleLinkedList doubleLinkedList =new DoubleLinkedList();

//        reverseLise(singleLinkedList.getHead());
        doubleLinkedList.list();



    }
}


//创建一个双向链表的类
class DoubleLinkedList {

    //    先初始化一个头节点，头节点不要动。不存放其他数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //    返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表
    public void list() {
        //判断是否列表为空
        if (head.next == null) {
            System.out.println("kong");
            return;
        }
        //因为头节点，需要辅助变量遍历
        HeroNode2 temp = head.next;
        while (true) {
//            判断链表是否最后
            if (temp == null) {
                break;
            }
            //输出节点的消息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
//        遍历链表，找到最后
        while (true) {
//            找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向链表最后，形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点内容，与单向链表一样
    public void update(HeroNode2 newHeronode) {
//    判断是否为空
        if (head.next == null) {
            System.out.println("kong");
            return;
        }
//        找到需要修改节点，根据no编号。定义辅助变量
        HeroNode2 temp = head.next;
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

    //      从双向链表中删除一个节点：对于双向链表，可以直接找到要删除的这个节点，找到后，自我删除
    public void del(int no) {
//        判断当前链表是否为空
        if (head.next == null) {//空链表
            System.out.println("kong ");
            return;
        }
        HeroNode2 temp = head.next;//辅助变量
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp == null) {//已经到链表最后
                break;
            }
            if (temp.no == no) {
                //找到待删除节点前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag) {//找到
            //可以删除
            temp.pre.next = temp.next;
            //如果是最后一个节点，不需要执行这句话。否则会出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            } else {
                System.out.println(no);
            }
        }


    }

    class HeroNode2 {
        public int no;
        public String name;
        public String nickname;
        public HeroNode2 next;//指向下一个节点，默认null
        public HeroNode2 pre;//指向前一个节点，默认null

        public HeroNode2(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "HeroNode2{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + ", next=" + next + ", pre=" + pre + '}';
        }
    }
}
