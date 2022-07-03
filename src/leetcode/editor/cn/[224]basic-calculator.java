//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效) 
// '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的) 
// 输入中不存在两个连续的操作符 
// 每个数字和运行的计算将适合于一个有符号的 32位 整数 
// 
// Related Topics 栈 递归 数学 字符串 👍 778 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int calculate(String s) {
        s=s.replaceAll(" ", "");
        Deque<Integer> nums = new ArrayDeque<>();
        nums.addLast(0);//防止负数，加0
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        char[] chs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            if (ch == '(') {
                ops.addLast(ch);
            }else if (ch == ')') {
                while (!ops.isEmpty()) {
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc(nums, ops);
                    }else{
                        ops.pollLast();
                        break;
                    }
                }
            }else{
                if(ch >= '0' && ch <= '9') {
                    int u = 0, j = i;
                    while (j < n && chs[j] >='0' && chs[j] <= '9' ) {
                        u = u*10+(int)(chs[j]-'0');
                    }
                    nums.addLast(u);
                    i = j-1;
                }else{
                    if (i>0&&(chs[i-1] == '(' || chs[i-1] == '+' || chs[i-1] == '-') ) {
                        nums.addLast(0);
                    }
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        calc(nums, ops);
                    }
                    ops.addLast(ch);
                }
            }

        }
        while (!ops.isEmpty()) calc( nums, ops);
        return nums.peekLast();
    }
    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+'?a+b: a-b);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
