//给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi
//] 表示服务器 ui 和 vi 之间有一条信息线路，在 一秒 内它们之间可以传输 任意 数目的信息。再给你一个长度为 n 且下标从 0 开始的整数数组 pati
//ence 。 
//
// 题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。 
//
// 编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。每个数据服务器都要向主服务器发送信息，并等待回复。信息在服务器之间按 最优 线路传输，也就
//是说每个信息都会以 最少时间 到达主服务器。主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。 
//
// 在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。从第 1 秒开始，每 一秒最 开始 时，每个数据服务器都会检查它是否收到了主服务器的回复信息（
//包括新发出信息的回复信息）： 
//
// 
// 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。数据服务器 i 每 patience[i] 秒都会重发一条信息，也就是说，数据服务器 i 在上
//一次发送信息给主服务器后的 patience[i] 秒 后 会重发一条信息给主服务器。 
// 否则，该数据服务器 不会重发 信息。 
// 
//
// 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。 
//
// 请返回计算机网络变为 空闲 状态的 最早秒数 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：edges = [[0,1],[1,2]], patience = [0,2,1]
//输出：8
//解释：
//0 秒最开始时，
//- 数据服务器 1 给主服务器发出信息（用 1A 表示）。
//- 数据服务器 2 给主服务器发出信息（用 2A 表示）。
//
//1 秒时，
//- 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
//- 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 < patience[1] = 2），所以不会重发信息。
//- 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。
//
//2 秒时，
//- 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
//- 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
//- 服务器 2 重发一条信息（用 2C 表示）。
//...
//4 秒时，
//- 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
//...
//7 秒时，回复信息 2D 到达服务器 2 。
//
//从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
//所以第 8 秒是网络变空闲的最早时刻。
// 
//
// 示例 2： 
//
// 
//
// 输入：edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
//输出：3
//解释：数据服务器 1 和 2 第 2 秒初收到回复信息。
//从第 3 秒开始，网络变空闲。
// 
//
// 
//
// 提示： 
//
// 
// n == patience.length 
// 2 <= n <= 105 
// patience[0] == 0 
// 对于 1 <= i < n ，满足 1 <= patience[i] <= 105 
// 1 <= edges.length <= min(105, n * (n - 1) / 2) 
// edges[i].length == 2 
// 0 <= ui, vi < n 
// ui != vi 
// 不会有重边。 
// 每个服务器都直接或间接与别的服务器相连。 
// 
// Related Topics 广度优先搜索 图 数组 
// 👍 55 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<ArrayList<Integer>> matrix;
    int n;
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        n = patience.length;
        matrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            matrix.add(new ArrayList<>());
        }
        for (int[] edge : edges){//建图
            int from = edge[0], to = edge[1];
            matrix.get(from).add(to);
            matrix.get(to).add(from);
        }
        int[] dist = dijkstra(0);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int times = (int)Math.ceil((double)2*dist[i]/patience[i]);
            int sum = 2*dist[i] + (times-1)*patience[i]+1;
            ans = Math.max(sum, ans);
        }
        return ans;
    }
    public int[] dijkstra(int src){
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE/2);
        dist[src] = 0;
        boolean[] isvisit = new boolean[n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });//按距离从近到远
        queue.add(new int[]{src,0});
        while (!queue.isEmpty()) {
            int index = queue.poll()[0];
            if (!isvisit[index]){
                isvisit[index] = true;
                for (int to : matrix.get(index)) {
                    if (!isvisit[to] && dist[index]+1<dist[to]){
                        dist[to] = dist[index] +1;
                        queue.add(new int[]{to,dist[to]});
                    }
                }
            }
        }
        return dist;
    }
}
//leetcode submit region end(Prohibit modification and deletion)