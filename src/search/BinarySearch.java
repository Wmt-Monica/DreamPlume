package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基础二分查找
 * 注意：二分查找的数组对象必须是有序的
 * 更改：将所有的为 findVial 的下标返回
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {2,7,4,6,3,2,-2,0,9,4,5,3,4,-8,3};
        // 对数组进行排序
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int findVial = 4;
        int findSign = new BinarySearch().binarySearch(array,0, array.length, findVial);
        System.out.println("数组中值为"+findVial+"的下标为："+findSign);
        System.out.println("=========================================");
        List<Integer> findList = new ArrayList<>();
        new BinarySearch().binarySearch2(array, 0, array.length, findVial, findList);
        System.out.println(findList);
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


    /**
     * 在binarySearch()方法上，将所有的值为 findVial 的 下标以集合的形式返回
     * @param array 所要查找的数组对象
     * @param left 查找的左索引
     * @param right 查找的右索引
     * @param findVial 所要查找的值
     * @param findList 所查找 findVial 的所有结果的下标
     * @return 返回所查找的 findVial 值的所有下标 List 集合
     */
    public List<Integer> binarySearch2(int[] array, int left, int right , int findVial, List<Integer> findList) {

        if (left > right) {  // 当左索引大于右索引时说明未在 array 数组中查找到值为 findVial 的数据，就返回 -1
           return findList;
        }
        int mid = (left + right) / 2;  // 计算所查找的二分段的所查找的中间分割下标
        int midVial = array[mid];  // 将中间下标的值存入 midVial 中，为下面查找方向做出判断
        if (findVial > midVial) {  // 如果所查找的 findVial 值大于中间下标查找的值，就递归向左查找
            return binarySearch2(array, mid+1, right, findVial, findList);
        }else if (findVial < midVial) {   // 如果所查找的 findVial 值小于中间下标查找的值，就递归向右查找
            return binarySearch2(array, left, mid-1, findVial, findList);
        }else {  // 否则当 findVial == midVial 就将其下标存入 findList 集合中
            findList.add(mid);
            // 由于数组是有序的，将所有的等于要查找的数据的对应的下标存入 findList 集合中，向 mid 下标的左右两边寻找
            int i = mid-1;  // 查找的左下标
            int j = mid+1;  // 查找的右下标
            while (array[i] == findVial) {
                findList.add(i--);
            }
            while (array[j] == findVial) {
                findList.add(j++);
            }
            return findList;
        }
    }
}
