package databasestructures;

import javax.swing.*;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始数组，0：表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //稀疏数组，1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

//        2.创建对应的稀疏数组
        int sparseArrp[][] = new int[sum + 1][3];
//        给稀疏数组赋值
        sparseArrp[0][0] = 11;
        sparseArrp[0][1] = 11;
        sparseArrp[0][2] = sum;
//        遍历二维数组，将非0的值存放到sparsearray中
        int count = 0;//用与记录第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArrp[count][0] = i;
                    sparseArrp[count][1] = j;
                    sparseArrp[count][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println();
        System.out.println("稀疏数组为");
        for(int i =0;i<sparseArrp.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArrp[i][0],sparseArrp[i][1],sparseArrp[i][2]);
        }
        System.out.println();

//        稀疏数组恢复成原始二维数组
//        1.先读取稀疏数组第一行，根据第一行数据，创建原始二维数组。
        int chessarr2[][] =new int[sparseArrp[0][0]][sparseArrp[0][1]];
//        2.在读取稀疏数组后几行的数据，并赋给原始二维数组即可
        for (int i =1;i<sparseArrp.length;i++){
            chessarr2[sparseArrp[i][0]][sparseArrp[i][1]] = sparseArrp[i][2];
        }

//        输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        for(int[] row :chessarr2){
            for (int data:row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}