package Sort.SelectSort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择排序
 * 这里将冒泡排序和选择排序进行时间的比较，可以明显看出选择排序的时间比冒泡排序所损耗的时间少很多
 */
public class selectSort {
    public static void main(String[] args) {
        int[] array = new int[100000];
        for (int i = 0; i < 100000; i ++){
            array[i] = (int)Math.random()*(80000)+1; // 随即范围 [1,100000000]
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss");
        Date date1 = new Date();
        String buttonStartTime = dateFormat.format(date1);
        System.out.println("冒泡开始时间："+buttonStartTime);

        long startTime = System.currentTimeMillis();

        new selectSort().buttonSort(array);  // 执行冒泡排序方法

        long endTime = System.currentTimeMillis();
        Date date2= new Date();
        String buttonEndTime = dateFormat.format(date2);
        System.out.println("冒泡结束时间："+buttonEndTime);
        System.out.println("冒泡排序所用时间："+(endTime-startTime)+"ms");

        System.out.println("\n\n==================================\n");

        Date date3 = new Date();  // 将选择排序的开始日期时间存储
        String selectStartTime = dateFormat.format(date3);
        System.out.println("选择开始时间："+selectStartTime);

        long startTime2 = System.currentTimeMillis();

        new selectSort().selectSort(array);  // 执行选择排序的方法

        long endTime2 = System.currentTimeMillis();
        Date date4 = new Date();
        String selectEndTime = dateFormat.format(date4);
        System.out.println("选择结束时间："+selectEndTime);
        System.out.println("选择排序所用时间："+(endTime2-startTime2)+"ms");

    }

    public void buttonSort(int[] array){
        for (int i = 0; i < array.length-1; i ++){
            for (int j = 0; j < array.length-1-i; j ++){
                if (array[j] > array[j+1]){
                    int step = array[j];
                    array[j] = array[j+1];
                    array[j+1] = step;
                }
            }
//            System.out.println("第"+(i+1)+"趟排序结果："+ Arrays.toString(array));
        }
    }

    public void selectSort(int[] array){
        for (int i = 0; i < array.length-1; i ++){
            for (int j = i+1; j < array.length; j ++){
                if (array[i] > array[j]){
                    int step = array[i];
                    array[i] = array[j];
                    array[j] = step;
                }
            }
//            System.out.println("第"+(i+1)+"趟排序结果："+ Arrays.toString(array));
        }
    }
}
