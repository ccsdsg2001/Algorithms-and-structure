package KMP;

import java.util.Arrays;

/**
 * @author cc
 * @date 2022年05月19日 21:22
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "AVCADASDA";
        String str2 = "ADAAFSAG";

        int[] next=kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));
        int index=kmpSearch(str1,str2, next);
        System.out.println(index);
    }

    /**
     * @Author cc
     * @Description //TODO
     * @Date 22:18 2022/5/19
 * @param str1 源字符串
 * @param str2 子串
 * @param next 部分匹配表,是子串对应的部分匹配表
 * @return int 如果是-1就是没有匹配到,否则就返回第一个匹配的位置
     **/

    public static int kmpSearch(String str1,String str2,int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charat(i)!=str2.charAt(j),去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {//找到了j=3i
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next=new int[dest.length()];
        next[0]=0;//如果字符串的长度为1部分匹配值就是0
        for(int i=1,j=0;i<dest.length();i++){
            //当dest.charAt(i)!=dest.charAt(j),需要从next[j-1]获取新的j
            //dest.charAt(i)==dest.charAt(j)成立才退出
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];
            }

            //当dest.charAt(i)==dest.charAt(j)满足时,部分匹配值就是+1
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
