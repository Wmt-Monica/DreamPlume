package sort.quickSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序法：是冒泡排序的一个改进，再时间上最坏的结果是和冒泡排序一样 O(n^2),平均所消耗的时间是 O(N*logN)
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = new  int[80000000];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(Math.random()*80000+1);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String startTime = dateFormat.format(date1);
        System.out.println("快速排序法的开始时间："+startTime);
        new QuickSort().quickSort(array,0,array.length-1);
        Date date2 = new Date();
        String endTime = dateFormat.format(date2);
        System.out.println("快速排序法的结束时间："+endTime);
        System.out.println(Arrays.toString(array));
    }

    public void quickSort(int[] array, int left, int right) {
        if (left > right) {  // 如果左下表大于右下标就 return 返回
            return;
        }
        int l = left;  // 二分遍历的左下标
        int r = right;  // 二分遍历的右下标
        int step = array[left];  // 基准位的数初始化设置为二分段的左下标所对应的数
        while (l < r) {  // 循环条件是左下标一定要比右下标小
            // 由于基准位设定是最左边，所以先开始移动右下标的位置
            while (array[r] >= step && l < r) {  // 移动右下标的条件是右下标所对应的数大于等于基准位的值同时左下标小于右下标
                r --;
            }
            while (array[l] <= step && l < r) {  // 移动左下标的条件是左下标所对应的数小于等于基准位的值同时左下标小于右下标
                l ++;
            }
            if (l < r) {  // 如果再找到一个大于基准位的值和小于基准位的值的下标且这两个小标始终保持左下标小于右下标就交换
                int t = array[l];
                array[l] = array[r];
                array[r] = t;
            }
        }
        // 在经过第一次交换之后找到了基准位所对应值的下标位置 i,将其下标为 i 的数与基准位的数交换
        array[left] = array[l];
        array[l] = step;
        // 最终完整的完成了一个交换的过程之后，在交换位置后的基准位为分割线，进行二分，再将左边进行同样的操作
        quickSort(array,left,r-1);
        // 在左边完成一次递归的交换之后再进行右边的递归交换
        quickSort(array,l+1,right);
    }
}
