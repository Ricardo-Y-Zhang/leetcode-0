//给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左
//下角点，(xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等
//概率被返回。 
//
// 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。 
//
// 请注意 ，整数点是具有整数坐标的点。 
//
// 实现 Solution 类: 
//
// 
// Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。 
// int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。 
// 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: 
//["Solution", "pick", "pick", "pick", "pick", "pick"]
//[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
//输出: 
//[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
//
//解释：
//Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
//solution.pick(); // 返回 [1, -2]
//solution.pick(); // 返回 [1, -1]
//solution.pick(); // 返回 [-1, -2]
//solution.pick(); // 返回 [-2, -2]
//solution.pick(); // 返回 [0, 0] 
//
// 
//
// 提示： 
//
// 
// 1 <= rects.length <= 100 
// rects[i].length == 4 
// -10⁹ <= ai < xi <= 10⁹ 
// -10⁹ <= bi < yi <= 10⁹ 
// xi - ai <= 2000 
// yi - bi <= 2000 
// 所有的矩形不重叠。 
// pick 最多被调用 10⁴ 次。 
// 
// Related Topics 水塘抽样 数学 二分查找 有序集合 前缀和 随机化 👍 78 👎 0


import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //获取矩形中的整数点数目
    public int getCount(int[] rect) {
        int a = rect[0], b = rect[1], x = rect[2], y = rect[3];
        return (x-a+1)*(y-b+1);
    }
    int[][] rects;
    int[] pre;
    int max = 0;
    Random random = new Random();
    public Solution(int[][] rects) {
        this.rects = rects;
        int n = rects.length;
        pre = new int[n];
        int sum = 0;
        int i = 0;
        for(int[] rect : rects) {
            sum += getCount(rect);
            pre[i++] = sum-1;
        }
        max = sum;
    }
    
    public int[] pick() {
        int index = random.nextInt(max);
        int l = 0, r = pre.length-1;
        while (l < r) {
            int mid = l+r >> 1;
            if (pre[mid] < index) {
                l = mid+1;
            }else{
                r = mid;
            }
        }
        int a = rects[l][0], b = rects[l][1], x = rects[l][2], y = rects[l][3];
        int tempx = random.nextInt(x-a+1), tempy = random.nextInt(y-b+1);
        return new int[]{a+tempx, b+tempy};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
//leetcode submit region end(Prohibit modification and deletion)
