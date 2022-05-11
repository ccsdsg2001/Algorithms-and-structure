package AVL;


import org.apache.commons.math3.geometry.partitioning.utilities.AVLTree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLtree avlTree=new AVLtree();
        int[] arr={10,11,7,6,8,9};
        for(int i =0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());


    }
}

class AVLtree {
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




class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node[value=" + value + "]";
    }

    public Node(int value) {
        this.value = value;
    }

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(left ==null?0:left.height(),right==null?0:right.height())+1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if(left ==null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if(right ==null){
            return 0;
        }
        return right.height();
    }

    //左旋转的方法
    private void leftRotate(){
        //1.创建一个新的节点newNode,值等于当前根节点的值
        //2.把新节点的左子树设置当前节点的左子树newNode.left=left
        //3.把新节点的右子树设置为当前节点的右子树的左子树 newNode.right=right.left;
        //4.把当前节点的值换为右子节点的值 value=right.value
        //5.把当前节点的右子树设置成右子树的右子树 right=right.right
        //6.把当前节点的左子树设置为新节点 left=newleft;
        Node newnode =new Node(value);
        newnode.left=left;
        newnode.right=right.left;
        value=right.value;
        right=right.right;
        left=newnode;
    }

    //右旋转
    private void rightRotate(){
        //1.创建一个新的节点newNode,值等于当前根节点的值.
        //2.把新节点的左子树设置当前节点的左子树的右子树newNode.right=right
        //3.把新节点的左子树设置为当前节点的左子树的右子树newNode.left=left.right
        //4.把当前节点的值换为左子节点的值value =left.value
        //5.把当前节点的左子树设置成左子树的左子树left=left.left
        //6.把当前节点的右子树设置为新节点 right=newLeft;
        Node newnode=new Node(value);
        newnode.right=right;
        newnode.left=left.right;
        value=left.value;
        left=left.left;
        right=newnode;
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
    public Node searchParent(int value) {
        //如果当前节点就i是要删除的节点的父节点,就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值,并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //添加节点的方法
    //递归的形式添加节点,注意满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值,和当前的子树的根节点的值关系
        if (node.value < this.value) {
            //如果当前节点的左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {
            //添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }
//    添加节点的方法.递归的形式添加节点,需要满足二叉排序树的要求

//当添加完一个节点后,如右子树的高度-左子树的高度>1,左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //先对右子节点旋转
                this.right.rightRotate();
//                    然后在对当前节点左旋转
                this.leftRotate();
            } else {
                //直接进行左旋转
                this.leftRotate();
            }
            return;
        }
        //如果左子树高度-右子树的高度>1,右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
//                如果左子树的右子树的高度大于它的左子树的高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
//                    先对当前节点的左节点左旋转
                this.left.leftRotate();
//                    在对当前节点右旋转
                this.rightRotate();
            } else {
                //直接进行右旋转
                this.rightRotate();
            }
        }
    }



    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}




