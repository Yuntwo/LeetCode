import java.util.*;
//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
//
//
//
// 示例:
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
//
//
//
//
// 提示：
//
//
// 各函数的调用总次数不超过 20000 次
//
//
//
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
// Related Topics 栈 设计 👍 351 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//class MinStack {
//
//    // 注意声明这里也要指明模板类型啊，否则实际生成对象时指定的模板是无效的
//    // 这是辅助计算min的栈
//    // 注意实际上这里存的min是某个元素及其栈下最小的，此元素之上弹出并不会影响下面的这种min关系，不会改变helpStack
//    // 从逻辑上来讲这两个栈是要空一起空的
//    private LinkedList<Integer> helpStack;
//
//    // 这是实际的栈
//    private LinkedList<Integer> minStack;
//
//    // 当前的min
//    // 你维持这个中间变量虽然全局用起来很方便，但是你要更新它，如果是多处操作涉及到了就很不方便
//    // 这样不灵活，更新也需要时间开销，还不如直接根据属性取呢
//    private int min;
//
//    /** initialize your data structure here. */
//    public MinStack() {
//
//        helpStack = new LinkedList<Integer>();
//
//        minStack = new LinkedList<Integer>();
//
//        // 这样不科学，方面这个值不够大；另一方面当栈空之后就min的值获取不到了，就会报错
////        min = 99999;
//    }
//
//    public void push(int x) {
//        if (minStack.size() == 0) {
//            min = x;
//        }
//        minStack.push(x);
//        if (x <= min) {
//            min = x;
//            helpStack.push(x);
//        }
//    }
//
//    public void pop() {
//        int temp = minStack.pop().intValue();
//        if (temp == min) {
//            // 如果弹出的是当前的min，那么辅助栈中的min也要弹出
//            helpStack.pop();
//            // 修改当前的min，但是不弹出
//            // 注意LinkedList是前插法，所以栈顶是peekFirst!!!
//            // 注意这里不着急修改min的值，因为要是栈空了，就获取不到了
////            min = helpStack.peekFirst().intValue();
//            // 从逻辑上来讲能修改min的地方就只有push和pop这里，其它地方都没有自动更新min的操作，所以这里pop必须修改min
//            // 只是可能存在为空的情况，那就由push修改，分类讨论即可
//            if (helpStack.size() != 0) {
//                min = helpStack.peekFirst().intValue();
//            }
//        }
//    }
//
//    // top与pop的区别是没有弹出
//    public int top() {
//        // 注意LinkedList是前插法，所以栈顶是peekFirst!!!
//        int temp = minStack.peekFirst().intValue();
//        return temp;
//    }
//
//    // 只是获取minStack中的最小值，没有弹出操作
//    public int min() {
//        // 注意没有弹出操作
//        // 注意LinkedList是前插法，所以栈顶是peekFirst!!!
//        // 直接在获取min的时候修改min
//        // 但是这样也是不科学的，不获取就不修改min的话在其它判断逻辑中会出错
////        min = helpStack.peekFirst().intValue();
//        return min;
//    }
//
//    public static void main(String[] args) {
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        int res = minStack.min();
//        minStack.pop();
//        res = minStack.top();
//        res = minStack.min();
//    }
//}

// 不维护min变量实际上更快，内存占用更少
class MinStack {

    // 注意声明这里也要指明模板类型啊，否则实际生成对象时指定的模板是无效的
    // 这是辅助计算min的栈
    // 注意实际上这里存的min是某个元素及其栈下最小的，此元素之上弹出并不会影响下面的这种min关系，不会改变helpStack
    // 从逻辑上来讲这两个栈是要空一起空的
    private LinkedList<Integer> helpStack;

    // 这是实际的栈
    private LinkedList<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {

        helpStack = new LinkedList<Integer>();

        minStack = new LinkedList<Integer>();

        // 这样不科学，方面这个值不够大；另一方面当栈空之后就min的值获取不到了，就会报错
//        min = 99999;
    }

    public void push(int x) {
        minStack.push(x);
        if (helpStack.size() == 0) {
            helpStack.push(x);
        } else{
            if (x <= helpStack.peekFirst().intValue()) {
                helpStack.push(x);
            }
        }
    }

    public void pop() {
        int temp = minStack.pop().intValue();
        if (temp == helpStack.peekFirst().intValue()) {
            // 如果弹出的是当前的min，那么辅助栈中的min也要弹出
            helpStack.pop();
        }
    }

    // top与pop的区别是没有弹出
    public int top() {
        // 注意LinkedList是前插法，所以栈顶是peekFirst!!!
        int temp = minStack.peekFirst().intValue();
        return temp;
    }

    // 只是获取minStack中的最小值，没有弹出操作
    public int min() {
        int temp = helpStack.peekFirst().intValue();
        return temp;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int res = minStack.min();
        minStack.pop();
        res = minStack.top();
        res = minStack.min();
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)
