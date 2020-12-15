package Sort;

/**
 * 选择排序
 */
public class selectSort {
    public static void main(String[] args) {
       int[] nums = {2,4,6,7,8,4,2,6,8,6,4,3,2,3,45,6,6,6,7,65,4,2,45,4,56,7,7,5,3,75};
       for (int i = 0,j; i < nums.length; i ++){
           int k = i;
           for (j = nums.length-1; j > i; j --){
               if (nums[k] > nums[j]){
                   k = j;
               }
           }
           int step = nums[k];
           nums[k] = nums[j];
           nums[j] = step;
       }

       // 打印排好序的数组
        for (int i = 0; i < nums.length; i ++){
            System.out.print(nums[i]+" ");
        }
    }
}
