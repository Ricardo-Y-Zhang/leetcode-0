//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。 
//
// 实现 LFUCache 类： 
//
// 
// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象 
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。 
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 
//capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。 
// 
//
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。 
//
// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入：
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", 
//"get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//输出：
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//解释：
//// cnt(x) = 键 x 的使用计数
//// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // 返回 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // 返回 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3 
//
// 
//
// 提示： 
//
// 
// 0 <= capacity <= 10⁴ 
// 0 <= key <= 10⁵ 
// 0 <= value <= 10⁹ 
// 最多调用 2 * 10⁵ 次 get 和 put 方法 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 558 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {
    class Node{//双向链表节点
        int key, value;
        Node pre, next;
        Node(){};
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    class Nodes{
        int frequency;
        Nodes pre, next;
        Node head, tail;//双向链表的头尾节点
        Nodes(){//初始化双向链表
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        };
        Nodes(int frequency) {
            this.frequency = frequency;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }
        void deleteNode(Node node) {//删除节点node
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        void addToHead(Node node) {//将节点node添加至双向链表表头
            node.next = head.next;
            node.next.pre = node;
            node.pre = head;
            head.next = node;
        }
    }
    Nodes heads, tails;
    HashMap<Integer, Node> keyToNode = new HashMap<>();
    HashMap<Integer, Nodes> keyToNodes = new HashMap<>();
    int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        heads = new Nodes();
        tails = new Nodes();
        heads.next = tails;
        tails.pre = heads;
    }
    
    public int get(int key) {
        if (capacity == 0) return -1;
        if (!keyToNode.containsKey(key)) return -1;
        Nodes nodes = keyToNodes.get(key);
        Node node = keyToNode.get(key);
        nodes.deleteNode(node);//将节点node从frequency的双向链表中删除
        Nodes preNodes = nodes.pre;
        //node插入frequency+1中
        if (preNodes.frequency == nodes.frequency + 1) {
            preNodes.addToHead(node);
        }else{//新建frequency+1节点
            preNodes = new Nodes(nodes.frequency+1);
            preNodes.addToHead(node);
            //frequency+1插入frequency前
            preNodes.next = nodes;
            preNodes.pre = nodes.pre;
            nodes.pre = preNodes;
            preNodes.pre.next = preNodes;
        }
        keyToNodes.put(key, preNodes);
        if (nodes.head.next == nodes.tail) {//frequency为空，则删除frequency
            nodes.pre.next = nodes.next;
            nodes.next.pre = nodes.pre;
        }
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        if(keyToNode.containsKey(key)) {
            Nodes nodes = keyToNodes.get(key);
            Node node = keyToNode.get(key);
            node.value = value;

            //将node从frequency中删除，插入frequency+1中
            nodes.deleteNode(node);
            Nodes preNodes = nodes.pre;
            if (preNodes.frequency == nodes.frequency + 1) {
                preNodes.addToHead(node);
            }else{//新建frequency+1
                preNodes = new Nodes(nodes.frequency+1);
                preNodes.addToHead(node);
                preNodes.next = nodes;
                preNodes.pre = nodes.pre;
                nodes.pre = preNodes;
                preNodes.pre.next = preNodes;
            }
            keyToNodes.put(key, preNodes);

            //frequency为空，删除frequency
            if (nodes.head.next == nodes.tail) {
                nodes.pre.next = nodes.next;
                nodes.next.pre = nodes.pre;
            }
        }else{
            //缓存已满，删除操作
            if (keyToNodes.size() == capacity) {
                //获取最小的frequency
                Nodes lastNodes = tails.pre;
                //获取该frequency中双向链表中最后一个节点
                Node lastNode = lastNodes.tail.pre;
                //删除该节点
                int deletekey = lastNode.key;
                keyToNode.remove(deletekey);
                keyToNodes.remove(deletekey);
                lastNodes.deleteNode(lastNode);
                //frequency为空，删除frequency
                if (lastNodes.head.next == lastNodes.tail) {
                    lastNodes.pre.next = lastNodes.next;
                    lastNodes.next.pre = lastNodes.pre;
                }
            }

            //添加新节点
            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);

            //将newNode添加到frequency=1的双向链表中
            Nodes nodes = tails.pre;
            if (nodes.frequency != 1) {//新建frequency=1
                nodes = new Nodes(1);
                tails.pre.next = nodes;
                nodes.pre = tails.pre;
                nodes.next = tails;
                tails.pre = nodes;
            }
            nodes.addToHead(newNode);
            keyToNodes.put(key, nodes);
        }
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
