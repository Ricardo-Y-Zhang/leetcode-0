//在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能
//围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。 
//
// 
//
// 示例 1: 
//
// 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
//输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
//解释:
//
// 
//
// 示例 2: 
//
// 输入: [[1,2],[2,2],[4,2]]
//输出: [[1,2],[2,2],[4,2]]
//解释:
//
//即使树都在一条直线上，你也需要先用绳子包围它们。
// 
//
// 
//
// 注意: 
//
// 
// 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。 
// 输入的整数在 0 到 100 之间。 
// 花园至少有一棵树。 
// 所有树的坐标都是不同的。 
// 输入的点没有顺序。输出顺序也没有要求。 
// Related Topics 几何 数组 数学 👍 164 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean judge(int[] a, int[] b, int[] c) {//判断向量b->c是否在向量a->b上侧
        int[] x1 = new int[]{b[0]-a[0],b[1]-a[1]};
        int[] x2 = new int[]{c[0]-a[0],c[1]-a[1]};
        return x2[1]*x1[0]-x2[0]*x1[1]>0;
    }
    public int[][] outerTrees(int[][] trees) {
        //按x从小到大，y从小到大排序
        Arrays.sort(trees, (a,b)->{
            return a[0]!=b[0] ? a[0]-b[0]:a[1]-b[1];
        });
        int n = trees.length;
        int[] stack = new int[n+10];//数组模拟栈，方便取栈顶的两个元素
        int top = 0;
        boolean[] isvisit = new boolean[n];
        //上半部分
        for (int i = 0; i < n; i++) {
            int[] c = trees[i];
            while (top >= 2) {//stack中存在两个元素，即存在一条边
                int[] a = trees[stack[top-2]], b = trees[stack[top-1]];
                if (judge(a, b, c)) {
                    isvisit[stack[top-1]] = false;
                    top--;
                }else{
                    break;
                }
            }
            stack[top++] = i;
            isvisit[i] = true;
        }
        //下半部分
        int half = top;
        for (int i = n-1; i >= 0; i--) {
            int[] c = trees[i];
            while (top > half) {//存在边
                int[] a = trees[stack[top-2]], b = trees[stack[top-1]];
                if (judge(a, b, c)) {
                    top--;
                }else{
                    break;
                }
            }
            if (isvisit[i]) continue;//防止重复加入结果中
            stack[top++] = i;
        }

        int[][] ans = new int[top][2];
        for (int i = 0; i < top; i++) {
            ans[i] = trees[stack[i]];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
