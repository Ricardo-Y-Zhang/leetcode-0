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
        List<List<Integer>> list = new ArrayList<>();
        //初始化邻接表
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        boolean[] isvisit = new boolean[n];//记录节点是否进入拓扑序列
        int[] in = new int[n];
        HashSet<Integer> set = new HashSet<>();//记录1~n是否均出现在seqs中
        for (List<Integer> seq : seqs){
            for (int i = 0; i < seq.size()-1; i++) {
                int start = seq.get(i)-1, end = seq.get(i+1)-1;
                if (start < 0 || start >= n || end < 0 || end >= n){//判断元素是否属于 1~n
                    return false;
                }
                if (!list.get(start).contains(end)){//去除重复边
                    list.get(start).add(end);
                    in[end]++;//入度
                }
                set.add(start);
            }
            int last = seq.get(seq.size()-1)-1;//只有一个元素的情况
            if (last < 0 || last >= n){
                return false;
            }
            set.add(last);
        }
        if (set.size() != n){
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0){
                queue.add(i);
                isvisit[i] = true;
            }
        }

        int index = 0;
        while (!queue.isEmpty()){
            if (queue.size() != 1){//保证队列中只有一个元素
                return false;
            }
            int first = queue.poll();
            if (first != org[index++]-1){
                return false;
            }
            for (int i = 0; i < list.get(first).size(); i++) {
                int next = list.get(first).get(i);
                if (isvisit[next] == false){
                    in[next]--;
                    if (in[next] == 0){
                        queue.add(next);
                        isvisit[next] = true;
                    }
                }
            }
        }
        return index == n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


