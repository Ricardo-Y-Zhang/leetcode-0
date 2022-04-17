//给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。 
//
// 两个相邻元素间的距离为 1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
//输出：[[0,0,0],[0,1,0],[0,0,0]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
//输出：[[0,0,0],[0,1,0],[1,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat[i].length 
// 1 <= m, n <= 10⁴ 
// 1 <= m * n <= 10⁴ 
// mat[i][j] is either 0 or 1. 
// mat 中至少有一个 0 
// 
// Related Topics 广度优先搜索 数组 动态规划 矩阵 👍 673 👎 0


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //多中心广度优先搜索
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i],-1);
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0){
                    ans[i][j] = 0;
                    queue.add(new int[]{i,j});
                }
            }
        }
        int[][] add = {{-1,1,0,0},{0,0,-1,1}};
        while (!queue.isEmpty()) {
            int[] first = queue.poll();
            int i = first[0], j = first[1];
            for (int k = 0; k < 4; k++) {//当前 0 的上下左右四个点
                int x = i+add[0][k], y = j + add[1][k];
                if (x>=0&&x<m&&y>=0&&y<n&&ans[x][y]==-1) {//合法且未访问过
                    ans[x][y] = ans[i][j]+1;
                    queue.add(new int[]{x,y});
                }

            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
