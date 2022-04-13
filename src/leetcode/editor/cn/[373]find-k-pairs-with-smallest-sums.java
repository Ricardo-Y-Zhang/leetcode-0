//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。 
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。 
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// 
//
// 示例 2: 
//
// 
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// 
//
// 示例 3: 
//
// 
//输入: nums1 = [1,2], nums2 = [3], k = 3 
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums1.length, nums2.length <= 105 
// -109 <= nums1[i], nums2[i] <= 109 
// nums1 和 nums2 均为升序排列 
// 1 <= k <= 1000 
// 
// Related Topics 数组 堆（优先队列） 
// 👍 389 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean flag = false;//标识是否交换数组
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        if (n > m) {//优化，使优先队列中元素较少
            flag = true;
            return kSmallestPairs(nums2, nums1, k);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return nums1[ints[0]]+nums2[ints[1]]-nums1[t1[0]]-nums2[t1[1]];
            }
        });
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (k > 0 && !queue.isEmpty()) {
            int[] poll = queue.poll();
            List<Integer> temp = new ArrayList<>();
            int a = poll[0], b = poll[1];
            //保证答案数组元素顺序的正确性
            temp.add(flag ? nums2[b] : nums1[a]);
            temp.add(flag ? nums1[a] : nums2[b]);
            ans.add(temp);
            if (b < m-1){
                queue.add(new int[]{a, b+1});
            }
            k--;
        }
        return ans;
    }
}


//leetcode submit region end(Prohibit modification and deletion)


