package sort.radixSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数算法排序,对基数算法的运行速度进行测试
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = new int[40000000];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(Math.random()*80000+1);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String startTime = dateFormat.format(date1);
        System.out.println("基数算法的运行开始时间："+startTime);
//        System.out.println("原始无序数组："+Arrays.toString(array));
        new RadixSort().radixSort(array);
//        System.out.println("排序最终结果："+Arrays.toString(array));
        Date date2 = new Date();
        String endTime = dateFormat.format(date2);
        System.out.println("基数算法的运行结束时间："+endTime);
    }
    public void radixSort(int[] array) {
        // 创建存放每一轮排序结果的桶 (二维数组)
        int[][] temp = new int[10][array.length];
        // 计算出要排序数组中最大的数的位数
        int maxDigit = 0;
        for (int i = 0 ; i < array.length; i ++) {
            if (array[i] > maxDigit) {
                maxDigit = array[i];
            }
        }
        // 将最大数与字符串连接在使用字符串的 length()方法可以很巧妙的计算出数字中最大的长度
        maxDigit = (maxDigit+"").length();
        int divisor = 1;  // 在数组中的数字从个位开始获取数字的辅助除数
        // 创建变量 t 用于确定一共要循环获取多少个位数
        for (int t = 0; t < maxDigit; t ++, divisor*= 10) {
            int[] nums = new int[temp.length];  // 创建一维数组 nums 用于存储临时数组 temp 中每个桶中的有效数组个数
            // 创建变量 i 用于循环遍历无序数组 array 中的所有数据
            for (int i = 0 ; i < array.length; i ++) {
                //  使用 '/ maxDigit % 10' 的方式将 array 数组中的数据存入对应的桶 temp 中
                int k = array[i] / divisor % 10;
                temp[k][nums[k]] = array[i];
                nums[k] ++; // 每添加一个有效数字其存放对应的桶的有效数字个数的辅助 nums 数组加一
            }

            for (int j = 0,i = 0; j < temp.length; j++) {
                if (nums[j] != 0) {  // 如果该桶中的有效数字不为0，就从 temp 桶中获取数据拷贝入 array 数组中
                     int m = 0;
                     while (m < nums[j]) {
                         array[i++] = temp[j][m++];
                     }
                     nums[j] = 0;  // 重新将存放 temp 中有效数据个数设置为初始状态 0
                }
            }
        }
    }
}
