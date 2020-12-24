package sort.heapSort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i ++) {
            array[i] = (int)(Math.random()*20+1);
        }
        System.out.println(Arrays.toString(array));
        System.out.println("===================================");
        HeapSort heapSort = new HeapSort();
        heapSort.createBigHeap(array);
        System.out.println("结果 = "+Arrays.toString(array));
        System.out.println("===================================");

    }

    public void createBigHeap(int[] array) {
        // 1.使得从最后一个不是叶子节点的父节点开始调用move()方法，与左右子节点进行比较并交换，完成后再继续向上遍历其他的父节点
        for (int i = (array.length/2-1); i >= 0; i --) {
            move(array, i, array.length);
            System.out.println(Arrays.toString(array));
        }

        System.out.println("===================================");
        // 2.从数组的底端从下向上的方向将堆顶元素与末尾元素交换，将最大的元素"沉"到数组的末端
        // 3.由于操作进行了操作二，为了始终满足堆定义，每交换之后再调整一次结构，在循环中进行 交换+调整 步骤，直到整个数组有序
        for (int j = array.length-1; j > 0; j --) {
            // 交换
            int temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            System.out.println("---"+Arrays.toString(array));
            move(array, 0, j);
            System.out.println("---"+Arrays.toString(array));
        }

    }

    public void move(int[] array, int i, int length) {
        int step = array[i];
        for (int k = (2 * i) + 1; k < length; k = (2 * k) + 1) {
            // 让 k 等于左右子树中最大值的子树在数组 array 的下标位置
            if ((k+1) < length && array[k] < array[k+1]) {
                k ++;
            }
            // 如果子树中最大的子树大于父节点就将最大的子树值赋值给父节点
            if (array[k] > step) {
                array[i] = array[k];  // 将父节点处于的数组对应的位置重新赋值
                i = k;
            }else {  // 当子节点不大于父节点就可以直接 break 跳出循环，因为循环冲从二叉树的底端遍历，可以保证该节点一下均满足堆结构
                break;
            }
        }
        // 将最后的子树的值赋值上父节点的值
        array[i] = step;
    }

}
