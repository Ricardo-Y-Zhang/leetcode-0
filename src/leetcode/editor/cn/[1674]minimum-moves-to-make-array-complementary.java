//给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一
//个整数。 
//
// 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。例如，数组 
//[1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。 
//
// 返回使数组 互补 的 最少 操作次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,4,3], limit = 4
//输出：1
//解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
//nums[0] + nums[3] = 1 + 3 = 4.
//nums[1] + nums[2] = 2 + 2 = 4.
//nums[2] + nums[1] = 2 + 2 = 4.
//nums[3] + nums[0] = 3 + 1 = 4.
//对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,2,1], limit = 2
//输出：2
//解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,1,2], limit = 2
//输出：0
//解释：nums 已经是互补的。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 10⁵ 
// 1 <= nums[i] <= limit <= 10⁵ 
// n 是偶数。 
// 
// Related Topics 数组 哈希表 前缀和 👍 78 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    对于任意nums[i]=a, nums[n-i-1]=b，互补后的数字和为x，均有：
    （1）x=a+b，无需操作
    （2）除（1）外，1+min(a,b)<=x<=limit+max(a,b)，只需一次操作，改变其中一个数
    （3）除（1）（2）外，2<=x<=2*limit，需要两次操作

    令sum[x]为互补后的数字和为x所需的总操作数，遍历所有数对：
    （1）将 [2,2*limit] 区间加上 2，代表此数对需要两次操作
    （2）将 [1+min(a,b), limit+max(a,b)] 区间减去 1，代表此数对需要一次操作
    （3）将 [a+b,a+b] 区间减去 1，代表此数对无需操作

    若暴力，时间复杂度为O(n^2)，超时；多次修改某个区间，输出最终结果，考虑用差分数组
     */
    public int minMoves(int[] nums, int limit) {
        int N = 2*limit+1;
        int[] diff = new int[N+1];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n-i-1];

            //(1）将 [2,2*limit] 区间加上 2，代表此数对需要两次操作
            diff[2] += 2;
            diff[2*limit+1] -= 2;

            //（2）将 [1+min(a,b), limit+max(a,b)] 区间减去 1，代表此数对需要一次操作
            diff[1+ Math.min(a,b)] -= 1;
            diff[limit+Math.max(a,b)+1] += 1;

            //（3）将 [a+b,a+b] 区间减去 1，代表此数对无需操作
            diff[a+b] -= 1;
            diff[a+b+1] += 1;
        }

        int ans = Integer.MAX_VALUE;
        int temp = 0;
        for (int i = 2; i <= 2*limit; i++) {
            temp += diff[i];//和为 i 所需的总操作数
            ans = Math.min(temp, ans);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
