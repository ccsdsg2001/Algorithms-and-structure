package tree;

import com.sun.source.util.DocTrees;

import java.awt.*;
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

//        //test
//        binaryTree.preOreder();
//        System.out.println("*************");
//        binaryTree.infixOrder();
//        System.out.println("*************");
//        binaryTree.postOrder();

        //serach
//        binaryTree.postOrderSearch(5);

        //delete
        binaryTree.preOreder();
        System.out.println("--------------------contrast----------------------------");
        binaryTree.delNode(5);
        binaryTree.preOreder();

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
    //前序查找
    public HeroNode preOrederSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if(root!= null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return this.root.postOrederSearch(no);
        }else {
            return null;
        }
    }
    //删除结点
    public void delNode(int no ){
        if(root!=null){
            //如果只有一个root结点,判断root是不是要删除结点
            if(root.getNo() ==no){
                root =null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("cantdel");
        }
    }





}

//创建HeroNode节点
class HeroNode {
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
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树进行遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    //查找no,找打就返回no,没有找到就返回null
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历");
        //比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        //1.判断当前结点的左子结点是否为空,如果不为空,则递归前序查找
        //2.如果左递归前序查找,找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明左子树找到
            return resNode;
        }
        //左递归前序查找,找到结点,则返回,否则继续判断
        //当前的结点的右子结点是否为空,如果不空,则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前结点的左子结点是否为空,如果不为空,则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序查找");
        //如果找到,就返回,如果没有找到,就和当前结点进行比较,如果是则返回当前结点
        if (this.no == no) {
            return this;
        }
        //否则进行继续向右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrederSearch(int no) {
        //判断当前结点的左子结点是否为空,如果不为空,则递归后序查早
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrederSearch(no);
        }
        if (resNode != null) {//说明再左子树找到
            return resNode;
        }
        //如果左子树没有找到,则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrederSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("后序查找");
        //如果左右子树都没有找到,就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除结点
    //如果删除结点是叶子结点,则删除该结点,如果删除结点非叶子结点,则删除该子树
    public void delNode(int no){
        /*1.因为二叉树是单向的,所以判断当前的子节点是否需要要删除结点,而不能去判断当前这个结点是不是需要删除结点
2.如果当前结点的左子结点不为空,并且左子结点就是要删除结点,就将this.left =null;并且返回(结束递归删除)
3.如果当前结点的右子结点不为空,并且右子结点就是要删除结点,就将this.right=null;并且就返回
4.如第二步和第三步没有删除结点,需要向左子树进行递归删除
5.如果第四步没有删除结点,应当向右子树进行递归删除*/
        //如果当前结点的左子结点不为空,并且左子结点就是要删除结点,就将this.left =null;并且返回(结束递归删除)
        if(this.left!= null&&this.left.no==no){
            this.left=null;
            return;
        }
        //如果当前结点的右子结点不为空,并且右子结点就是要删除结点,就将this.right=null;并且就返回
        if(this.right!=null&& this.right.no==no){
            this.right =null;
            return;
        }
        //左子树进行递归删除
        if(this.left!=null){
            this.left.delNode(no);
        }
        //向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}











