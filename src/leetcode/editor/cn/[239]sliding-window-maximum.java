//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1657 👎 0


import java.util.LinkedList;


//leetcode submit region begin(Prohibit modification and deletion)
//单调队列
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                list.removeLast();
            }
            list.offerLast(nums[i]);
        }
        ans[0] = list.peekFirst();
        for (int i = k; i < n; i++) {
            int out = nums[i-k];
            if (list.peekFirst() == out) {
                list.removeFirst();
            }
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                list.removeLast();
            }
            list.offerLast(nums[i]);
            ans[i-k+1] = list.peekFirst();
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
