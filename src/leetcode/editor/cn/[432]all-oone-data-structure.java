//请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。 
//
// 实现 AllOne 类： 
//
// 
// AllOne() 初始化数据结构的对象。 
// inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。 
// dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例
//保证：在减少计数前，key 存在于数据结构中。 
// getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。 
// getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMin
//Key"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//输出
//[null, null, null, "hello", "hello", null, "hello", "leet"]
//
//解释
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "leet"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= key.length <= 10 
// key 由小写英文字母组成 
// 测试用例保证：在每次调用 dec 时，数据结构中总存在 key 
// 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 176 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class AllOne {
    class Node {
        int count;//该节点对应的次数
        Set<String> set;
        Node l, r;
        Node (int count, String str) {
            this.count = count;
            this.set = new HashSet<>();
            this.set.add(str);
        }
    }
    public void delete(Node node) {//删除节点node
        if (node.l == null) {//不在链表中
            return;
        }
        node.l.r = node.r;
        node.r.l = node.l;
    }
    public void insert(Node node, Node pre) {//将节点node插入在pre之后
        delete(node);//在链表中删除节点
        node.r = pre.r;
        pre.r = node;
        node.l = pre;
        node.r.l = node;
    }
    HashMap<String, Node> map;//字符串key和其所在节点node的映射
    Node head, tail;//双向链表
    public AllOne() {
        map = new HashMap<>();
        head = new Node(-1, "");
        tail = new Node(-1, "");
        head.r = tail;
        tail.l = head;
    }
    
    public void inc(String key) {
        if (!map.containsKey(key)) {//当前key不存在
            if (head.r.count == 1) {//第一个节点的次数为1，则将key插入第一个节点的set中，更新map
                Node node = head.r;
                node.set.add(key);
                map.put(key, node);
            }else{//不为1，新建节点，并插入key，更新map
                Node node = new Node(1, key);
                insert(node, head);//插入链表头部
                map.put(key, node);
            }
        }else{//当前key存在映射
            Node node = map.get(key);//key对应的节点
            if (node.r.count == node.count+1) {//key应插入node的后续节点中
                node.r.set.add(key);
                map.put(key, node.r);
            }else{//key应插入node与node的后续节点间
                Node next = new Node(node.count+1, key);
                insert(next, node);
                map.put(key, next);
            }
            node.set.remove(key);//当前节点node移除key
            if (node.set.size() == 0){//节点对应key的集合为空，删除节点
                delete(node);
            }
        }
    }
    
    public void dec(String key) {
        Node node = map.get(key);
        if (node.count != 1) {
            Node pre = node.l;//前一个节点
            if (pre.count != node.count-1) {//key应插入pre和node之间
                Node newNode = new Node(node.count-1, key);
                insert(newNode,pre);
                map.put(key, newNode);
            }else{//key应插入pre节点
                pre.set.add(key);
                map.put(key, pre);
            }
        }else{//key对应次数为1，删除key即可
            map.remove(key);
        }
        //节点对应key集合为空，删除节点
        node.set.remove(key);
        if (node.set.size() == 0){
            delete(node);
        }
    }
    
    public String getMaxKey() {
        if (tail.l.count == -1) {
            return "";
        }
        return tail.l.set.iterator().next();
    }
    
    public String getMinKey() {
        if (head.r.count == -1) {
            return "";
        }
        return head.r.set.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
//leetcode submit region end(Prohibit modification and deletion)


