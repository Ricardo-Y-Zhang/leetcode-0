//树可以看成是一个连通且 无环 的 无向 图。 
//
// 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信
//息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。 
//
// 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: edges = [[1,2],[1,3],[2,3]]
//输出: [2,3]
// 
//
// 示例 2： 
//
// 
//
// 
//输入: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
//输出: [1,4]
// 
//
// 
//
// 提示: 
//
// 
// n == edges.length 
// 3 <= n <= 1000 
// edges[i].length == 2 
// 1 <= ai < bi <= edges.length 
// ai != bi 
// edges 中无重复元素 
// 给定的图是连通的 
// 
//
// 
//
// 注意：本题与主站 684 题相同： https://leetcode-cn.com/problems/redundant-connection/ 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 
// 👍 12 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] ans = new int[2];
        int[][] matrix = new int[n][n];
        boolean[] isvisit = new boolean[n];
        for (int i = 0; i < n; i++) {
            int a = edges[i][0]-1, b = edges[i][1]-1;
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }
        List<int[]> res = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(j);
            boolean[] isvisit1 = isvisit.clone();
            isvisit1[j] = true;
            int[][] matrix1 = matrix.clone();
            lable : while (!queue.isEmpty()){
                int first = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (matrix1[first][i] == 1){
                        if (isvisit1[i] == true){
                            ans[0] = first+1;
                            ans[1] = i+1;
                            break lable;
                        }else{
                            queue.add(i);
                            isvisit1[i] = true;
                        }
                        matrix1[first][i] = 0;
                        matrix1[i][first] = 0;
                    }
                }
            }
            res.add(ans);
        }
        for (int[] temp : edges){
            if (res.contains(temp)){
                ans = temp;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


