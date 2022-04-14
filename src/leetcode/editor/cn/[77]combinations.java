//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 
// 👍 715 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int n, k;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1, 0);
        return ans;
    }
    public void dfs(int index, int num) {//index为当前选择元素，num为集合中已选择元素个数
        if (num == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        if (index == n+1) {
            return;
        }
        temp.add(index);
        dfs(index+1, num+1);
        temp.remove(temp.size()-1);
        dfs(index+1, num);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


