//给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。 
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
//输入：s = "10203040"
//输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
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
//
// 
//
// 注意：本题与主站 93 题相同：https://leetcode-cn.com/problems/restore-ip-addresses/ 
// Related Topics 字符串 回溯 
// 👍 16 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res;
    List<String> temp;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        dfs(s, 0, 0);
        return res;
    }
    public void dfs(String s, int index, int count){
        if (count == 4){//找到4个ip字段
            if (index == s.length()){//遍历完 s
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < temp.size(); i++) {//组合成 ip
                    sb.append(temp.get(i));
                    if (i != temp.size()-1){
                        sb.append(".");
                    }
                }
                res.add(sb.toString());
            }
            return;
        }
        if (s.length() == index){//遍历完 s
            return;
        }
        if (s.charAt(index) == '0'){//第一个字符为 0
            temp.add("0");
            dfs(s, index+1, count+1);
            temp.remove(temp.size()-1);
        }else{
            for (int i = index; i < s.length(); i++) {
                int num = Integer.parseInt(s.substring(index, i+1));
                if (num <= 255){
                    temp.add(String.valueOf(num));
                    dfs(s, i+1, count+1);
                    temp.remove(temp.size()-1);
                }else{
                    break;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


