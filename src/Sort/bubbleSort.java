package Sort;

/**
 * 冒泡排序
 */
public class bubbleSort {
    public static void main(String[] args) {
        int[] nums = {2,4,6,8,6,3,5,6,8,9,4,2,4,73,2,45,63,78,23,13};
        for (int i = 0; i < nums.length-1; i ++){
            for (int j = i+1; j < nums.length; j ++){
                if (nums[i] > nums[j]){
                    int step = nums[i];
                    nums[i] = nums[j];
                    nums[j] = step;
                }
            }
        }

        // 打印排好序的数组
        for (int i = 0; i < nums.length; i ++){
            System.out.print(nums[i]+" ");
        }
    }
}
