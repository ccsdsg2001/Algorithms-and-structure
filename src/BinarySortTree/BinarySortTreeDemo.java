package BinarySortTree;

import org.apache.commons.math3.analysis.solvers.RiddersSolver;

import java.security.PublicKey;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree=new BinarySortTree();
        for(int i=0;i<arr.length;i++){
            binarySortTree.add(new Node(arr[i]));
        }

        binarySortTree.infixOrder();

//        binarySortTree.delNode(12);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(10);
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(3);
        binarySortTree.delNode(9);
        binarySortTree.delNode(1);

        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法1.返回的以Node为根节点的二叉排序树的最小节点的值2.删除Node为根节点的二叉排序树的最小节点
    //node传入的节点 return以Node为根节点的二叉排序树的最小节点的值
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小节点,删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.先去找到要删除的节点targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果发现当前二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点时叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode时父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    //是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除右两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一颗子树的节点,如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果是targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                }
            }
        }
    }
    //添加节点的方法
    public void add(Node node){
        if(root==null){
            root=node;//如果root为空直接让root指向Node
        }else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else {
            System.out.println("null");
        }
    }
}



//创建Node节点
class Node{
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node[value="+value+"]";
    }

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点 value希望删除节点的值 return找到返回该节点的值,否则返回null
    public Node search(int value) {
        if (value == this.value) {//找到就是该节点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前节点,向左子树递归查找
            //如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前节点,向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        //如果当前节点就i是要删除的节点的父节点,就返回
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else {
            //如果查找的值小于当前节点的值,并且当前节点的左子节点不为空
            if(value <this.value&&this.left!=null){
                return this.left.searchParent(value);//向左子树递归查找
            }else if(value >=this.value&&this.right!=null){
                return this.right.searchParent(value);//向右子树递归查找
            }else{
                return null;//没有找到父节点
            }
        }
    }

    //添加节点的方法
    //递归的形式添加节点,注意满足二叉排序树的要求
    public void add(Node node){
        if(node ==null){
            return;
        }
        //判断传入的节点的值,和当前的子树的根节点的值关系
        if(node.value<this.value){
            //如果当前节点的左子节点为null
            if(this.left==null){
                this.left=node;
            }else {
                //递归的向左子树添加
                this.left.add(node);
            }
        }else {
            //添加的节点的值大于当前节点的值
            if(this.right==null){
                this.right=node;
            }else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }


}