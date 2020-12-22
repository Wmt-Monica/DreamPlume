package homework.exercise.test1;

import java.util.Arrays;

/**
 * 1、请实现一维字符数组的插入排序，并写出测试用例。（出题人：崔延斌）
 */
public class Test {
    public static void main(String[] args) {
        char[] chars = new char[20];
        for (int i = 0; i < chars.length ; i ++) {
            chars[i] = (char)(int)(Math.random()*('z'-'a')+'a');
        }
        new Test().insertSort(chars);
        System.out.println(Arrays.toString(chars));
    }
    public void insertSort(char[] chars) {
        for (int i = 1,j; i < chars.length; i ++) {
            char step = chars[i];
            for (j = i; j > 0 && chars[j-1] < step; j --) {  // 降序
                chars[j] = chars[j-1];
            }
            chars[j] = step;
        }
    }
}
