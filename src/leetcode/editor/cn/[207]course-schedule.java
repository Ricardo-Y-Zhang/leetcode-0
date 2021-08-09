//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 105 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 899 👎 0


package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //节点入度
        int[] k = new int[numCourses];

        //边
        ArrayList<Integer>[] edge = new ArrayList[numCourses];

        boolean[] flag = new boolean[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int[] temp = prerequisites[i];
            int left = temp[0];
            int right = temp[1];

            //更新入度
            k[left]++;

            //更新边
            if (flag[right] == false){
                ArrayList<Integer> e = new ArrayList<>();
                e.add(left);
                edge[right] = e;
                flag[right] = true;
            }else{
                edge[right].add(left);
            }
        }

        //记录入度为0的节点数
        int nums = 0;

        //存放入度为0的节点
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (k[i] == 0){
                stack.add(i);
                nums++;
            }
        }


        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            if (flag[pop] == true){
                ArrayList<Integer> list = edge[pop];
                for (int temp : list){
                    k[temp]--;
                    if (k[temp] == 0){
                        stack.add(temp);
                        nums++;
                    }
                }
            }
        }

        return nums == numCourses;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


