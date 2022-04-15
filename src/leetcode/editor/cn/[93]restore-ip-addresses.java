//给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。 
//
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 
//输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯 
// 👍 703 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> ans = new ArrayList<>();
    char[] chs;
    int n;
    public List<String> restoreIpAddresses(String s) {
        chs = s.toCharArray();
        n = s.length();
        dfs(0, 0, "");
        return ans;
    }
    //index表示当前转换字符下标，count表示已转换字段个数，temp表示当前已转换ip
    public void dfs(int index, int count, String temp) {
        if (index == n && count == 4) {//满足要求
            ans.add(temp);
            return;
        }
        if (index == n || count == 4) {//不满足要求
            return;
        }
        String str = temp;
        if (str.length()!=0) str += ".";
        if (chs[index] == '0'){//特殊处理 0
            str += 0;
            dfs(index+1, count+1, str);

        }else{
            int number = 0;
            for (int i = index; i < n; i++) {
                number = number * 10 + chs[i]-'0';
                if (number > 255) break;
                dfs(i+1, count+1, str+number);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


