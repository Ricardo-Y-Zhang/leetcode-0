//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 601 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArray(int[] nums) {
        buildMaxHeap(nums);
        int n = nums.length;
        //维护大根堆 [0, i]
        for (int i = n-1; i>=1 ; i--) {
            //交换堆顶元素nums[0]和堆的最后一个元素nums[i]，此时nums[i]为堆中最大元素
            swap(nums, 0, i);

            //向下调整维护大根堆nums [0,i-1]
            adjustDown(nums, 0, i-1);
        }
        return nums;
    }

    //建大根堆，堆顶元素即为最大元素
    public void buildMaxHeap(int[] nums) {
        int n = nums.length;
        //从最后一个父节点向下调整
        for (int i = (n-1)/2; i>=0; i--) {
            adjustDown(nums, i, n-1);
        }
    }

    public void adjustDown(int[] nums, int i, int end) {
        while (2*i+1 <= end) {
            int j = 2*i+1;
            if (j+1<=end && nums[j] < nums[j+1]) {
                j++;
            }
            //若父节点比孩子节点大，则交换父节点和孩子节点，并继续向下调整
            if (nums[i] < nums[j]) {
                swap(nums, i, j);
            }else{
                break;
            }
            i = j;
        }
    }

    public void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
