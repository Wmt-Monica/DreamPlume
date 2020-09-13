package 稀疏数组;

import java.io.File;
import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //1.创建一个初始二维数组
        int a1[][] = new int[10][10];
        //2.初始化初始数组有效数值
        a1[3][6]=21;
        a1[6][1]=56;
        a1[9][3]=4;
        //3.打印初始数组——foreach
        System.out.println("初始数组的结果：");
        for (int [] row: a1
        ) {
            for ( int data: row
            ) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }
        //4.获取初始二维数组的有效数值个数——for a1.length(获取数组行的个数) a1[0].length(获取数组列的个数)
        int sum = 0;
        for (int i=0;i<a1.length;i++){
            for (int j=0;j<a1[0].length;j++){
                if (a1[i][j]!=0)
                    sum++;
            }
        }
        //5.创建稀疏数组a2[][]
        int a2 [][] = new int[sum+1][3];
        //6.初始化稀疏数组的第一行数据
        a2[0][0] = a1.length;  //初始数组的行数
        a2[0][1] = a1[0].length;  //初始数组的列数
        a2[0][2] = sum;  //初始数组的有效数组个数
        //7.将有效数组存入稀疏数组当中——for
        int num = 1;
        for (int i = 0; i < a1.length ; i++){
            for (int j =0; j < a1[0].length; j++){
                if (a1[i][j]!=0){
                    a2[num][0] = i;
                    a2[num][1] = j;
                    a2[num][2] = a1[i][j];
                    num++;
                }
            }
        }
        //6.将稀疏数组打印
        System.out.println("\n"+"稀疏数组的结果：");
        for (int [] row: a2
        ) {
            for (int data: row
            ) {
                System.out.print(data+"\t");
            }
           System.out.println();
        }
      /*  //7.将稀疏数组存入指定文件
        File file = new File("SparseArray.txt");
        FileOutputStream out = new FileOutputStream(file);
        String content = "数值\t行数\t列数\t\n";
        out.write(Byte.valueOf(content));
        for(int i=1;i<a2.length;i++){
            content = a2[i][0]+"\t"+a2[i][1]+"\t"+a2[i][2]+"\n";
            out.write(content.getBytes());
        }
        out.close();*/
    }
}
