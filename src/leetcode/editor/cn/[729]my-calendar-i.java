//实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。 
//
// 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。 
//
// 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为， start <= x < e
//nd 。 
//
// 实现 MyCalendar 类： 
//
// 
// MyCalendar() 初始化日历对象。 
// boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 fa
//lse 并且不要将该日程安排添加到日历中。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyCalendar", "book", "book", "book"]
//[[], [10, 20], [15, 25], [20, 30]]
//输出：
//[null, true, false, true]
//
//解释：
//MyCalendar myCalendar = new MyCalendar();
//myCalendar.book(10, 20); // return True
//myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了
//。
//myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20
// ，且不包含时间 20 。 
//
// 
//
// 提示： 
//
// 
// 0 <= start < end <= 109 
// 每个测试用例，调用 book 方法的次数最多不超过 1000 次。 
// 
// Related Topics 设计 线段树 有序集合 
// 👍 117 👎 0


package leetcode.editor.cn;

import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
/*
class MyCalendar {
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> floorentry = map.floorEntry(start);
        Map.Entry<Integer, Integer> ceilentry = map.ceilingEntry(start);
        int floor = Integer.MIN_VALUE , ceil = Integer.MAX_VALUE;
        if (floorentry!=null) {
            floor = floorentry.getValue();
        }
        if (ceilentry != null) {
            ceil = ceilentry.getKey();
        }
        if (floor <= start && end <= ceil) {
            map.put(start, end);
            return true;
        }
        return false;
    }
}

 */

class MyCalendar {
    class Node{
        int lc, rc, add, val;
    }
    int N = (int)1e9, M = 120010;
    int count = 1;
    Node[] tree = new Node[M];
    public void update(int u, int lc, int rc, int left, int right, int val) {
        if (left <= lc && rc <= right) {
            tree[u].val += (rc-lc+1) * val;
            tree[u].add += val;
            return;
        }
        lazyCreate(u);
        int mid = lc+rc >> 1;
        if (left <= mid) update(tree[u].lc, lc, mid, left, right, val);
        if (mid+1 <= right) update(tree[u].rc, mid+1, rc, left, right, val);
        pushup(u);
    }
    public int query(int treeIndex, int lc, int rc, int left, int right) {
        if (left <= lc && rc <= right) return tree[treeIndex].val;
        lazyCreate(treeIndex);
        pushdown(treeIndex);
        int mid = lc+rc >> 1;
        int ans = 0;
        if (left <= mid) {
            ans = Math.max(ans, query(tree[treeIndex].lc,lc, mid, left, right ));
        }
        if (mid+1 <= right) {
            ans = Math.max(ans, query(tree[treeIndex].rc, mid+1, rc, left, right));
        }
        return ans;
    }
    public void lazyCreate(int u) {//动态开点
        if (tree[u] == null) tree[u] = new Node();
        if (tree[u].lc == 0) {
            tree[u].lc = ++count;
            tree[tree[u].lc] = new Node();
        }
        if (tree[u].rc == 0) {
            tree[u].rc = ++count;
            tree[tree[u].rc] = new Node();
        }
    }
    public void pushdown(int u) {
        tree[tree[u].lc].add += tree[u].add; tree[tree[u].rc].add += tree[u].add;
        tree[tree[u].lc].val += tree[u].add; tree[tree[u].rc].val += tree[u].add;
        tree[u].add = 0;
    }
    public void pushup(int u) {
        tree[u].val = Math.max(tree[tree[u].lc].val, tree[tree[u].rc].val);
    }
    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        if (query(1, 1, N+1, start+1, end) > 0) return false;
        update(1, 1, N+1, start+1, end, 1);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)


