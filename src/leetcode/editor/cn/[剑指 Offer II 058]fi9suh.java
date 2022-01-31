//请实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。 
//
// MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里
//的时间是半开区间，即 [start, end), 实数 x 的范围为， start <= x < end。 
//
// 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。 
//
// 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该
//日程安排添加到日历中。 
//
// 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(st
//art, end) 
//
// 
//
// 示例: 
//
// 
//输入:
//["MyCalendar","book","book","book"]
//[[],[10,20],[15,25],[20,30]]
//输出: [null,true,false,true]
//解释: 
//MyCalendar myCalendar = new MyCalendar();
//MyCalendar.book(10, 20); // returns true 
//MyCalendar.book(15, 25); // returns false ，第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预
//定了
//MyCalendar.book(20, 30); // returns true ，第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。 
// 0 <= start < end <= 109 
// 
//
// 
//
// 注意：本题与主站 729 题相同： https://leetcode-cn.com/problems/my-calendar-i/ 
// Related Topics 设计 线段树 有序集合 
// 👍 12 👎 0


package leetcode.editor.cn;

import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendar {
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(start);//小于等于 start 且最接近的key的键值对
        Map.Entry<Integer, Integer> ceilEntry = map.ceilingEntry(start);//大于等于 start 且最接近的key的键值对
        int floor = Integer.MIN_VALUE, ceil = Integer.MAX_VALUE;
        if (floorEntry != null){
            floor = floorEntry.getValue();
        }
        if (ceilEntry != null){
            ceil = ceilEntry.getKey();
        }
        if (floor <= start && end <= ceil){
            map.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)


