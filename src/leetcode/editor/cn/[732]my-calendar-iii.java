//当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。 
//
// 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。 
//
// 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。 
//
// 
// MyCalendarThree() 初始化对象。 
// int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
//[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
//输出：
//[null, 1, 1, 2, 3, 3, 3]
//
//解释：
//MyCalendarThree myCalendarThree = new MyCalendarThree();
//myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
//myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
//myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是
// 2 次预订。
//myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
//myCalendarThree.book(5, 10); // 返回 3
//myCalendarThree.book(25, 55); // 返回 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= start < end <= 10⁹ 
// 每个测试用例，调用 book 函数最多不超过 400次 
// 
// Related Topics 设计 线段树 有序集合 👍 113 👎 0


import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendarThree {
    class Node{
        Node leftChild, rightChild;
        int lazy, val;
    }
    Node root = new Node();
    int N = (int)1e9+10;
    void update(Node node, int start, int end, int left, int right, int val) {
        if(left <= start && right >= end) {
            node.lazy += val;
            node.val += val;
            return;
        }
        pushdown(node, start, end);
        int mid = start+end >> 1;
        if (left <= mid) update(node.leftChild, start, mid, left, right, val);
        if (mid+1 <= right) update(node.rightChild, mid+1, end, left, right, val);
        pushup(node);
    }
    int query(Node node, int start, int end, int left, int right) {
        if (left <= start && end <= right) {
            return node.val;
        }
        pushdown(node, start, end);
        int mid = start+end >> 1;
        int ans = 0;
        if (left <= mid) ans = Math.max(ans, query(node.leftChild, start, mid, left, right));
        if (mid+1 <= right) ans = Math.max(ans, query(node.rightChild, mid+1, end, left, right));
        return ans;
    }
    void pushdown(Node node, int start, int end) {
        if (node.leftChild == null) node.leftChild = new Node();
        if (node.rightChild == null) node.rightChild = new Node();

        node.leftChild.lazy += node.lazy; node.rightChild.lazy += node.lazy;
        node.leftChild.val += node.lazy; node.rightChild.val += node.lazy;
        node.lazy = 0;
    }
    void pushup(Node node) {
        node.val = Math.max(node.leftChild.val, node.rightChild.val);
    }
    public MyCalendarThree() {

    }

    public int book(int start, int end) {
        update(root, 0, N, start, end-1, 1);
        return query(root, 0, N, 0, N);
    }



}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)
