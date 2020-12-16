package sort.shellSort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 哈希排序的移动排序，相对与希尔排序的交换排序的效率大大提高
 */
public class shellRemoveSort {
    public static void main(String[] args) {
//        int[] array = new int[20];
//        for (int i = 0; i < array.length; i ++){
//            array[i] = (int)(Math.random()*20+1);
//        }
//        new shellRemoveSort().removeSort(array);

        // 测试哈希算法的移动排序的所消耗时间复杂度
        int[] array = new int[8000000];
        for (int i = 0; i < 8000000; i ++){
            array[i] = (int)(Math.random()*80000+1);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String startTime = dateFormat.format(date1);
        System.out.println("希尔排序的移动排序开始时间："+startTime);
        new shellRemoveSort().removeSort(array);
        Date date2 = new Date();
        String endTime = dateFormat.format(date2);
        System.out.println("希尔排序的移动排序结束时间："+endTime);
    }
    public void removeSort(int[] array) {
        for (int t = array.length/2,count = 0; t > 0; t /= 2) {
            for (int i = t; i < array.length; i ++) {
                int j = i;
                int step = array[j];
                if (array[j] < array[j-t]) {
                    while (j - t >= 0 && step < array[j-t]) {
                        array[j] = array[j-t];
                        j -= t;
                    }
                    array[j] = step;
                }
            }
//            System.out.println("第"+(++count)+"排序结果："+ Arrays.toString(array));
        }
    }
}
