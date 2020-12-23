package homework.exercise.test4;

import sort.quickSort.QuickSort;

import javax.management.Query;
import java.util.*;

/**
 * 4、如下图所示，从左到右有 A、B、C 三根柱子，其中 A 柱子上面有从小叠到大
 *    的 n 个圆盘，现要求将 A 柱子上的圆盘移到 C 柱子上去，期间只有一个原则：
 *    一次只能移到一个盘子且大盘子不能在小盘子上面，求移动的步骤和移动的次
 *    数（假设 N=12,要求最少写出前 n=1,n=2,n=3 的移动步骤）（出题人：李俊）
 */
public class Test {
    public static void main(String[] args) {
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        Stack<Integer> C = new Stack<>();
        int hanoiNums = 5;
        for (int i = hanoiNums; i > 0; i --) {
            A.add(i);
        }
        new Test().Hanoi(hanoiNums, A, "A", B, "B", C, "C");
        System.out.println(C);
    }

    /**
     *
     * @param hanoiNums 汉诺塔的总个数
     * @param A List 集合塔 A
     * @param B List 集合塔 B
     * @param C List 集合塔 C
     */
    public void Hanoi(int hanoiNums, Stack<Integer> A,String nameA, Stack<Integer> B,String nameB, Stack<Integer> C,String nameC) {
        if (hanoiNums == 1) {  // 当只有一个汉诺塔时直接将汉诺塔从塔 A 移动到塔 C
            remove(hanoiNums, A, nameA, C, nameC);
        }else {
            // 1.先借助 C 塔将 hanoiNums-1 个汉诺塔从 A 塔移动到 B 塔
            Hanoi(hanoiNums-1, A, "A", C, "C", B, "B");
            // 2.将最大的第 hanoiNums 个汉诺塔从 A 塔移动到 C 塔
            remove(hanoiNums, A, nameA, C, nameC);
            // 3. 最后将已经有序的剩下的 hanoiNums-1 个汉诺塔借助 A 塔全部挨个从 B 塔移动到 C 塔
            Hanoi(hanoiNums-1, B,"B", A, "A", C, "C");
        }
    }

    /**
     * 汉诺塔移动方法
     * @param num 移动的的第 num 个汉诺塔
     * @param A 第 num 个汉诺塔的起始柱子
     * @param B 第 num 个汉诺塔的终点柱子
     */
    public void remove(int num, Stack<Integer> A, String nameA, Stack<Integer> B, String nameB) {
        System.out.println("将第"+num+"个汉诺塔从 "+nameA+" :"+A+"塔移动到 "+nameB+" :"+B+"塔上");
        B.add(A.pop());
    }
}
