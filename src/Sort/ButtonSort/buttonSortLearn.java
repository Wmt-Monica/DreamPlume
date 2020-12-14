package Sort.ButtonSort;

import java.util.Arrays;

public class buttonSortLearn {
    public static void main(String[] args) {
        int[] array = {-1,-5,6,3,8,0,-4,7,6,8,9};
//        int[] array = {1,2,3,4,5,6};
        int step;
        boolean flag = false;
        for (int i = 0; i < array.length-1; i ++){
            for (int j = 0; j < array.length-1-i; j ++){
                if (array[j] > array[j+1]){
                    step = array[j];
                    array[j] = array[j+1];
                    array[j+1] = step;
                    flag = true;
                }
            }
            if (flag == false){
                System.out.println("第"+(i+1)+"趟排序结果："+ Arrays.toString(array));
                break;
            }else {
                flag = false;
                System.out.println("第"+(i+1)+"趟排序结果："+ Arrays.toString(array));
            }
        }
    }
}
