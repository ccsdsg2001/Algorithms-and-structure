package tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //创建一个arrbinarytree
        ArrBinaryTree arrBinaryTree =new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
        System.out.println("-------------------------------");
        arrBinaryTree.infixOrder(0);
        System.out.println("-------------------------------");
        arrBinaryTree.pastOrder(0);
    }
}

class ArrBinaryTree {
    private int[] arr;//存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法完成顺序存储二叉树的前序遍历,index数组的下标
    public void preOrder(int index) {
        //如果数组为空,或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("null");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归进行遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归进行遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中序遍历
    public void infixOrder(int index) {
        //如果数组为空,或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("null");
        }
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //后序遍历
    public void pastOrder(int index) {
        //如果数组为空,或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("null");
        }
        if ((index * 2 + 1) < arr.length) {
            pastOrder(index * 2 + 1);
        }

        if ((index * 2 + 2) < arr.length) {
            pastOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}
