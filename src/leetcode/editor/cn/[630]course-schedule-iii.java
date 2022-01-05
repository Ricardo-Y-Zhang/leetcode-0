//这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDay
//i] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。 
//
// 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。 
//
// 返回你最多可以修读的课程数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
//输出：3
//解释：
//这里一共有 4 门课程，但是你最多可以修 3 门：
//首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
//第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
//第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
//第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。 
//
// 示例 2： 
//
// 
//输入：courses = [[1,2]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：courses = [[3,2],[4,3]]
//输出：0
// 
//
// 
//
// 提示: 
//
// 
// 1 <= courses.length <= 104 
// 1 <= durationi, lastDayi <= 104 
// 
// Related Topics 贪心 数组 堆（优先队列） 
// 👍 247 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int scheduleCourse(int[][] courses) {
        //courses按课程结束时间升序排列
        Arrays.sort(courses, new Comparator<int[]>(){
            public int compare(int[] ints1, int[] ints2){
                return Integer.compare(ints1[1], ints2[1]);
            }
        });

        //优先队列，大根堆，记录课程持续时间
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){

            @Override
            public int compare(Integer integer, Integer t1) {
                return t1-integer;
            }
        });
        int sum = 0;
        for (int i = 0; i < courses.length; i++) {
            sum += courses[i][0];
            queue.offer(courses[i][0]);
            if (sum>courses[i][1]){
                sum -= queue.poll();
            }
        }

        return queue.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


