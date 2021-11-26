//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 滑动窗口 单调队列 堆（优先队列） 
// 👍 345 👎 0


package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums.length == 0){
            return new int[0];
        }

        int[] res = new int[nums.length-k+1];
        Deque<Integer> queue = new LinkedList<>();
        int left = 0, right = 0, index = 0;
        queue.addLast(nums[0]);
        while (right != nums.length){
            if (right - left == k-1){
                res[index++] = queue.peekFirst();
                if (queue.peekFirst() == nums[left]){
                    queue.pollFirst();
                }
                left++;
            }else if (right - left < k-1){
                right++;
                if (right == nums.length){
                    break;
                }else{
                    int value = nums[right];
                    while (!queue.isEmpty() && queue.peekLast() < value){
                        queue.pollLast();
                    }
                    queue.addLast(value);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


