package prim;

import Graph.Graph;

import java.util.Arrays;

/**
 * @author cc
 * @date 2022年05月21日 13:13
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data=new char[] {'a','b','c','d','e','f','g'};
        int verxs=data.length;

        MGraph mGraph=new MGraph(verxs);
        MinTree minTree=new MinTree();
//        minTree.createGraph(Graph, verxs,data, mGraph.weight);
        //测试略
    }
}


//创建最小生成树-村庄的图
class MinTree{
    //创建图的领接矩阵
    /**
     * @Author cc
     * @Description //TODO
     * @Date 13:16 2022/5/21
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的领接矩阵
     **/
    public void createGraph(MGraph graph,int verxs,char data[],int[][] weight){
        int i,j;
        for(i=0;i<verxs;i++){
            //顶点
            graph.data[i]=data[i];
            for(j=0;j<verxs;j++){
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    //显示图的领接矩阵
    public void showGraph(MGraph graph){
        for(int[] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }
    //编写prim算法,得到最小生成树
    /**
     * @Author cc
     * @Description //TODO
     * @Date 13:20 2022/5/21
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     **/

    public void prim(MGraph graph,int v){
        //标记顶点是否被访问过
        int visited[] =new int[graph.verxs];
        //默认元素的值都是0,表示从来没有访问过
        for(int i=0;i<graph.verxs;i++){
            visited[i]=0;
        }

        //把当前这个节点记为以访问
        visited[v]=1;
        //h1和h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;//初始化一个大数,后面在遍历过程中,会被替换
        for(int k=1;k<graph.verxs;k++){
            //因为graph.verxs顶点,算法结束后,有graph.verxs-1边
            //确定每一次生成的子图,和哪个节点的距离最近
            for(int i =0;i<graph.verxs;i++){
                //i节点表示被访问过的节点
                for(int j=0;j<graph.verxs;j++){
                    //j节点表示还没有访问过的节点
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        //替换minWeight(寻找已经访问过的节点和未访问过的节点间的权值最小的边
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            System.out.println(minWeight);
            //将当前这个节点标记为以访问
            visited[h2]=1;
            //minweight重新设置为100000
            minWeight =10000;
        }
    }





}

class MGraph{
    int[][] weight;//存放边,领接矩阵
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据


    public MGraph(int verxs){
        this.verxs=verxs;
        data = new char[verxs];
        weight=new int[verxs][verxs];

    }
}