package Kruskal;

/**
 * @author cc
 * @date 2022年05月21日 15:15
 */
public class KruskalCase {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//领接矩阵
    //使用ING表示两个顶点不能连通
    private static final int INF=Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] data=new char[] {'a','b','c','d','e','f','g'};
        //测试略

    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen=vertexs.length;
        this.vertexs = new char[vlen];
        for(int i =0;i< vertexs.length;i++){
            this.vertexs[i]=vertexs[i];
        }
        //初始化边,使用的是复制拷贝的方式
        this.matrix=new int[vlen][vlen];
        for(int i=0;i<vlen;i++){
            for(int j=0;j<vlen;j++){
                this.matrix[i][j]=matrix[i][j];
            }
        }
        //统计边的个数
        for(int i =0;i<vlen;i++){
            for(int j=i+1;j<vlen;j++){
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }

    //打印领接矩阵
    public void print(){
        for(int i =0;i<vertexs.length;i++){
            for(int j=0;j< vertexs.length;j++){
                System.out.println(matrix[i][j]);
            }
        }
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 15:26 2022/5/21
     * 对边进行排序处理,冒泡排序
     * @param edges 边的集合
     **/

    private void sortEdges(EData[] edges){
        for(int i=0;i<edges.length-1;i++){
            for(int j =0;j<edges.length-1-i;j++){
                if(edges[j].weight>edges[j+1].weight){
                    //交换
                    EData tmp =edges[j];
                    edges[j]=edges[j+1];
                    edges[j+1]=tmp;
                }
            }
        }
    }

    public void kruskal(){
        int index=0;//表示最后结果数组的索引
        int[] ends=new int[edgeNum];//用于保存最小生成树中的每个顶点在最小生成树的终点
        //创建结果数组,保存最后的最小生成树
        EData[] rets=new EData[edgeNum];

        //获取图中所有边的集合
        EData[] edges=getEdges();
        //按照边的权值大小进行排序(从小到大)
        sortEdges(edges);
        //遍历edges数组,将边添加到最小生成树中,判断是否准备加入的边是否形成了回路,如果没有就加入rets,否则不能加入
        for(int i=0;i<edgeNum;i++){
            //获取第i条边的第一个顶点
            int p1=getPosition(edges[i].start);
            //获取到第i条边的第二个顶点
            int p2=getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树的中的终点
            int m=getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n=getEnd(ends,p2);
            //是否构成回路
            if(m!=m){
                //没有构成回路
                ends[m]=n;//设置m在已有最小生成树中的终点
                rets[index++]=edges[i];//有一条边加入到rets数组
            }
        }

        //统计并打印最小生成树
        System.out.println();
        for(int i=0;i<index;i++){
            System.out.println(rets[i]);
        }
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 15:27 2022/5/21
     * @param ch 顶点的值 比如'A' 'B'
     * @return int 返回ch顶点的对应的下标,如果找不到就返回-1
     **/

    private int getPosition(char ch){
        for(int i=0;i< vertexs.length;i++){
            if(vertexs[i]==ch){
                return i;//找到
            }
        }
        //找不到返回-1
        return -1;
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 15:30 2022/5/21
     * @return Kruskal.EData[]
     * 获取图中边,放到Edata[] 数组中,通过matrix矩阵来获取
     **/

    private EData[] getEdges(){
        int index=0;
        EData[] edges=new EData[edgeNum];
        for(int i=0;i< vertexs.length;i++){
            for(int j=i+1;j< vertexs.length;j++){
                if(matrix[i][j]!=INF){
                    edges[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }


    /**
     * @Author cc
     * @Description //TODO
     * @Date 15:32 2022/5/21
     * 获取下标为i的顶点的终点 用于后面判断两个顶点的终点是否相同
     * @param ends 数组就是巨鹿各个顶点对应的终点是哪个,ends数组是在遍历过程中,逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return int 返回的就是下标为i的这个顶点对应的终点的下标
     **/

    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }


}

class EData{
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData[<"+start+","+end+">="+weight+"]";
    }
}