package sort.merger;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergerSort {
    public static void main(String[] args) {
        int[] array = new int[30];
        for (int i = 0; i < array.length; i ++){
            array[i] = (int)(Math.random()*10+1);
        }
        new MergerSort().open(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    // 创建拆分数组 array 的方法
    public void open(int[] array, int left, int right) {
        if (left < right) {  // 如果左指针小于右指针就进行分割
            int mid = (left + right) / 2;  // 再进行数组的分割是从采用二分法的方法
            //  获得要分割的中轴线的位置下标之后在对左边使用递归在进行分割
            open(array, left, mid);
            // 获得要分割的中轴线的位置下标之后再对右边使用递归再进行分割
            open(array, mid+1, right);
            // 在左递归和右递归将数组分割成不可再分的最小单位时，（一小段只有一个一个数组，就是left == right）
            // 在以最小单位回溯合并
            merge(array, left, mid, right);
        }
    }
    // 创建合并数组 array 的方法
    public void merge(int[] array, int left, int mid, int right) {
        // 首先创建一个临时数组变量用于存储 array 的数据
        int[] temp = new int[right-left+1];
        int i = left;  // 创建循环遍历的左部分的起始下标
        int j = mid+1;  // 创建循环遍历的右部分的起始下标
        int t = 0; // 创建指向 temp 数组添加数据的下标 t

        // 有效的将分割的左右部分的数组的元素有序的添加入原数组 array 中
        // 循环条件是两边的数组有一个还未达到末尾
        while (i <= mid && j <= right) {
            // 比较两边数组的下标 i , j 所指向数组的值的最小值存入临时存储数据的数组 temp 中
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            }else  {
                temp[t++] = array[j++];
            }
        }
        // 将剩余长度更长的数组依次存放入临时数组 temp 中
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        while (j <= right) {
            temp[t++] = array[j++];
        }
        // 将存储与 temp 排好序的有序数据拷贝入array中
        // 注意此处并不是从 array 下标为 0 处开始拷贝，而是从排序的起始 left 开始
        for (int k = 0; k < temp.length; k ++) {
            array[k+left] = temp[k];
        }
    }
}
