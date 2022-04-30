//有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。 
//
// 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上
//单元格 高于海平面的高度 。 
//
// 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。 
//
// 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可
//流向大西洋 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
//输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
// 
//
// 示例 2： 
//
// 
//输入: heights = [[2,1],[1,2]]
//输出: [[0,0],[0,1],[1,0],[1,1]]
// 
//
// 
//
// 提示： 
//
// 
// m == heights.length 
// n == heights[r].length 
// 1 <= m, n <= 200 
// 0 <= heights[r][c] <= 10⁵ 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 👍 469 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        boolean[][] isvisit = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{0, i});
            isvisit[0][i] = true;
        }
        for (int i = 0; i < m; i++) {
            queue.add(new int[]{i, 0});
            isvisit[i][0] = true;
        }
        int[][] add = {{-1,1,0,0}, {0,0,1,-1}};
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = temp[0]+add[0][i], y = temp[1]+add[1][i];
                if (x >= 0 && x < m && y >= 0&&y < n && !isvisit[x][y] && heights[x][y] >= heights[temp[0]][temp[1]]) {
                    queue.add(new int[]{x,y});
                    isvisit[x][y] = true;
                }
            }
        }

        boolean[][] isvisit1 = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{m-1, i});
            isvisit1[m-1][i] = true;
            if (isvisit[m-1][i]){
                List<Integer> temp = new ArrayList<>();
                temp.add(m-1);
                temp.add(i);
                ans.add(temp);
            }
        }
        for (int i = 0; i < m-1; i++) {
            queue.add(new int[]{i, n-1});
            isvisit1[i][n-1] = true;
            if (isvisit[i][n-1]){
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(n-1);
                ans.add(temp);
            }

        }
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = temp[0]+add[0][i], y = temp[1]+add[1][i];
                if (x >= 0 && x < m && y >= 0&&y < n && !isvisit1[x][y] && heights[x][y] >= heights[temp[0]][temp[1]]) {
                    queue.add(new int[]{x,y});
                    isvisit1[x][y] = true;
                    if (isvisit[x][y]) {
                        List<Integer> temp1 = new ArrayList<>();
                        temp1.add(x);
                        temp1.add(y);
                        ans.add(temp1);
                    }
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
