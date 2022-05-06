//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1428 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b)->{
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });
        Stack<int[]> stack = new Stack<>();
        for (int[] interval : intervals) {
            if (stack.isEmpty()) {
                stack.add(interval);
            }else{
                if (stack.peek()[1] >= interval[0]) {
                    int[] pre = stack.pop();
                    pre[1] = Math.max(pre[1], interval[1]);
                    stack.add(pre);
                }else{
                    stack.add(interval);
                }
            }
        }
        return stack.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


