//给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|'
// 表示一支 蜡烛 。 
//
// 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[left
//i...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边
// 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。 
//
// 
// 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符
//串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。 
// 
//
// 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。 
//
// 
//
// 示例 1: 
//
// 
//
// 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
//输出：[2,3]
//解释：
//- queries[0] 有两个盘子在蜡烛之间。
//- queries[1] 有三个盘子在蜡烛之间。
// 
//
// 示例 2: 
//
// 
//
// 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16
//]]
//输出：[9,0,0,0,0]
//解释：
//- queries[0] 有 9 个盘子在蜡烛之间。
//- 另一个查询没有盘子在蜡烛之间。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= s.length <= 105 
// s 只包含字符 '*' 和 '|' 。 
// 1 <= queries.length <= 105 
// queries[i].length == 2 
// 0 <= lefti <= righti < s.length 
// 
// Related Topics 数组 字符串 二分查找 前缀和 
// 👍 61 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    HashMap<Integer, Integer> map;
    List<Integer> list;
    boolean flag;
    public int[] platesBetweenCandles(String s, int[][] queries) {
        map = new HashMap<>();//存储蜡烛前的盘子数量
        list = new ArrayList<>();//存储蜡烛的下标
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*'){
                num++;
            }else{
                map.put(i, num);
                list.add(i);
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < ans.length; i++) {
            int left = get2(queries[i][0]), right = get1(queries[i][1]);
            if (left== -1 || right == -1 || left >= right) ans[i] = 0;//左右边界不存在，或左边界 >= 右边界
            else ans[i] = map.get(right)-map.get(left);
        }
        return ans;
    }
    public int get1(int target){//找最近的小于等于target的数
        if (list.size() == 0){
            return -1;
        }
        int left = 0, right = list.size()-1;
        while (left < right){
            int mid = (left+right+1)/2;
            if (list.get(mid) <= target){
                left = mid;
            } else if (list.get(mid) > target){
                right = mid-1;
            }
        }
        int temp = list.get(left);
        return temp <= target ? temp : -1;
    }
    public int get2(int target){//找最近的大于等于target的数
        if (list.size() == 0){
            return -1;
        }
        int left = 0, right = list.size()-1;
        while (left < right){
            int mid = (left+right)/2;
            if (list.get(mid) < target){
                left = mid+1;
            } else if (list.get(mid) >= target){
                right = mid;
            }
        }
        int temp = list.get(left);
        return temp >= target ? temp : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


