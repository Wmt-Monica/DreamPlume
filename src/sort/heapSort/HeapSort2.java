package sort.heapSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序排序性能的测试
 */
public class HeapSort2 {
    public static void main(String[] args) {
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(Math.random()*80000+1);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date1 = new Date();
        System.out.println("堆排序起始运行时间："+new String(dateFormat.format(date1)));
//        System.out.println("array数组初始状态："+ Arrays.toString(array));
        new HeapSort2().createHeapTree(array);
        Date date2 = new Date();
        System.out.println("堆排序结束排序时间："+(new String(dateFormat.format(date2))));
//        System.out.println("array数组堆排序之后的状态"+Arrays.toString(array));
    }

    public void createHeapTree(int[] array) {
        for (int i = array.length-1; i >= 0; i --) {
            move(array, i , array.length);
        }

        for (int j = array.length-1; j > 0; j --) {
            int step = array[j];
            array[j] = array[0];
            array[0] = step;
            move(array,0, j);
        }
    }

    /**
     * 实现从 i 到 length 范围的二叉树让第 i 节点的满足堆结构的一次交换节点操作
     * @param array 未塑造完整堆结构的 array 数组
     * @param i 不是叶子节点的父节点
     * @param length 要排序的二叉树节点数的数组长度
     */
    public void move(int[] array, int i, int length) {
        int step = array[i];
        for (int j = i*2+1; j < length; j = j*2+1) {
            if (j+1 < length && array[j] < array[j+1]) {
                j ++;
            }
            if (array[j] > step) {
                array[i] = array[j];
                i = j;
            }else {
                break;
            }
        }
        array[i] = step;
    }

}
