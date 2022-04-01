//写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。 
//
// 
//
// 示例: 
//
// 输入: a = 1, b = 1
//输出: 2 
//
// 
//
// 提示： 
//
// 
// a, b 均可能是负数或 0 
// 结果不会溢出 32 位整数 
// 
// Related Topics 位运算 数学 
// 👍 242 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int add(int a, int b) {
        //将 a 看作 a+b 不进位的结果，b 看作 a+b 只算进位的结果
        while (b != 0) {
            int temp = a;
            a = a^b;//不算进位
            b = (temp&b)<<1;
        }
        return a;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


