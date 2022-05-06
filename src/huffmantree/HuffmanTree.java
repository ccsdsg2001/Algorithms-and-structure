package huffmantree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] ={13,23,25,11,1,3,5};
        Node root = createHuffmanTree(arr);
        System.out.println("-------------------------");
        preOrder(root);

    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("null");
        }
    }


    //创建赫夫曼树的方法
    //arr 需要创建哈夫曼树的数组
    //return 创建好后赫夫曼树的root结点
    public static Node createHuffmanTree(int[] arr){
        //1,遍历arr数组 2.将arr元素构成一个node 3.将Node放入Arratlist中
        List<Node> nodes =new ArrayList<Node>();
        for(int value :arr){
            nodes.add(new Node(value));
        }

        //来一个循环的过程
        while (nodes.size()>1){
            //排序从小到大
            Collections.sort(nodes);

            System.out.println(nodes);

            //取出根节点权值最小的两颗二叉树
            //1.取出权值最小的结点(二叉树)
            Node leftNode = nodes.get(0);
            //2.取出权值第二小的结点
            Node rightNode = nodes.get(1);

            //3.构造一课新的二叉树
            Node parent =new Node(leftNode.value+rightNode.value);
            parent.right=rightNode;
            parent.left =leftNode;
            //4.从arraylist删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent加入到nodes
            nodes.add(parent);
        }
        //返回root结点
        return nodes.get(0);
    }
}

//创建结点类
//为了让Node对象持续排序Collections集合排序
//让node实现Comparable接口
class Node implements Comparable<Node>{
    int value;//结点权值
    Node left;//指向左子结点
    Node right;//指向右子结点

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    public Node(int value){
        this.value =value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}