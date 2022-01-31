//
// 运用所掌握的数据结构，设计和实现一个 LRU (Least Recently Used，最近最少使用) 缓存机制 。 
//
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 105 
// 最多调用 2 * 105 次 get 和 put 
// 
// 
//
// 
//
// 进阶：是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 注意：本题与主站 146 题相同：https://leetcode-cn.com/problems/lru-cache/ 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 18 👎 0


package leetcode.editor.cn;


import java.util.HashMap;


//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {
    class Node{
        int key, value;
        Node pre, next;
        public Node(){}
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head = new Node();
    Node tail = new Node();
    int size = 0;
    HashMap<Integer, Node> map;
    public void deleteNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    public void addNode(Node node){//尾插法
        node.next = tail;
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
    }
    public void moveNode(Node node){
        deleteNode(node);
        addNode(node);
    }
    public LRUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap<Integer, LRUCache.Node>();
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        moveNode(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key)){
            if (size == map.size()){
                map.remove(head.next.key);
                deleteNode(head.next);
            }
            Node node = new Node(key,value);
            addNode(node);
            map.put(key, node);
        }else{
            Node node = map.get(key);
            node.value = value;
            moveNode(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)


