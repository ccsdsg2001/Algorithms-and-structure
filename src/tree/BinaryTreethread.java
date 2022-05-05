package tree;

public class BinaryTreethread {
    public static void main(String[] args) {
        HeroNode1 root =new HeroNode1(1,"tom");
        HeroNode1 node2 =new HeroNode1(3,"jack");
        HeroNode1 node3=new HeroNode1(6,"smith");
        HeroNode1 node4=new HeroNode1(8,"mary");
        HeroNode1 node5=new HeroNode1(10,"king");
        HeroNode1 node6=new HeroNode1(14,"dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTre =new ThreadedBinaryTree();
        threadedBinaryTre.setRoot(root);
        threadedBinaryTre.threadedNodes();
        System.out.println("-----------------");
        threadedBinaryTre.prethreadedNodes(root);

        HeroNode1 leftNode = node5.getLeft();
        HeroNode1 rightNode = node5.getRight();

        threadedBinaryTre.threadedList();
    }
}

class ThreadedBinaryTree{
    private HeroNode1 root;
    //为了实现线索化,需要创建给指向当前结点的前驱结点的指针
    //在递归进行线索化,pre总是保留前一个结点
    private HeroNode1 pre =null;

    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量,存储当前遍历的结点,从root结点开始
        HeroNode1 node =root;
        while (node!=null){
            //循环的找到leftType==1的结点
            //后面随着遍历而变化,当leftType ==1时,说明该结点按照线索化
            //处理后的有效结点
            while (node.getLeftType()==0){
                node=node.getLeft();
            }

            //打印当前这个结点
            System.out.println(node);
            //如果当前节点的的右指针指向的是后继结点,就一直输出
            while (node.getRightType()==1){
                //获取到当前结点的后继结点
                node =node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node =node.getRight();
        }
    }

    //编写对二叉树进行中序线索化的方法
    //node就是当前需要线索化的结点
    public void threadedNodes(HeroNode1 node){
        //如果node==null,不能线索化
        if(node ==null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前结点
        if(node.getLeft()==null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if(pre!=null&&pre.getRight() ==null){
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个结点后,让当前结点是下一个结点的前驱结点
        pre = node;

        //在线索化右子树
        threadedNodes(node.getRight());
    }



    public void prethreadedNodes(HeroNode1 node){
        //如果node==null,不能线索化
        if(node ==null){
            return;
        }
        //线索化当前结点
        if(node.getLeft()==null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if(pre!=null&&pre.getRight() ==null &&pre.getLeft() !=node){
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个结点后,让当前结点是下一个结点的前驱结点
        pre = node;
        //先线索化左子树
        if(node.getLeftType()==0) {
            prethreadedNodes(node.getLeft());
        }
        //在线索化右子树
        prethreadedNodes(node.getRight());
    }



    public void pastThreadedTree(HeroNode1 node){
        if(node ==null){
            return;
        }

        pastThreadedTree(node.getLeft());
        pastThreadedTree(node.getRight());

        if(node.getLeft() ==null){
            node.setLeftType(1);
            node.setLeft(pre);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRightType(1);
            pre.setRight(node);
        }
        pre=node;
    }

    //前序遍历
    public void preOrder(){
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
    public HeroNode1 preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode1 infixOrderSearch(int no){
        if(root!= null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode1 postOrderSearch(int no){
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
class HeroNode1 {
    private int no;
    private String name;
    private HeroNode1 left;//默认null
    private HeroNode1 right;//默认null
    //说明如果leftType==0表示指向的是左子树,如果是1表示指向前驱结点
    //如果rightType==0表示指向的是右子树,如果1表示指向后继系欸但
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode1(int no, String name) {
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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
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
    public HeroNode1 preOrderSearch(int no) {
        System.out.println("前序遍历");
        //比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        //1.判断当前结点的左子结点是否为空,如果不为空,则递归前序查找
        //2.如果左递归前序查找,找到结点,则返回
        HeroNode1 resNode = null;
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
    public HeroNode1 infixOrderSearch(int no) {
        //判断当前结点的左子结点是否为空,如果不为空,则递归中序查找
        HeroNode1 resNode = null;
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
    public HeroNode1 postOrederSearch(int no) {
        //判断当前结点的左子结点是否为空,如果不为空,则递归后序查早
        HeroNode1 resNode = null;
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
    public void delNode(int no) {
        /*1.因为二叉树是单向的,所以判断当前的子节点是否需要要删除结点,而不能去判断当前这个结点是不是需要删除结点
2.如果当前结点的左子结点不为空,并且左子结点就是要删除结点,就将this.left =null;并且返回(结束递归删除)
3.如果当前结点的右子结点不为空,并且右子结点就是要删除结点,就将this.right=null;并且就返回
4.如第二步和第三步没有删除结点,需要向左子树进行递归删除
5.如果第四步没有删除结点,应当向右子树进行递归删除*/
        //如果当前结点的左子结点不为空,并且左子结点就是要删除结点,就将this.left =null;并且返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //如果当前结点的右子结点不为空,并且右子结点就是要删除结点,就将this.right=null;并且就返回
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}

