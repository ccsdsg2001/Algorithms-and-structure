package recursion;

public class Mazemigong {
    public static void main(String[] args) {
        //先创建一个二维数组,模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙,上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板,1表示
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        setWay(map, 1, 1);
        setWay2(map,1,1);



        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }


    }


    //使用递归回溯给小球找路
    /*1.map表示地图
    * 2.i,j表示从地图哪个位置开始出发(1,1)
    * 3.如果小球能到map[6][5]位置,说明路找到
    * 4.当map[i][j]为0表示该点没有走过,1表示墙,2表示通路可以走,3表示该点以找过,但是走不通*
    5.走迷宫时,确定一个策略,下>右>上>左.如果该点走不通在回溯
    * i从哪个位置开始找
    *j
    * return如果找到通路就返回true,否则就返回false*/
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这点还没走过,按照策略下>右>上>左 走
                map[i][j] = 2;//假定可以走通
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上
                    return true;
                } else if (setWay(map, i, j - 1)) {//向右走
                    return true;
                } else {
                    //说明走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0,可能是1,2,3
                return false;
            }
        }
    }
//修改策略,改成上 右 下 左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这点还没走过,按照策略下>右>上>左 走
                map[i][j] = 2;//假定可以走通
                if (setWay(map, i - 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i +1, j)) {//向上
                    return true;
                } else if (setWay(map, i, j - 1)) {//向右走
                    return true;
                } else {
                    //说明走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0,可能是1,2,3
                return false;
            }
        }

    }
}

//小球得到路径,与找路策略有关,找路上下左右顺序有关
