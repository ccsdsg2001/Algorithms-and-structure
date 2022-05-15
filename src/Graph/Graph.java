package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author cc
 * @date 2022年05月15日 23:37
 */
public class Graph {

    public static void main(String[] args) {
        int n =8;
        String Vextesx[] ={"1","2","3","4","5","6","7","8"};

        Graph graph=new Graph(n);
        for(String vertex:Vextesx){
            graph.insertVertex(vertex);


            graph.insertEdge(0,1,1);
            graph.insertEdge(0,2,1);
            graph.insertEdge(1,3,1);
            graph.insertEdge(1,4,1);
            graph.insertEdge(3,7,1);
            graph.insertEdge(4,7,1);
            graph.insertEdge(2,5,1);
            graph.insertEdge(2,6,1);
            graph.insertEdge(5,6,1);

            graph.showGraph();

            graph.dfs();

            graph.bfs();
        }
    }

    private ArrayList<String>vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的领结矩阵
    private  int numOfEdges;//表示边的数目
    //定义给数组Boolean[],记录某个节点是否被访问
    private boolean[] isVisited;
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList=new ArrayList<String>();
        numOfEdges=0;
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 23:41 2022/5/15
     * @param index
     * @return 如果存在就返回对应的下标,否则返回-1
     **/

    public int getFirstNeighBor(int index){
        for(int j =0;j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个领接节点的下标获取下一个领接节点
    public int getNextNeightBor(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i第一次就是0
    private void dfs(boolean[] isVisited,int i) {
        //首先访问节点,输出
        System.out.println(getValueByIndex(i));
        //将节点设置为已经访问
        isVisited[i]=true;
        //查找节点i的第一个领接节点w
        int w=getFirstNeighBor(i);
        while (w!=-1){//说明有
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w=getNextNeightBor(i,w);
        }
    }


    //对dfs进行一个重载,遍历我们所有的节点,并进行dfs
    public void dfs(){
        isVisited=new boolean[vertexList.size()];
        //遍历所有的节点,进行dfs回溯
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }


    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头节点和下标
        int w;//领接节点w
        //队列,记录节点访问的顺序
        LinkedList queue =new LinkedList();
        //访问节点,输出节点信息
        System.out.println(getValueByIndex(i));
        //标记为以访问
        isVisited[i]=true;
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出队列头节点下标
            u=(Integer)queue.removeFirst();
            //得到第一个领节点的下标w
            w =getFirstNeighBor(u);
            while (w!=-1) {//找到
                //是否访问过
                if(!isVisited[w]){
                    System.out.println(getValueByIndex(w));
                    //标记为以访问
                    isVisited[w]=true;
                    //入队
                    queue.addLast(w);
                }
                //以w为前驱点,找w后面的下一个领节点
                w=getNextNeightBor(u,w);//体现出广度优先
            }
        }
    }

    //遍历所有的节点,都进行广度优先搜索
    public void bfs(){
        isVisited =new boolean[vertexList.size()];
        for(int i =0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i对应的数据
    public String getValueByIndex(int i ){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 23:49 2022/5/15
     * @param v1 表示点的下标即使第几个顶点
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     **/

    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
}
