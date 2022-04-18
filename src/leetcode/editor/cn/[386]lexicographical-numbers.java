//给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。 
//
// 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 13
//输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
// 
//
// 示例 2： 
//
// 
//输入：n = 2
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 5 * 104 
// 
// Related Topics 深度优先搜索 字典树 
// 👍 303 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> lexicalOrder(int n) { ;
        List<Integer> list = new ArrayList<>();
        int num = 1;
        Stack<Integer> stack = new Stack<>();
        while (list.size()!=n) {
            if (num<=n) {
                list.add(num);
                stack.add(num);
                num *= 10;//第一个孩子节点
            }else{
                num = stack.pop();
                while (!stack.isEmpty()&&num%10==9){
                    num = stack.pop();
                }
                num++;
            }
        }
        return list;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
