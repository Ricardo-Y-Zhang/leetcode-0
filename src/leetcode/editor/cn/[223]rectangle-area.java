//给你 二维 平面上两个 由直线构成且边与坐标轴平行/垂直 的矩形，请你计算并返回两个矩形覆盖的总面积。 
//
// 每个矩形由其 左下 顶点和 右上 顶点坐标表示： 
//
// 
// 
// 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。 
// 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。 
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
//输出：45
// 
//
// 示例 2： 
//
// 
//输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
//输出：16
// 
//
// 
//
// 提示： 
//
// 
// -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104 
// 
// Related Topics 几何 数学 
// 👍 165 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2-ax1) * (ay2-ay1), area2 = (bx2-bx1) * (by2-by1);//分别为两个矩形的面积
        if (bx1 >= ax2 || ax1 >= bx2 || ay1 >= by2 || by1 >= ay2){
            return area1 + area2;
        }

        int[] x = new int[]{ax1, ax2, bx1, bx2}, y = new int[]{ay1, ay2, by1, by2};
        Arrays.sort(x);
        Arrays.sort(y);

        int area3 = (x[2]-x[1]) * (y[2]-y[1]);

        return area1 + area2 - area3;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


