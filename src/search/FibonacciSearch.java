package search;
/**
 *
 * 斐波那契查找算法
 */

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] array = {4,7,9,-3,5,55,43,10,34,78,21,-3,5,73,78,-4,-2,-46,-22,-34,23};
        Arrays.sort(array);
        int findVail = 4;
        int findSign = fibonacciSearch(array,findVail);
        System.out.println("原数组"+Arrays.toString(array));
        System.out.println(findVail+"在数组中位置下标为："+findSign);
    }

    /**
     * 创建 fibonacciCreateArray() 方法创建斐波那契数组
     * @param array 要查找对应数据的有序数组
     * @return 返回根据要查找对象数组的长度创建的斐波那契数组
     */
    static int maxLength = 20;
    public static int[] fibonacciCreateArray() {
        int[] fibonacciArray = new int[maxLength];
        fibonacciArray[0] = 1;
        fibonacciArray[1] = 1;
        for (int i = 2; i < maxLength ; i ++) {
            fibonacciArray[i] = fibonacciArray[i-1] + fibonacciArray[i-2];
        }
        return fibonacciArray;
    }

    /**
     *
     * @param array 查找 findVial值在的array数组对象
     * @param findVail 要寻找的数据的值
     * @return 返回 findVail 在 array 数组对应的下标，如果未查找到就返回 -1
     */
    public static int fibonacciSearch(int[] array, int findVail) {
        int left = 0;  // 数组的左指针
        int right = array.length-1;  // 数组的右指针
        int mid;  // 按照斐波那契黄金分割的 mid 分割线初始值
        int k = 0 ; // 表示斐波那契数组的下标
        int[] fibonacciArray = fibonacciCreateArray();  // 调用方法 fibonacciCreateArray() 方法根据array数组的长度创建斐波那契数组
//        System.out.println(Arrays.toString(fibonacciArray));
        // 获得斐波那契分割数值下标
        while (right >= fibonacciArray[k] - 1) {
            k ++;
        }
        // 因为f[k] 值可能大于 array 的长度，因此我们需要使用 Arrays 类来填充，构造一个新的数组，并指向数组 array
        int[] temp = Arrays.copyOf(array,fibonacciArray[k]);
        for (int i = right + 1; i < temp.length; i ++) {
            temp[i] =  array[right];
        }
//        System.out.println("temp = "+Arrays.toString(temp));
        while (left <= right) {
            mid = left + fibonacciArray[k-1] - 1;
            if (findVail < temp[mid]) {  // 向左边继续寻找
                right = mid-1;
                k --;
            }else if (findVail > temp[mid]) {
                left = mid+1;
                k -= 2;
            }else {
                if (mid <= right) {
                    return mid;
                }else {
                    return right;
                }
            }
        }
        return -1;
    }
}
