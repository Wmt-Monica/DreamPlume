package Sort.ShellSort;

import java.util.Arrays;

/**
 * 希尔的交换算法
 */
public class shellChangeSort {
    public static void main(String[] args) {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i ++){
            array[i] = (int)(Math.random()*20)+1;
        }
        new shellChangeSort().changeSort(array);
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
                 System.out.println("第"+(++count)+"趟排序后的结果："+ Arrays.toString(array));
            }
        }
    }
}
