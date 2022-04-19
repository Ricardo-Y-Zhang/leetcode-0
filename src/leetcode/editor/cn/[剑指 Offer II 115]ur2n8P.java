//请判断原始的序列 org 是否可以从序列集 seqs 中唯一地 重建 。 
//
// 序列 org 是 1 到 n 整数的排列，其中 1 ≤ n ≤ 104。重建 是指在序列集 seqs 中构建最短的公共超序列，即 seqs 中的任意序列都
//是该最短序列的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入: org = [1,2,3], seqs = [[1,2],[1,3]]
//输出: false
//解释：[1,2,3] 不是可以被重建的唯一的序列，因为 [1,3,2] 也是一个合法的序列。
// 
//
// 示例 2： 
//
// 
//输入: org = [1,2,3], seqs = [[1,2]]
//输出: false
//解释：可以重建的序列只有 [1,2]。
// 
//
// 示例 3： 
//
// 
//输入: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
//输出: true
//解释：序列 [1,2], [1,3] 和 [2,3] 可以被唯一地重建为原始的序列 [1,2,3]。
// 
//
// 示例 4： 
//
// 
//输入: org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
//输出: true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// org 是数字 1 到 n 的一个排列 
// 1 <= segs[i].length <= 105 
// seqs[i][j] 是 32 位有符号整数 
// 
//
// 
//
// 注意：本题与主站 444 题相同：https://leetcode-cn.com/problems/sequence-reconstruction/ 
// Related Topics 图 拓扑排序 数组 
// 👍 9 👎 0


package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        List<HashSet<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            matrix.add(new HashSet<>());
        }
        int[] in = new int[n];//入度
        HashSet<Integer> set = new HashSet<>();
        for (List<Integer> seq : seqs) {//建图
            for (int i = 0; i<seq.size()-1; i++) {
                int l = seq.get(i)-1, r = seq.get(i+1)-1;
                if (l<0||l>=n||r<0||r>=n) return false;//节点是否合法
                set.add(l);
                if (!matrix.get(l).contains(r)) {
                    matrix.get(l).add(r);
                    in[r]++;
                }
            }
            int last = seq.get(seq.size()-1)-1;
            if (last<0||last>=n) return false;
            set.add(last);
        }
        if (set.size()!=n) return false;//节点未全部出现

        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size()!=1) return false;
            int first = queue.poll();
            list.add(first);
            for (int next : matrix.get(first)) {
                in[next]--;
                if (in[next] == 0){
                    queue.add(next);
                }
            }
        }
        if (list.size()!=n) return false;
        for (int i = 0; i < n; i++) {
            if (list.get(i)!=org[i]-1) return false;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


