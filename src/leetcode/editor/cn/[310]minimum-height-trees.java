//树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。 
//
// 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中
// edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。 
//
// 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度
//树 。 
//
// 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。 
//树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, edges = [[1,0],[1,2],[1,3]]
//输出：[1]
//解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。 
//
// 示例 2： 
//
// 
//输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//输出：[3,4]
// 
//
// 
//
// 
// 
//
// 提示： 
//
// 
// 1 <= n <= 2 * 10⁴ 
// edges.length == n - 1 
// 0 <= ai, bi < n 
// ai != bi 
// 所有 (ai, bi) 互不相同 
// 给定的输入 保证 是一棵树，并且 不会有重复的边 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 621 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //链式前向星图
    int N = 20010, M = 2*N, index = 0;
    int[] head = new int[N];//head[i]表示以 i 为起点的最后一条边的编号
    int[] edge = new int[M];//每条边的终点
    int[] next = new int[M];//与该边拥有相同起点的前一条边的编号
    void add(int a , int b) {
        edge[index] = b;
        next[index] = head[a];
        head[a] = index++;
    }

    //f1：节点最大向下高度，f2：节点次最大向下高度，p：节点最大向下高度由哪个节点得到，g：节点最大向上高度
    int[] f1 = new int[N], f2 = new int[N], p = new int[N], g = new int[N];
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Arrays.fill(head, -1);//每个节点最后一条边的编号初始化为-1
        for (int[] e : edges) {//建图
            add(e[0], e[1]);
            add(e[1], e[0]);
        }

        dfs1(0, -1);//更新每个节点最大向下高度
        dfs2(0, -1);//更新每个节点最大向上高度
        int min = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int height = Math.max(f1[i], g[i]);
            if (height < min) {
                min = height;
                ans.clear();
                ans.add(i);
            }else if (height == min) {
                ans.add(i);
            }
        }
        return ans;
    }

    public int dfs1(int u, int fa) {
        for (int i = head[u]; i != -1; i = next[i]) {// i 为以 u 为起点的边的编号
            int j = edge[i];
            if (j == fa) continue;
            int sub = dfs1(j, u) + 1;
            if (sub > f1[u]) {
                f2[u] = f1[u];
                f1[u] = sub;
                p[u] = j;
            }else if (sub > f2[u]) {
                f2[u] = sub;
            }
        }
        return f1[u];
    }

    void dfs2(int u, int fa) {
        for (int i = head[u]; i != -1; i = next[i]) {
            int j = edge[i];
            if (j == fa) continue;
            //向上再向下
            if (p[u] != j) {
                g[j] = Math.max(g[j], f1[u]+1);
            }else {
                g[j] = Math.max(g[j], f2[u]+1);
            }
            //向上
            g[j] = Math.max(g[j], g[u]+1);
            dfs2(j, u);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
