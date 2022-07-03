//请你设计一个带光标的文本编辑器，它可以实现以下功能： 
//
// 
// 添加：在光标所在处添加文本。 
// 删除：在光标所在处删除文本（模拟键盘的删除键）。 
// 移动：将光标往左或者往右移动。 
// 
//
// 当删除文本时，只有光标左边的字符会被删除。光标会留在文本内，也就是说任意时候 0 <= cursor.position <= currentText.
//length 都成立。 
//
// 请你实现 TextEditor 类： 
//
// 
// TextEditor() 用空文本初始化对象。 
// void addText(string text) 将 text 添加到光标所在位置。添加完后光标在 text 的右边。 
// int deleteText(int k) 删除光标左边 k 个字符。返回实际删除的字符数目。 
// string cursorLeft(int k) 将光标向左移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的
//字符数目。 
// string cursorRight(int k) 将光标向右移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边
//的字符数目。 
// 
//
// 
//
// 示例 1： 
//
// 输入：
//["TextEditor", "addText", "deleteText", "addText", "cursorRight", 
//"cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
//[[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
//输出：
//[null, null, 4, null, "etpractice", "leet", 4, "", "practi"]
//
//解释：
//TextEditor textEditor = new TextEditor(); // 当前 text 为 "|" 。（'|' 字符表示光标）
//textEditor.addText("leetcode"); // 当前文本为 "leetcode|" 。
//textEditor.deleteText(4); // 返回 4
//                          // 当前文本为 "leet|" 。
//                          // 删除了 4 个字符。
//textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
//textEditor.cursorRight(3); // 返回 "etpractice"
//                           // 当前文本为 "leetpractice|". 
//                           // 光标无法移动到文本以外，所以无法移动。
//                           // "etpractice" 是光标左边的 10 个字符。
//textEditor.cursorLeft(8); // 返回 "leet"
//                          // 当前文本为 "leet|practice" 。
//                          // "leet" 是光标左边的 min(10, 4) = 4 个字符。
//textEditor.deleteText(10); // 返回 4
//                           // 当前文本为 "|practice" 。
//                           // 只有 4 个字符被删除了。
//textEditor.cursorLeft(2); // 返回 ""
//                          // 当前文本为 "|practice" 。
//                          // 光标无法移动到文本以外，所以无法移动。
//                          // "" 是光标左边的 min(10, 0) = 0 个字符。
//textEditor.cursorRight(6); // 返回 "practi"
//                           // 当前文本为 "practi|ce" 。
//                           // "practi" 是光标左边的 min(10, 6) = 6 个字符。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length, k <= 40 
// text 只含有小写英文字母。 
// 调用 addText ，deleteText ，cursorLeft 和 cursorRight 的 总 次数不超过 2 * 10⁴ 次。 
// 
// 👍 8 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
/*
TextEditor()用空文本初始化对象。
void addText(string text)将text添加到光标所在位置。添加完后光标在text的右边。
int deleteText(int k)删除光标左边k个字符。返回实际删除的字符数目。
string cursorLeft(int k) 将光标向左移动k次。返回移动后光标左边min(10, len)个字符，
其中len是光标左边的字符数目。
string cursorRight(int k)将光标向右移动k次。返回移动后光标左边min(10, len)个字符，
其中len是光标左边的字符数目。


 */
class TextEditor {
    Stack<Character> stack1, stack2;
    public TextEditor() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void addText(String text) {
        for (char ch : text.toCharArray()) {
            stack1.push(ch);
        }
    }
    
    public int deleteText(int k) {
        int ans = 0;
        while (k > 0 && !stack1.isEmpty()) {
            stack1.pop();
            k--;
            ans++;
        }
        return ans;
    }
    
    public String cursorLeft(int k) {
        while (!stack1.isEmpty() && k>0) {
            stack2.push(stack1.pop());
            k--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10 && !stack1.isEmpty(); i++) {
            sb.append(stack1.pop());
        }
        String ans = sb.reverse().toString();
        for (char ch : ans.toCharArray()) {
            stack1.push(ch);
        }
        return ans;
    }
    
    public String cursorRight(int k) {
        while (!stack2.isEmpty() && k>0) {
            stack1.push(stack2.pop());
            k--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10 && !stack1.isEmpty(); i++) {
            sb.append(stack1.pop());
        }
        String ans = sb.reverse().toString();
        for (char ch : ans.toCharArray()) {
            stack1.push(ch);
        }
        return ans;
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */
//leetcode submit region end(Prohibit modification and deletion)
