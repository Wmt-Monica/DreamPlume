package sort;

/**
 * 哨兵法
 */
public class InsertSort2 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        int i,j;
        for (i = 2; i < nums.length; i ++) {
            nums[0] = nums[i];
            for (j = i-1; nums[j] > nums[0];  j --) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = nums[0];
        }

        // 打印输出
        for (i = 0; i < nums.length; i ++){
            System.out.print(nums[i]+" ");
        }
    }
}
