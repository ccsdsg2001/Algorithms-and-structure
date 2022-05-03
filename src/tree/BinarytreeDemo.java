package tree;

import java.beans.JavaBean;

public class BinarytreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree =new BinaryTree();
        //创建需要的节点
        HeroNode root =new HeroNode(1, "cc");
        HeroNode node =new HeroNode(2,"cs");
        HeroNode node1 = new HeroNode(3,"cv");
        HeroNode node2 =new HeroNode(4,"cb");
        HeroNode node3 = new HeroNode(5,"xz");

        //手动创建二叉树
        root.setLeft(node);
        root.setRight(node1);
        node1.setRight(node2);
        node1.setLeft(node3);
        binaryTree.setRoot(root);

        //test
        binaryTree.preOreder();
        System.out.println("*************");
        binaryTree.infixOrder();
        System.out.println("*************");
        binaryTree.postOrder();

    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOreder(){
        if(this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("null");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("null");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("null");
        }
    }





}

//创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树进行遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right!= null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left !=null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }







}
