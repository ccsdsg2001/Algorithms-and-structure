package horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author cc
 * @date 2022年05月29日 14:38
 */
public class HorseChessboard {

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个棋盘，标记棋盘各个位置是否被访问
    private static boolean visited[];
    //使用一个属性，标记是否棋盘所有位置被访问
    private static boolean finished;//如true，则成功

    public static void main(String[] args) {
        X=8;
        Y=8;
        int row =1;//初始位置的行
        int column=1;//初始位置的列
        //创建棋盘
        int[][] chessboard=new int[X][Y];
        visited =new boolean[X*Y];//初始值都是false

        traversalChessboard(chessboard,row-1,column-1,1);
        for (int[] rows:chessboard){
            for(int step:rows){
                System.out.println(step);
            }
        }
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 15:00 2022/5/29
     * @param chessboard 棋盘
     * @param row 马当前位置的行 从0开始
     * @param column 马当前位置的列 从0开始
     * @param step 第几步,初始位置就是第一步
     **/

    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column]=step;
        visited[row*X+column]=true;//标记该位置已经访问
        //获取当前位置可以走的下一个集合
        ArrayList<Point> ps=next(new Point(column,row));
//对ps进行排序,规则就是对ps所有的point对象下一步位置的数目,进行非递减排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p=ps.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过
            if(!visited[p.y*X+ p.x]){
                //说明还没有访问过
                traversalChessboard(chessboard,p.y, p.x, step+1);
            }
        }
        //判断马是否完成,使用step和走的步数比较
        //如果没有达到数量,则表示没有完成任务,将整个棋盘置为0
        //step<X*Y 有两种情况1.棋盘到目前位置,还没有走完2.棋盘处于一个回溯过程
        if(step<X*Y&&!finished){
            chessboard[row][column]=0;
            visited[row*X+column]=false;
        }else {
            finished=true;
        }
    }


    /**
     * @Author cc
     * @Description //TODO
     * @Date 14:50 2022/5/29
     * 根据当前位置（point对象），计算还能走哪些位置，并放入到一个集合中，最多有8个位置
     * @param curPoint
     * @return java.util.ArrayList<java.awt.Point>
     **/

    public static ArrayList<Point> next(Point curPoint){
        //创建一个ArrayList
        ArrayList<Point> ps=new ArrayList<Point>();
        //创建一个point
        Point p1=new Point();
        //表示马可以走5这个位置
        if((p1.x=curPoint.x-2)>=0&&(p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
//6
        if((p1.x=curPoint.x-1)>=0&&(p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //7
        if((p1.x=curPoint.x+1)<X&&(p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //0
        if((p1.x=curPoint.x+2)<X&&(p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //1
        if((p1.x=curPoint.x+2)<X&&(p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        //2
        if((p1.x=curPoint.x+1)<X&&(p1.y=curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //3
        if((p1.x=curPoint.x-1)>=0&&(p1.y=curPoint.y+2)< Y){
            ps.add(new Point(p1));
        }
        //4
        if((p1.x=curPoint.x-2)>=0&&(p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前一步所有的下一步选择位置,进行非递减排序,减少回溯的次数
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
               //获取到o1的下一步所有位置个数
                int count1=next(o1).size();
                //获取到o2的下一步所有位置个数
                int count2=next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }

            }
        });
    }


}
