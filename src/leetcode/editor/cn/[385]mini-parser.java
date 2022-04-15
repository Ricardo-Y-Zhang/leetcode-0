//给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。 
//
// 列表中的每个元素只可能是整数或整数嵌套列表 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "324",
//输出：324
//解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
// 
//
// 示例 2： 
//
// 
//输入：s = "[123,[456,[789]]]",
//输出：[123,[456,[789]]]
//解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
//1. 一个 integer 包含值 123
//2. 一个包含两个元素的嵌套列表：
//    i.  一个 integer 包含值 456
//    ii. 一个包含一个元素的嵌套列表
//         a. 一个 integer 包含值 789
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 5 * 104 
// s 由数字、方括号 "[]"、负号 '-' 、逗号 ','组成 
// 用例保证 s 是可解析的 NestedInteger 
// 输入中的所有值的范围是 [-106, 106] 
// 
// Related Topics 栈 深度优先搜索 字符串 
// 👍 123 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)



/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    int index = 0;

    public NestedInteger deserialize(String s) {
        if (s.charAt(index) == '[') {//列表
            index++;
            NestedInteger ni = new NestedInteger();
            while (s.charAt(index) != ']') {//']' 表示解析完毕
                ni.add(deserialize(s));
                if (s.charAt(index) == ',') {//还有其他元素
                    index++;
                }
            }
            index++;
            return ni;
        } else {//只包含整数
            boolean negative = false;
            if (s.charAt(index) == '-') {//负数
                negative = true;
                index++;
            }
            int num = 0;
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }
            if (negative) {
                num *= -1;
            }
            return new NestedInteger(num);
        }
    }
}


//leetcode submit region end(Prohibit modification and deletion)


