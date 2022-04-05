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
// 2 <= timePoints.length <= 2 * 10⁴ 
// timePoints[i] 格式为 "HH:MM" 
// 
// Related Topics 数组 数学 字符串 排序 👍 193 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] times = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            times[i] = convert(timePoints.get(i));
        }
        Arrays.sort(times);
        int ans = times[0]-times[times.length-1]+24*60;
        for (int i = 1; i < times.length; i++) {
            ans = Math.min(ans , times[i]-times[i-1]);
        }
        return ans;
    }
    public int convert (String time) {
        int ans = 0;
        ans += 60 * Integer.parseInt(time.substring(0,2));
        ans += Integer.parseInt(time.substring(3));
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
