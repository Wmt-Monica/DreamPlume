package sort.shellSort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 希尔的交换算法
 */
public class shellChangeSort {
    public static void main(String[] args) {
//        int[] array = new int[20];
//        for (int i = 0; i < array.length; i ++){
//            array[i] = (int)(Math.random()*20)+1;
//        }
//        new shellChangeSort().changeSort(array);

        // 测试哈希算法的移动排序的所消耗时间复杂度
        int[] array = new int[8000000];
        for (int i = 0; i < 8000000; i ++){
            array[i] = (int)(Math.random()*80000+1);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String startTime = dateFormat.format(date1);
        System.out.println("希尔排序的交换排序开始时间："+startTime);
        new shellChangeSort().changeSort(array);
        Date date2 = new Date();
        String endTime = dateFormat.format(date2);
        System.out.println("希尔排序的交换排序结束时间："+endTime);
    }
    public void changeSort(int[] array) {
        for (int t = array.length/2, count = 0; t > 0; t /= 2) {
            for (int i = t; i < array.length; i ++) {
                 for (int j = i-t; j >= 0; j -= t){
                     if (array[j] > array[j+t]){
                         int step = array[j];
                         array[j] = array[j+t];
                         array[j+t] = step;
                     }
                 }
//                 System.out.println("第"+(++count)+"趟排序后的结果："+ Arrays.toString(array));
            }
        }
    }
}
