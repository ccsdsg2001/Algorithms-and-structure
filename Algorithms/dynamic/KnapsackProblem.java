package dynamic;

/**
 * @author cc
 * @date 2022年05月17日 20:35
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3,2};//物品的重量
        int[] val = {1500,3000,2000,1000};//物品的价值 val 就是v[i]
        int m = 10;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的价值的情况,定一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一列和第一行
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0
        }

        //根据前面的公式来动态规划管理]
        for(int i = 1; i < v.length; i++) {//不处理第一行i是从1开始
            for (int j = 1; j < v[0].length; j++) {//j从1开始
                //公式
                if(w[i-1]> j) {//因为从1开始,所以w[i]为w[i-1]
                    v[i][j]=v[i-1][j];
            }
                else {
                    //y因为从1开始所以
//                    v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况,使用if-else来体现公式
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
        }
    }

        //输出一下v
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                System.out.println(v[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("========================");
        //输出最后我们放入的商品
        //遍历path,输出把所有放入情况都得到
        int i=path.length-1;//行的最大下标
        int j=path[0].length-1;//列的最大下标
        while (i>0&&j>0) {//从path的最后开始找
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j-=w[i-1];
            }
            i--;
        }
    }
}
