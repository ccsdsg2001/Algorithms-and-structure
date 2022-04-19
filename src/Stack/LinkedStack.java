package Stack;

/**
 /**
 * 用单链表实现栈
 *
 * 表示链表的一个节点
 * @author ly
 *
 */
class Node {

    Object element;
    Node next;

    public Node(Object element){
        this(element,null);
    }

    /**
     * 创建一个新的节点
     * 让他的next指向，参数中的节点
     * @param element
     * @param n
     */
    public Node(Object element,Node n){
        this.element=element;
        next=n;
    }

    public Object getElement() {
        return element;
    }

}

/**
 * 用链表实现的栈，内含push pop peak 方法
 * @author ly
 *
 */
public class LinkedStack {

    Node header;//栈顶元素
    int elementCount;//栈内元素个数
    int size;//栈的大小

    /**
     * 构造函数，构造一个空的栈
     */
    public LinkedStack(){
        header=null;
        elementCount=0;
        size=0;
    }

    /**
     * 通过构造器自定义栈的大小
     * @param size
     */
    public LinkedStack(int size) {
        header=null;
        elementCount=0;
        this.size=size;
    }

    public void setHeader(Node header) {
        this.header=header;
    }

    public boolean isFull() {
        if (elementCount==size) {
            return true;
        }

        return false;
    }

    public boolean isEmpty() {
        if (elementCount==0) {
            return true;
        }

        return false;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(Object value) {
        if (this.isFull()) {
            throw new RuntimeException("Stack is Full");
        }
        //注意这里面试将原来的header作为参数传入，然后以新new出来的Node作为header
        header=new Node(value, header);
        elementCount++;
    }

    /**
     * 出栈
     * @return
     */
    public Object pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        Object object=header.getElement();

        header=header.next;

        elementCount--;

        return object;
    }

    /**
     * 返回栈顶元素
     */
    public Object peak(){

        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return header.getElement();
    }

}