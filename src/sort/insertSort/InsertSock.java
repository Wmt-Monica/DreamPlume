package sort.insertSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSock {
    public static void main(String[] args) {
        int[] array = new int[20];
        for (int i = 0 ; i < 20; i ++){
            array[i] = (int)(Math.random()*10+1); // [1,100000]
        }
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:ms");

        Date date1 = new Date();
        String insertStartDateTime = format.format(date1);
        System.out.println("插入排序的开始时间："+insertStartDateTime);
        long insertStartTime = System.currentTimeMillis();
        new InsertSock().insert(array);
        Date date2 = new Date();
        String insertEndDateTime = format.format(date2);
        System.out.println("插入排序的结束时间："+insertEndDateTime);
        long insertEndTime = System.currentTimeMillis();

        Date date3 = new Date();
        String buttonStartDateTime = format.format(date3);
        System.out.println("冒泡排序开始时间："+buttonStartDateTime);
        long buttonStartTime = System.currentTimeMillis();
        new InsertSock().button(array);
        Date date4 = new Date();
        String buttonEndDateTime = format.format(date4);
        System.out.println("冒泡排序的结束时间："+buttonEndDateTime);
        long buttonEndTime = System.currentTimeMillis();

        Date date5 = new Date();
        String selectStartDateTime = format.format(date5);
        System.out.println("选择排序开始时间："+selectStartDateTime);
        long selectStartTime = System.currentTimeMillis();
        new InsertSock().select(array);
        Date date6 = new Date();
        String selectEndDateTime = format.format(date6);
        System.out.println("选择排序的结束时间："+selectEndDateTime);
        long selectEndTime = System.currentTimeMillis();

        System.out.println("插入排序时间："+(insertEndTime-insertStartTime));
        System.out.println("冒泡排序时间："+(buttonEndTime-buttonStartTime));
        System.out.println("选择排序时间："+(selectEndTime-selectStartTime));


    }

    /**
     * 插入排序
     * @param array 传入的未排序的数组
     */
    public void insert(int[] array) {
        for (int i = 1; i < array.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (array[i] < array[j]) {
                    int step = array[i];
                    array[i] = array[j];
                    array[j] = step;
                }
            }
            System.out.println("第"+(i-1)+"次排序结果："+Arrays.toString(array));
        }
    }

    /**
     * 冒泡排序
     * @param array 传入为排序的数组
     */
    public void button(int[] array) {
        for (int i = 0; i < array.length-1; i ++) {
            for (int j = 0; j < array.length-1-i; j ++) {
                if (array[j] > array[j+1]){
                    int step = array[j];
                    array[j] = array[j+1];
                    array[j+1] = step;
                }
            }
            System.out.println("第"+(i+1)+"次排序结果："+ Arrays.toString(array));
        }
    }

    /**
     * 选择排序
     * @param array 传入未排序的数组
     */
    public void select(int[] array) {
        for (int i = 0; i < array.length-1; i ++) {
            for (int j = i+1; j < array.length; j ++) {
                if (array[i] > array[j]){
                    int step = array[i];
                    array[i] = array[j];
                    array[j] = step;
                }
            }
            System.out.println("第"+(i+1)+"次排序结果："+Arrays.toString(array));
        }
    }
}
