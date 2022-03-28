//给你一个整数 n ，它表示一个 带权有向 图的节点数，节点编号为 0 到 n - 1 。 
//
// 同时给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi, weighti] ，表示从 fromi 到 toi 有一条边
//权为 weighti 的 有向 边。 
//
// 最后，给你三个 互不相同 的整数 src1 ，src2 和 dest ，表示图中三个不同的点。 
//
// 请你从图中选出一个 边权和最小 的子图，使得从 src1 和 src2 出发，在这个子图中，都 可以 到达 dest 。如果这样的子图不存在，请返回 -1
// 。 
//
// 子图 中的点和边都应该属于原图的一部分。子图的边权和定义为它所包含的所有边的权值之和。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,
//4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
//输出：9
//解释：
//上图为输入的图。
//蓝色边为最优子图之一。
//注意，子图 [[1,0,3],[0,5,6]] 也能得到最优解，但无法在满足所有限制的前提下，得到更优解。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
//输出：-1
//解释：
//上图为输入的图。
//可以看到，不存在从节点 1 到节点 2 的路径，所以不存在任何子图满足所有限制。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= n <= 105 
// 0 <= edges.length <= 105 
// edges[i].length == 3 
// 0 <= fromi, toi, src1, src2, dest <= n - 1 
// fromi != toi 
// src1 ，src2 和 dest 两两不同。 
// 1 <= weight[i] <= 105 
// 
// Related Topics 图 最短路 
// 👍 30 👎 0


package leetcode.editor.cn;


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Edge{
        int e;
        int weight;
        public Edge(){}
        public Edge(int edge, int weight){
            this.e = edge;
            this.weight = weight;
        }
    }
    ArrayList<ArrayList<Edge>> matrix, matrix_r;
    int n;
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        matrix = new ArrayList<>();
        matrix_r = new ArrayList<>();
        this.n = n;
        for (int i = 0; i < n; i++) {
            matrix.add(new ArrayList<Solution.Edge>());
            matrix_r.add(new ArrayList<Solution.Edge>());
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            Edge edge1 = new Edge(to, weight);
            matrix.get(from).add(edge1);
            Edge edge2 = new Edge(from, weight);
            matrix_r.get(to).add(edge2);
        }
        long[] dist1 = dijkstra(src1, matrix);
        long[] dist2 = dijkstra(src2, matrix);
        long[] dist3 = dijkstra(dest, matrix_r);
        for (int i = 0; i < n; i++) {
            System.out.println("dist1[i] = " + dist1[i]);
            System.out.println("dist2[i] = " + dist2[i]);
            System.out.println("dist3[i] = " + dist3[i]);
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist1[i] == Long.MAX_VALUE || dist2[i] == Long.MAX_VALUE || dist3[i] == Long.MAX_VALUE){
                continue;
            }
            long dist = dist1[i]+dist2[i]+dist3[i];
            System.out.println("dist = " + dist);
            min = Math.min(min, dist);
        }
        return min == Long.MAX_VALUE ? -1 : min;
    }


    public long[] dijkstra(int src, List<ArrayList<Edge>> matrix) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;//初始化自身距离为0
        boolean[] isvisit = new boolean[n];
        Queue<long[]> queue = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] ints, long[] t1) {
                return (int)(ints[1]-t1[1]);
            }
        });
        queue.add(new long[]{src, 0});
        while (!queue.isEmpty()) {
            int index = (int)queue.poll()[0];
            if (isvisit[index]){
                continue;
            }
            isvisit[index] = true;
            for (Edge edge : matrix.get(index)){
                int to = edge.e, weight = edge.weight;
                if (!isvisit[to] && dist[index]+weight < dist[to]){
                    dist[to] = dist[index] + weight;
                    queue.add(new long[]{to, dist[to]});
                }
            }
        }
        return dist;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
