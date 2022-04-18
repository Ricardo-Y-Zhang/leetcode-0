//给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values
//[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。 
//
// 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj =
// ? 的结果作为答案。 
//
// 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替
//代这个答案。 
//
// 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"]
//,["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//条件：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
// 
//
// 示例 2： 
//
// 
//输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], quer
//ies = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
// 
//
// 示例 3： 
//
// 
//输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a
//","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 20 
// equations[i].length == 2 
// 1 <= Ai.length, Bi.length <= 5 
// values.length == equations.length 
// 0.0 < values[i] <= 20.0 
// 1 <= queries.length <= 20 
// queries[i].length == 2 
// 1 <= Cj.length, Dj.length <= 5 
// Ai, Bi, Cj, Dj 由小写英文字母与数字组成 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 数组 最短路 
// 👍 724 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int n;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> map = new HashMap<>();
        double[][] matrix = new double[41][41];
        //初始化图
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], -1);//-1代表不连通
            matrix[i][i] = 1;
        }
        //建图
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0), b = equation.get(1);
            if (!map.containsKey(a)) map.put(a, map.size());
            if (!map.containsKey(b)) map.put(b, map.size());
            int ind1 = map.get(a), ind2 = map.get(b);
            matrix[ind1][ind2] = values[i];
            matrix[ind2][ind1] = 1/values[i];
        }
        ////floyd计算多源最短路径
        n = map.size();
        for (int i = 0; i < n; i++) {
            floyd(matrix, i);
        }

        double[] ans = new double[queries.size()];
        int i = 0;
        for (List<String> quer : queries) {
            String a = quer.get(0), b = quer.get(1);
            int ind1 = map.getOrDefault(a, -1), ind2 = map.getOrDefault(b, -1);
            if (ind1==-1||ind2==-1){//节点不存在，或节点不连通
                ans[i++]=-1;
            }else{
                ans[i++]=matrix[ind1][ind2];
            }
        }
        return ans;
    }

    public void floyd(double[][] matrix, int index) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1 && matrix[i][index] != -1 && matrix[index][j] != -1) {
                    matrix[i][j] = matrix[i][index] * matrix[index][j];
                    matrix[j][i] = 1/matrix[i][j];
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


