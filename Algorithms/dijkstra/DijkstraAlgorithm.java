package dijkstra;

import java.util.Arrays;

/**
 * @author cc
 * @date 2022年05月24日 13:29
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        //测试略
    }
}


class Graph{
    private char[] vertex;//顶点数组
    private int[][] matrix;//领接矩阵
    private VisitedVertex vv;//已经访问的顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示结果
    public void showDijksta(){
        vv.show();
    }

    //显示图
    public void showGraph(){
        for (int[] link:matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //算法实现 index表示出发顶点对应的下标
    public void dsj(int index){
        vv=new VisitedVertex(vertex.length,index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for(int j=1;j<vertex.length;j++){
            index=vv.updateArr();//选择并返回新的访问顶点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len=0;
        //遍历领接矩阵
        for(int j=0;j<matrix[index].length;j++){
            //len:出发顶点到index顶点的距离+从index顶点到j顶点距离的和
            len =vv.getDis(index)+matrix[index][j];
            //如果j顶点没有被访问过并且len小于出发顶点到j顶点的距离
            if(!vv.in(j)&&len<vv.getDis(j)){
                vv.updatePre(j,index);
                vv.updateDis(j,len);
            }
        }
    }
}


//以访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过1表示访问过,0为未访问
    public int[] already_arr;
    //每个下标对应的值未前一个顶点下标
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离,求的最短距离存放到dis
    public int[] dis;


/**
 * @Author cc
 * @Description //TODO
 * @Date 13:38 2022/5/24
 * @param length 表示顶点的个数
 * @param index 出发顶带你对应的下标
 * @return null
 **/

    public VisitedVertex(int length,int index){
        this.already_arr=new int[length];
        this.pre_visited=new int[length];
        this.dis=new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        this.already_arr[index]=1;//设置出发顶点被访问过
        this.dis[index]=0;//设置出发顶点访问距离为0
    }

    //判断index顶点是否被访问过 如果访问过 就返回true,否则false
    public boolean in(int index){
        return already_arr[index]==1;
    }

    //更新出发顶点到index顶点的距离
    public void updateDis(int index,int len){
        dis[index]=len;
    }

    //更新pre这个顶点前驱顶点为index顶点
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }

    //继续选择并返回新的访问顶点
    public int updateArr(){
        int min=65535,index=0;
        for(int i=0;i< already_arr.length;i++){
            if(already_arr[i]==0&&dis[i]<min){
                min=dis[i];
                index=i;
            }
        }
        //更新index顶点被访问过
        already_arr[index]=1;
        return index;
    }

    //显示最后的结果,将三个数组情况输出
    public void show(){
        for(int i:already_arr){
            System.out.println(i);
        }
        for(int i:pre_visited){
            System.out.println(i);
        }
        for(int i:dis){
            System.out.println(i);
        }
        char[] vertex={'a','b','c','d'};
        int count=0;
        for(int i:dis){
            if(i!=65535){
                System.out.println(vertex[count]);
            }else {
                System.out.println();
            }
            count++;
        }
    }
}