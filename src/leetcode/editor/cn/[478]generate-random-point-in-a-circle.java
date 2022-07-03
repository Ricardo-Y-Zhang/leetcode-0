//给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。 
//
// 实现 Solution 类: 
//
// 
// Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置
// (x_center, y_center) 初始化对象 
// randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入: 
//["Solution","randPoint","randPoint","randPoint"]
//[[1.0, 0.0, 0.0], [], [], []]
//输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
//解释:
//Solution solution = new Solution(1.0, 0.0, 0.0);
//solution.randPoint ();//返回[-0.02493，-0.38077]
//solution.randPoint ();//返回[0.82314,0.38945]
//solution.randPoint ();//返回[0.36572,0.17248] 
//
// 
//
// 提示： 
//
// 
// 0 < radius <= 10⁸ 
// -10⁷ <= x_center, y_center <= 10⁷ 
// randPoint 最多被调用 3 * 10⁴ 次 
// 
// Related Topics 几何 数学 拒绝采样 随机化 👍 131 👎 0


import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    double r, x, y;
    Random random = new Random();
    public Solution(double _r, double _x, double _y) {
        r = _r; x = _x; y = _y;
    }
    public double[] randPoint() {
        while (true) {
            double tempx = random.nextDouble() * 2 * r - r;
            double tempy = random.nextDouble() * 2 * r - r;
            if (tempx*tempx + tempy*tempy <= r*r) {
                return new double[]{tempx+x, tempy+y};
            }
        }
    }
}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
//leetcode submit region end(Prohibit modification and deletion)
