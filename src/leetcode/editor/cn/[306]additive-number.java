//累加数是一个字符串，组成它的数字可以形成累加序列。 
//
// 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。 
//
// 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。 
//
// 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。 
//
// 示例 1: 
//
// 输入: "112358"
//输出: true 
//解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
// 
//
// 示例 2: 
//
// 输入: "199100199"
//输出: true 
//解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199 
//
// 进阶: 
//你如何处理一个溢出的过大的整数输入? 
// Related Topics 字符串 回溯 
// 👍 178 👎 0


package leetcode.editor.cn;



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean flag = false;
    public boolean isAdditiveNumber(String num) {
        find(0, num, 0, 0);
        return flag;
    }
    public void find(int index, String num, long prepre, long pre){
        if ((flag == true || num.length() == 0) && index >= 3){
            flag = true;
            return;
        }
        if (num.length() == 0){
            return;
        }

        if (num.charAt(0) == '0'){
            if (index == 0 || index == 1 || prepre+pre == 0){
                find(index+1, num.substring(1), pre, 0);
            }
        }else{
            if (index == 0){
                for (int i = 0; i <= num.length()/3 && i < 17; i++) {
                    find(index+1, num.substring(i+1), pre, Long.parseLong(num.substring(0, i+1)));
                }
            }else if (index == 1){
                for (int i = 0; i <= num.length()/2 && i < 17; i++) {
                    find(index+1, num.substring(i+1), pre, Long.parseLong(num.substring(0, i+1)));
                }
            }else {
                String sum = String.valueOf(prepre+pre);
                if (num.indexOf(sum) == 0) {
                    find(index+1, num.substring(sum.length()), pre, prepre+pre);
                }else{
                    return;
                }
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)


