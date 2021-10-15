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
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        find(s, 1, "");
        return res;
    }

    public void find(String s, int index, String temp){
        if (index > 4 || s.length() == 0){
            return;
        }

        if (s.charAt(0) == '0'){
            if (index == 4){
                if (s.length() == 1){
                    res.add(new String(temp+"0"));
                }else{
                    return;
                }
            }else {
                find(s.substring(1), index+1, temp + "0.");
            }
        }else {
            if (index == 4){
                if (s.length() > 3){
                    return;
                }
                int num = Integer.parseInt(s);
                if (num >= 0 && num <= 255){
                    res.add(temp + num);
                }
            }else{
                for (int i = 1; i <= 3 && i <= s.length() ; i++) {
                    int num = Integer.parseInt(s.substring(0, i));
                    if (num >= 0 && num <= 255){
                        find(s.substring(i), index+1, temp + num + ".");
                    }
                }
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)


