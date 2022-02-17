//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
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
//
// 
//
// 注意：本题与主站 56 题相同： https://leetcode-cn.com/problems/merge-intervals/ 
// Related Topics 数组 排序 
// 👍 13 👎 0


package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                if (ints[0] != t1[0]){
                    return ints[0]-t1[0];
                }
                return ints[1]-t1[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals){
            if (list.size() == 0){
                list.add(interval);
            }else{
                int[] temp = list.get(list.size()-1);
                if (temp[1] < interval[0]){
                    list.add(interval);
                }else{
                    list.remove(list.size()-1);
                    temp[1] = Math.max(temp[1], interval[1]);
                    list.add(temp);
                }
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


