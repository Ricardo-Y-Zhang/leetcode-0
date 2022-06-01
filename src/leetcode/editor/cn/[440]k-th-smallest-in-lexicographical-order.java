//给定整数 n 和 k，返回 [1, n] 中字典序第 k 小的数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 13, k = 2
//输出: 10
//解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
// 
//
// 示例 2: 
//
// 
//输入: n = 1, k = 1
//输出: 1
// 
//
// 
//
// 提示: 
//
// 
// 1 <= k <= n <= 10⁹ 
// 
// Related Topics 字典树 👍 503 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthNumber(int n, int k) {
        int prefix = 1;
        while (k > 1) {
            int count = getCount(prefix, n);
            if (count < k) {
                k -= count;
                prefix++;
            }else{
                k--;
                prefix*=10;
            }
        }
        return prefix;
    }

    //求该字典树中以 prefix 节点为父节点的树的节点数
    public int getCount (int prefix, int n) {
        int count = 0;
        long first = prefix, last = prefix;//下一层的第一个节点和最后一个节点
        while (first <= n) {
            count += last - first + 1;
            first *= 10;
            last = last * 10 + 9;
            last = last <= n ? last : n;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
