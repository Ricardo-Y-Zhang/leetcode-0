//Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。 
//
// 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。 
//
// 实现 RangeModule 类: 
//
// 
// RangeModule() 初始化数据结构的对象。 
// void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的
//数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。 
// boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返
//回 true ，否则返回 false 。 
// void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入
//["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", 
//"queryRange"]
//[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
//输出
//[null, null, null, true, false, true]
//
//解释
//RangeModule rangeModule = new RangeModule();
//rangeModule.addRange(10, 20);
//rangeModule.removeRange(14, 16);
//rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
//rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样
//的数字）
//rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= left < right <= 10⁹ 
// 在单个测试用例中，对 addRange 、 queryRange 和 removeRange 的调用总数不超过 10⁴ 次 
// 
// Related Topics 设计 线段树 有序集合 👍 131 👎 0


import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class RangeModule {
    class Node{
        Node lc, rc;//左孩，右孩
        int lazy;//懒标记，区间和
        boolean val;
    }
    int N = (int)1e9+10;
    Node root = new Node();//根节点
    public void update(Node node, int start, int end, int left, int right, int val) {
        if(left <= start && right >= end) {
            node.val = val == 1;
            node.lazy = val;
            return;
        }
        int mid = start+end >> 1;
        lazyCreate(node);
        pushdown(node, start, end);
        if(left <= mid) update(node.lc, start, mid, left, right, val);
        if(mid+1 <= right) update(node.rc, mid+1, end, left, right, val);
        pushup(node);
    }

    public boolean query(Node node, int start, int end, int left, int right){
        if (left <= start && right >= end) return node.val;
        lazyCreate(node);
        pushdown(node, start, end);
        int mid = start+end >> 1;
        boolean ans = true;
        if (left <= mid) {
            ans &= query(node.lc, start, mid, left, right);
        }
        if(mid+1 <= right) {
            ans &= query(node.rc, mid+1, end, left, right);
        }
        return ans;
    }

    public void lazyCreate(Node node) {//动态开点
        if (node.lc == null) {
            node.lc = new Node();
        }
        if (node.rc == null) {
            node.rc = new Node();
        }
    }

    public void pushdown(Node node, int start, int end) {
        if (node.lazy == 0){
            return;
        }
        int mid = start+end >> 1;
        if(node.lazy == 1) {
            node.lc.val = true;
            node.rc.val = true;
        }else{
            node.lc.val = false;
            node.rc.val = false;
        }
        node.lc.lazy = node.lazy;
        node.rc.lazy = node.lazy;
        node.lazy = 0;
    }

    public void pushup(Node node) {
        node.val = node.lc.val && node.rc.val;
    }

    public RangeModule() {

    }
    
    public void addRange(int left, int right) {
        update(root, 1, N-1, left, right-1, 1);
    }
    
    public boolean queryRange(int left, int right) {
        return query(root, 1, N-1, left, right-1) ;
    }
    
    public void removeRange(int left, int right) {
        update(root, 1, N-1, left, right-1, -1);
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
