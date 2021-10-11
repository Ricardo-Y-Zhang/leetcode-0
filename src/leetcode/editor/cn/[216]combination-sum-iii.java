//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯 
// 👍 366 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        find(1, k, 0, n, 1);

        return res;
    }

    public void find(int round, int k, int sum, int n, int index){
        if (round > k){
            return;
        }

        for (int i = index; i < 10; i++) {
           sum += i;
           temp.add(i);

           if (sum == n && round == k){
               res.add(new ArrayList<Integer>(temp));
           }else if (sum < n && round < k){
               find(round+1, k, sum, n, i+1);
           }

           sum -= i;
           temp.remove(temp.size()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


