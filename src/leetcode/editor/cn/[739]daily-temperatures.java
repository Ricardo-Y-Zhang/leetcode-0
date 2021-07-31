//请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。 
//
// 示例 1: 
//
// 
//输入: temperatures = [73,74,75,71,69,72,76,73]
//输出: [1,1,4,2,1,1,0,0]
// 
//
// 示例 2: 
//
// 
//输入: temperatures = [30,40,50,60]
//输出: [1,1,1,0]
// 
//
// 示例 3: 
//
// 
//输入: temperatures = [30,60,90]
//输出: [1,1,0] 
//
// 
//
// 提示： 
//
// 
// 1 <= temperatures.length <= 105 
// 30 <= temperatures[i] <= 100 
// 
// Related Topics 栈 数组 单调栈 
// 👍 827 👎 0


package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public int[] dailyTemperatures(int[] temperatures) {
//        Stack<Integer> stack1 = new Stack<>();
//        Stack<Integer> stack2 = new Stack<>();
//        int[] wait = new int[temperatures.length];
//
//        for (int i = 0; i < temperatures.length; i++) {
//            if (i == 0){
//                stack1.add(i);
//                stack2.add(temperatures[i]);
//            }else{
//                int now = temperatures[i];
//                while (!stack2.isEmpty() && stack2.peek() < now){
//                    int day = i - stack1.peek();
//                    wait[stack1.peek()] = day;
//                    stack1.pop();
//                    stack2.pop();
//                }
//                stack1.add(i);
//                stack2.add(temperatures[i]);
//            }
//        }
//        return wait;
//    }

    public int[] dailyTemperatures(int[] temperatures){
        Stack<Integer> indexStack = new Stack<>();
        int[] wait = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            if (i != 0) {
                while (!indexStack.isEmpty() && temperatures[indexStack.peek()] < temperatures[i]){
                    wait[indexStack.peek()] = i - indexStack.peek();
                    indexStack.pop();
                }
            }
            indexStack.add(i);
        }

        return wait;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


