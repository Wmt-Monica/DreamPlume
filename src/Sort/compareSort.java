package Sort;

import java.util.Arrays;

public class compareSort {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < 10; i ++){
            array[i] = (int)(Math.random()*10+1);
        }
        new compareSort().insertSort(array);
//        new test().buttonSort(array);
//        new compareSort().selectSort(array);
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
            System.out.println("第"+(i+1)+"趟排序结果："+ Arrays.toString(array));
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
            System.out.println("第"+(i+1)+"趟排序结果："+ Arrays.toString(array));
        }
    }

    public void insertSort(int[] array){
//        for (int i = 0,j; i < array.length; i ++){
//            int step = array[i];
//            for (j = i; j > 0 && step < array[j-1]; j --){
//                array[j] = array[j-1];
//            }
//            array[j] = step;
//            System.out.println("第"+(i+1)+"趟排序："+Arrays.toString(array));
//        }

        // 哨兵法 首个地址不放具体数据
        for (int i = 2,j; i < array.length; i ++){
            array[0] = array[i];
            for (j = i-1; array[j] > array[0]; j --){
                array[j+1] = array[j];
            }
            array[j+1] = array[0];
            System.out.println("第"+(i-1)+"趟排序："+Arrays.toString(array));
        }
    }
}
