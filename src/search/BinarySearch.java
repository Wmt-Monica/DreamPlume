package search;

import java.util.Arrays;

/**
 * 基础二分查找
 * 注意：二分查找的数组对象必须是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {2,7,4,6,3,2,-2,0,9};
        // 对数组进行排序
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int findVial = 4;
        int findSign = new BinarySearch().binarySearch(array,0, array.length, findVial);
        System.out.println("数组中值为"+findVial+"的下标为："+findSign);
    }

    /**
     * 创建二分查找的方法
     * @param array 查找的数组对象，有序且为增序
     * @param left 查找的左索引
     * @param right 查找的右索引
     * @param findVial 所要查找的值
     * @return 返回所要查找的值所对应数组 array 的下标
     */
    public int binarySearch(int[] array, int left, int right, int findVial) {
        if (left > right) {  // 如果左索引大于右索引。说明在数组对象 array 未查找到 fiandVial 值，就返回 -1
            return -1;
        }
        int mid = (left + right) / 2;  // 获取二分段的分割下标
        int midVial = array[mid];  // 获取要查找的二分段的中间的值

        if (findVial > midVial) {  // 如果要查找的值大于中间的 midVial 值，就递归继续开始从右边查找
            return binarySearch(array, mid+1, right, findVial);
        }else if (findVial < midVial) {  // 如果要查找的值小于中间的 midVial 值，就递归继续开始从左边查找
            return binarySearch(array, left, mid-1, findVial);
        }else {
            // 否则就是当查找的 midVial 值等于 findVial 值，返回其 mid 下标
            return mid;
        }
    }
}
