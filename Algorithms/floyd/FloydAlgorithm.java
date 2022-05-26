package floyd;

import java.util.Arrays;

/**
 * @author cc
 * @date 2022年05月26日 17:02
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        //测试略

    }
}


//创建图
class Graph{
    private char[] vertex;//存放顶点的数组
    private int[][] dis;//保存,从各个顶点出发到其他顶点的距离,最后的结果,也是保留再该数组
    private int[][] pre;//保存到达目标顶点的前驱顶点

    /**
     * @Author cc
     * @Description //TODO
     * @Date 17:05 2022/5/26
     * @param length  大小
     * @param matrix 领接矩阵
     * @param vertex 顶点数组
     * @return null
     **/

    public Graph(int length,int[][] matrix,char[] vertex){
        this.vertex=vertex;
        this.dis=matrix;
        this.pre=new int[length][length];
        //pre数组初始化,存放的是前驱顶点的下标
        for(int i=0;i<length;i++){
            Arrays.fill(pre[i],i);
        }
    }

    //显示数组
    public void show() {
        for (int k = 0; k < dis.length; k++) {
            //先将pre数组输出的一行
            for (int i = 0; i < dis.length; k++) {
                System.out.println(pre[k][i]);
            }
            //输出dis数组
            for (int i = 0; i < dis.length; i++) {
                System.out.println(dis[k][i]);

            }
        }
    }


    //flord算法
    public void floyd(){
        int len=0;//变量保存距离
        //对中间顶点遍历,k就是中间顶点的下标[A,B,C,D,E,F,G]
        for(int k=0;k<dis.length;k++){
            //从i顶点开始出发
            for(int i=0;i<dis.length;i++){
                //到达j顶点
                for(int j=0;j<dis.length;j++){
                    len=dis[i][k]+dis[k][j];//从i顶点出发,经过k中间顶点,到达j顶点距离
                    if(len<dis[i][j]){
                        //如果len小于dis[i][j]
                        dis[i][j]=len;//更新距离
                        pre[i][j]=pre[k][j];//更新前驱顶点
                    }
                }
            }
        }
    }
}