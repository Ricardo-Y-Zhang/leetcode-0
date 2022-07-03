//实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。 
//
// MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里
//的时间是半开区间，即 [start, end), 实数 x 的范围为， start <= x < end。 
//
// 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。 
//
// 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该
//日程安排添加到日历中。 
//
// 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(
//start, end) 
//
// 
//
// 示例： 
//
// MyCalendar();
//MyCalendar.book(10, 20); // returns true
//MyCalendar.book(50, 60); // returns true
//MyCalendar.book(10, 40); // returns true
//MyCalendar.book(5, 15); // returns false
//MyCalendar.book(5, 10); // returns true
//MyCalendar.book(25, 55); // returns true
//解释： 
//前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
//第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
//第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
//第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
//时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
// 
//
// 
//
// 提示： 
//
// 
// 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。 
// 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。 
// 
// Related Topics 设计 线段树 有序集合 👍 119 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendarTwo {
    class Node{
        int lc, rc, max, add;//max:区间最大值，add:lazy标记
    }
    int N = (int)1e9, M = 120010, count = 1;
    Node[] tree = new Node[M];
    public int query(int u, int l, int r, int left, int right) {
        if (left <= l && r <= right) {
            return tree[u].max;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = l+r >> 1;
        int ans = 0;
        if (left <= mid) {
            ans = Math.max(ans, query(tree[u].lc, l, mid, left, right));
        }
        if (mid+1 <= right) {
            ans = Math.max(ans, query(tree[u].rc, mid+1, r, left, right));
        }
        return ans;
    }
    public void update(int u, int l, int r, int left, int right, int val) {
        if(left <= l && r <= right) {
            tree[u].add += val;
            tree[u].max += val;
            return;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = l+r >> 1;
        if (left <= mid) {
            update(tree[u].lc, l, mid, left, right, val);
        }
        if (mid+1 <= right) {
            update(tree[u].rc, mid+1, r, left, right, val);
        }
        pushup(u);
    }
    public void lazyCreate(int u) {
        if (tree[u] == null) {
            tree[u] = new Node();
        }
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
        tree[tree[u].lc].max += tree[u].add; tree[tree[u].rc].max += tree[u].add;
        tree[u].add = 0;
    }
    public void pushup(int u) {
        tree[u].max = Math.max(tree[tree[u].lc].max, tree[tree[u].rc].max);
    }
    public MyCalendarTwo() {

    }
    
    public boolean book(int start, int end) {
        if (query(1, 1, N+1, start+1, end) >= 2) return false;
        update(1, 1, N+1, start+1, end, 1);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)
