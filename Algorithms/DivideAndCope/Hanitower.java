package DivideAndCope;

/**
 * @author cc
 * @date 2022年05月17日 18:58
 */
public class Hanitower {
    public static void main(String[] args) {
        hanITower(5,'A', 'B', 'C');
    }


    //使用分治算法
    public static void hanITower(int num,char a,char b,char c){
        //如果只有一个盘
        if(num ==1){
            System.out.println("第1个盘"+a+"->"+c);
        }else
        {//如果有N>=2的情况,看作两个盘 1.最下边的盘 2.上面的盘
            //先把最上面的盘A->B,移动过程中会使用到c
            hanITower(num-1, a,c,b);
            //把最下边的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //把B塔的所有盘从B->C,移动过程中使用到A塔
            hanITower(num-1,b,a,c);
        }
    }
}

