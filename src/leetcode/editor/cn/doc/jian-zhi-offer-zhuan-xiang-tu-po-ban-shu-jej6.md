### 解题思路
利用单调栈，求每个柱子作为矩形高度时的面积，取最大。

即要找到左右两侧比该柱子矮的两个柱子的坐标（最近的），面积为两侧矮柱子之间的宽度乘以该柱子的高度。

如图，柱子B两侧比它矮的最近的柱子为柱A、C,则以柱子B为矩形高度时的面积为

(C坐标-A坐标-1) * B高度

![2021-09-17_11-18-13.png](https://pic.leetcode-cn.com/1631848909-fuVvvb-2021-09-17_11-18-13.png)

特殊情况：

1、所求柱子左侧无比它矮的，则计算宽度时，将上述公式的 A坐标 改为 -1 计算即可
2、所求柱子右侧无比它矮的，则计算宽度时，将上述公式的 C坐标 改为 heights.length 计算即可

时间复杂度：O(n)
空间复杂度：O(n)

### 代码

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1
                && heights[stack.peek()] >= heights[i]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        while (stack.peek() != -1) {
            int height = heights[stack.pop()];
            int width = heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }
}
```

### 代码 加入注解

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        //用栈存柱子的下标，并保持栈中的柱子高度是递增的
        Deque<Integer> stack = new LinkedList<>();
        //栈中先存入一个柱子，当栈中无柱子时，它代表一个较矮的柱子
        stack.push(-1);

        int maxArea = 0;
        //从左向右遍历直方图，依次找到每个柱子作为矩形高度时的面积，即要找到左右两侧比栈顶柱子矮的柱子
        for (int i = 0; i < heights.length; i++) {
            //stack.peek() != -1表示栈中还有实际的柱子，并且当前柱子比栈顶柱子矮时
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                //此时表示找到了栈顶左右两侧均比它矮的最近的柱子，栈顶柱出栈
                int height = heights[stack.pop()];
                //若stack.peek() == -1，即左侧没有比它矮的柱子，则宽度直接从头计算，-(-1)即可
                int width = i - stack.peek() - 1;
                //以栈顶的柱子为高度时的矩形面积，是两侧均比它矮的最近的柱子距离作为宽度与它高度的乘积
                maxArea = Math.max(maxArea, height * width);
            }
            //当前柱子比栈顶柱子高时，没有找到栈顶右侧的矮柱，入栈。
            stack.push(i);
        }
        //遍历结束后若栈中还有柱子，继续计算
        while (stack.peek() != -1) {
            int height = heights[stack.pop()];
            //每个柱子右侧没有比它矮的柱子，宽度直接从heights.length计算
            int width = heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }
}
```