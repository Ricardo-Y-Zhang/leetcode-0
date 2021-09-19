//给你一个 无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。 
//
// 示例 3： 
//
// 
//输入：intervals = [], newInterval = [5,7]
//输出：[[5,7]]
// 
//
// 示例 4： 
//
// 
//输入：intervals = [[1,5]], newInterval = [2,3]
//输出：[[1,5]]
// 
//
// 示例 5： 
//
// 
//输入：intervals = [[1,5]], newInterval = [2,7]
//输出：[[1,7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= intervals[i][0] <= intervals[i][1] <= 105 
// intervals 根据 intervals[i][0] 按 升序 排列 
// newInterval.length == 2 
// 0 <= newInterval[0] <= newInterval[1] <= 105 
// 
// Related Topics 数组 
// 👍 482 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();

        boolean flag = false;

        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];

            if (flag == false){
                if (newInterval[0] < left){
                    if (newInterval[1] < left){
                        list.add(new int[]{newInterval[0], newInterval[1]});

                        list.add(new int[]{left, right});

                        flag = true;
                    }else if (newInterval[1] >= left){
                        left = newInterval[0];
                        right = Math.max(right, newInterval[1]);

                        newInterval[0] = left;
                        newInterval[1] = right;
                    }
                }else if (left <= newInterval[0] && right >= newInterval[0]){
                    right = Math.max(newInterval[1], right);
                    newInterval[0] = left;
                    newInterval[1] = right;
                }else {
                    list.add(new int[]{left, right});
                }
            }else {
                list.add(new int[]{left, right});
            }

        }

        if (flag == false){
            list.add(new int[]{newInterval[0], newInterval[1]});
        }

        int[][] res = new int[list.size()][2];

        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


