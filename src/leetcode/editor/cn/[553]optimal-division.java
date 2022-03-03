//给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。 
//
// 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应
//该含有冗余的括号。 
//
// 示例： 
//
// 
//输入: [1000,100,10,2]
//输出: "1000/(100/10/2)"
//解释:
//1000/(100/10/2) = 1000/((100/10)/2) = 200
//但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
//因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
//
//其他用例:
//1000/(100/10)/2 = 50
//1000/(100/(10/2)) = 50
//1000/100/10/2 = 0.5
//1000/100/(10/2) = 2
// 
//
// 说明: 
//
// 
// 输入数组的长度在 [1, 10] 之间。 
// 数组中每个元素的大小都在 [2, 1000] 之间。 
// 每个测试用例只有一个最优除法解。 
// 
// Related Topics 数组 数学 动态规划 
// 👍 148 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //nums[i] 属于 [2, 1000]，a/b < b
    //nums[0] 必定为分子
    //需要添加括号，使 nums [1, n-1]构成最小数
    //nums[0]/(nums[1]/.../nums[n-1])
    public String optimalDivision(int[] nums) {
        if (nums.length == 1){
            return String.valueOf(nums[0]);
        }

        StringBuilder sb = new StringBuilder();
        if (nums.length == 2){
            sb.append(nums[0]);
            sb.append("/");
            sb.append(nums[1]);
            return sb.toString();
        }
        sb.append(nums[0]);
        sb.append("/(");
        for (int i = 1; i < nums.length-1; i++) {
            sb.append(nums[i]);
            sb.append("/");
        }
        sb.append(nums[nums.length-1]);
        sb.append(")");
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
