package HashTable;

import sort.BubbleSort;

import java.util.Scanner;

public class HashtabDEMO {
    public static void main(String[] args) {
        System.out.println("请输入你想要的链表");
        Scanner in =new Scanner(System.in);
        int num =in.nextInt();
        HashTab hashTab=new HashTab(num);

        String key = "";

        while (true){
            System.out.println("add");
            System.out.println("list");
            System.out.println("find");
            System.out.println("exit");
            System.out.println("del");

            key =in.next();
            switch (key){
                case "add":
                    System.out.println("id");
                    int id =in.nextInt();
                    System.out.println("naem");
                    String name =in.next();
                    //创建雇员
                    Emp emp =new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case"list":
                    hashTab.list();
                    break;
                case"find":
                    System.out.println("id");
                    id = in.nextInt();
                    hashTab.findEmpByID(id);
                    break;
                case"exit":
                    in.close();
                    System.exit(0);
                case "del":
                    System.out.println("输入你要删除的id");
                    id = in.nextInt();
                    hashTab.del(id);
                    break;
                default:
                    break;
            }

        }

    }
}

//创建HashTab管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListarray;//使用数组来实现链表
    private int size;//表示有多少条链表

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedlistarray
        empLinkedListarray = new EmpLinkedList[size];
        for(int i =0;i<size;i++){
            empLinkedListarray[i] =new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id,得到员工添加到哪条链表
        int empLinkedListNo=HASHFUN(emp.id);
        //将emp添加到对应的链表中
        empLinkedListarray[empLinkedListNo].add(emp);

    }

    //遍历所有的链表,遍历Hashtab
    public void list(){
        for (int i =0;i<size;i++){
            empLinkedListarray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpByID(int id) {
        //根据散列函数确定哪条链表查找
        int empLinkedListNO = HASHFUN(id);
        Emp emp = empLinkedListarray[empLinkedListNO].findEmpById(id);
        if (emp != null) {//找到
            System.out.println(id);
        } else {
            System.out.println("没有找到");
        }
    }
    public void del(int id){
        int emplnk =HASHFUN(id);
        empLinkedListarray[emplnk].del(id);
    }

    public int HASHFUN(int id){
        return id %size;
    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//next默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}


//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp，因此我们这个链表的head是指向第一个Emp
    private Emp head;//默认为null

    //添加雇员到链表
    //1.假定添加雇员时,id是自增长,id的分配总是从小到大,直接将雇员直接加入到链表的最后
    public void add(Emp emp) {
        //添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员,使用一个辅助的指针,帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到链表zuihou
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时直接将emp加入到链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {//说明链表为空
            System.out.println("null");
            return;
        }
        System.out.println("信息为");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.printf("id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next;//后移,遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到,就返回emp,如果没有找到就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("null");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;//curemp指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据id来删除雇员
    public void del(int no) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Emp emp = head;//辅助指针
        while (true) {
            if (head.id == no) {
                head = head.next;
                return;
            }
            if (emp.next == null) {
                break;
            }
            if (emp.next.id == no) {
                emp.next = emp.next.next;
            } else {
                System.out.println("找不到要删除的雇员");
            }
            emp = emp.next;

        }
    }
}