package Sort;

/**
 * 插入排序
 */
public class insertSort {
    public static void main(String[] args) {
        int[] nums = {1,2,5,7,9,4,3,3,5,6,7,87,9,98,42,43,33,45,5,64,66,7,54,32};
        for (int i = 0,j,step; i < nums.length ; i ++){
            step = nums[i];
            for (j = i;j > 0 && step < nums[j-1]; j --){
                nums[j] = nums[j-1];
            }
            nums[j] = step;
        }

        // 打印输出
        for (int i = 0; i < nums.length; i ++){
            System.out.print(nums[i]+" ");
        }
    }
}
