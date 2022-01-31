//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：timePoints = ["23:59","00:00"]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 2 <= timePoints <= 2 * 104 
// timePoints[i] 格式为 "HH:MM" 
// 
//
// 
//
// 注意：本题与主站 539 题相同： https://leetcode-cn.com/problems/minimum-time-difference/ 
// Related Topics 数组 数学 字符串 排序 
// 👍 6 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] times = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            times[i]=toInt(timePoints.get(i));
        }
        Arrays.sort(times);
        int res = 24*60-(times[times.length-1]-times[0]);
        for (int i = 0; i < times.length - 1; i++) {
            int minus = times[i+1]-times[i];
            int minTime = Math.min(minus, 24*60-minus);
            res = Math.min(res, minTime);
        }
        return res;
    }
    public int toInt(String str){
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0])*60+Integer.parseInt(strs[1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


