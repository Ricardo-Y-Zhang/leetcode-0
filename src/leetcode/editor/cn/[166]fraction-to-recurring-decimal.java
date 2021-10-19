//给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。 
//
// 如果小数部分为循环小数，则将循环的部分括在括号内。 
//
// 如果存在多个答案，只需返回 任意一个 。 
//
// 对于所有给定的输入，保证 答案字符串的长度小于 104 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numerator = 1, denominator = 2
//输出："0.5"
// 
//
// 示例 2： 
//
// 
//输入：numerator = 2, denominator = 1
//输出："2"
// 
//
// 示例 3： 
//
// 
//输入：numerator = 2, denominator = 3
//输出："0.(6)"
// 
//
// 示例 4： 
//
// 
//输入：numerator = 4, denominator = 333
//输出："0.(012)"
// 
//
// 示例 5： 
//
// 
//输入：numerator = 1, denominator = 5
//输出："0.2"
// 
//
// 
//
// 提示： 
//
// 
// -231 <= numerator, denominator <= 231 - 1 
// denominator != 0 
// 
// Related Topics 哈希表 数学 字符串 
// 👍 330 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator;
        if (a % b == 0){
            return String.valueOf(a/b);
        }
        StringBuilder sb = new StringBuilder();
        if (a*b < 0){
            sb.append("-");
        }
        a = Math.abs(a);
        b = Math.abs(b);
        sb.append(String.valueOf(a/b) + ".");
        a %= b;
        Map<Long, Integer> map = new HashMap<>();
        while (a != 0) {//辗转相除法
            map.put(a, sb.length());
            a *= 10;
            sb.append(a/b);
            a %= b;
            if (map.containsKey(a)) {
                int index = map.get(a);
                return String.format("%s(%s)", sb.substring(0, index), sb.substring(index));
            }
        }

        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


