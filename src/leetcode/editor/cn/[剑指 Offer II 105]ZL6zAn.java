//给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1
//,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0
//,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出: 6
//解释: 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// 
//输入: grid = [[0,0,0,0,0,0,0,0]]
//输出: 0 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] is either 0 or 1 
// 
//
// 
//
// 注意：本题与主站 695 题相同： https://leetcode-cn.com/problems/max-area-of-island/ 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 21 👎 0


package leetcode.editor.cn;



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] change = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    boolean[][] isvisit;
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        isvisit = new boolean[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isvisit[i][j] == false && grid[i][j] == 1){
                    int num = dfs(grid, i, j);
                    max = Math.max(max, num);
                }
            }
        }
        return max;
    }
    public int dfs(int[][] grid, int i, int j){
        if (grid[i][j] == 0 || isvisit[i][j] == true){
            return 0;
        }
        isvisit[i][j] = true;
        int num = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + change[0][k], y = j + change[1][k];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length && isvisit[x][y] == false && grid[x][y] == 1){
                num += dfs(grid, x, y);
            }
        }
        return num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


