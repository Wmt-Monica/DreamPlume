package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插值查找：在原先二分查找的基础上，将 findVial 值参与 mid 值的确定
 * mid = left + (right - left) * (findVial - array[left]) / (array[right] - array[left])
 * 注意：插值查找适用于数据较大，array 中数据的值分布比较均匀的查找，如果 array 数组中的数据的值分布不均匀，该方法不一定比二分查找好
 */
public class InsertVailSort {
    public static void main(String[] args) {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(Math.random()*5+1);
        }
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        List<Integer> findList;
        findList = new InsertVailSort().insertVailSort(array,0,array.length-1, 2);
        System.out.println(findList);
    }

    /**
     * 创建插值查找来寻找 array 数组中的要查找的值的下标集合
     * @param array 查找的数组对象
     * @param left 左索引
     * @param right 右索引
     * @param findVail 查找的值
     * @return 返回数组 array 中等于 findVail 的数据所对应的下标，并以集合的形式返回
     */
    public List<Integer> insertVailSort(int[] array, int left, int right, int findVail) {
        // 当 left > right 说明数组 array 中没有查找到值等于 findVail 的数据
        // findVail < array[0] || findVail > array[array.length-1] 的约束是为了在给 mid 赋值时不超出 array 数组的索引范围
        if (left > right || findVail < array[0] || findVail > array[array.length-1]) {
            List<Integer> findList = new ArrayList<>();
            return findList;  // 返回一个空 List 集合
        }
        // 将 mid 设置成自适应要查找的值 findVail 的位置
        int mid = left + (right - left) * (findVail - array[left]) / (array[right] - array[left]);
        int midVail = array[mid];  // 获取 mid 下标对应 array 的数据的值
        if (findVail > midVail) {  // 如果要查找的值大于中间查找值 midVail 就递归向右查找
           return insertVailSort(array, mid + 1, right, findVail);
        }else if (findVail < midVail) {  // 如果要查找的值大于中间查找值 midVail 就递归向左查找
           return insertVailSort(array, left, mid -1, findVail);
        }else {  // 否则当所查找的 midVail == findVail 就在此 mid 左右两边进行遍历，将 array 中所有的等于 findVail 的数据的下标存入 findList 数据中返回
           List<Integer> findList = new ArrayList<>();
           findList.add(mid);
           int l = mid - 1;  // 从 mid 开始向左遍历 array 的左指针
           int r = mid + 1;  // 从 mid 开始向右遍历 array 的右指针
           while (l >= 0 && array[l] == findVail) {
               findList.add(l--);
           }
           while (r < array.length && array[r] == findVail) {
               findList.add(r++);
           }
           return findList;
       }
    }
}
