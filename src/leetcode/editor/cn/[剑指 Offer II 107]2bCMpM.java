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
// 1 <= m, n <= 104 
// 1 <= m * n <= 104 
// mat[i][j] is either 0 or 1. 
// mat 中至少有一个 0 
// 
//
// 
//
// 注意：本题与主站 542 题相同：https://leetcode-cn.com/problems/01-matrix/ 
// Related Topics 广度优先搜索 数组 动态规划 矩阵 
// 👍 8 👎 0


package leetcode.editor.cn;

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        boolean[][] isvisit = new boolean[m][n];
        int[][] dist = new int[m][n];
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {//将 0 节点加入队列
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0){
                    int[] temp = {i, j};
                    queue.add(temp);
                    isvisit[i][j] = true;
                    dist[i][j] = 0;
                }
            }
        }
        int[][] add = {{-1,1,0,0}, {0,0,-1,1}};
        while (!queue.isEmpty()){
            int[] first = queue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int x = first[0]+add[0][i], y = first[1]+add[1][i];
                if (0<=x&&x<m&&0<=y&&y<n&&isvisit[x][y]==false){
                    int[] temp = {x,y};
                    queue.add(temp);
                    dist[x][y] = dist[first[0]][first[1]]+1;
                    isvisit[x][y]=true;
                }
            }
        }
        return dist;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


