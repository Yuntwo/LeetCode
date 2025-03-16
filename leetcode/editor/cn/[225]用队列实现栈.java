//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
//
// 实现 MyStack 类：
//
//
// void push(int x) 将元素 x 压入栈顶。
// int pop() 移除并返回栈顶元素。
// int top() 返回栈顶元素。
// boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
//
//
//
//
// 注意：
//
//
// 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
// 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
//
//
//
//
// 示例：
//
//
//输入：
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 2, 2, false]
//
//解释：
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // 返回 2
//myStack.pop(); // 返回 2
//myStack.empty(); // 返回 False
//
//
//
//
// 提示：
//
//
// 1 <= x <= 9
// 最多调用100 次 push、pop、top 和 empty
// 每次调用 pop 和 top 都保证栈不为空
//
//
//
//
// 进阶：你能否仅用一个队列来实现栈。
//
// Related Topics 栈 设计 队列 👍 606 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * END
 */

/**
 * Review2
 * 注意递归的话一定要理解递归的封装部分的结构是什么
 * 这里迭代也是类似的，也就是说你temp的那一部分本身也要是逆序的，然后倒到stack就还是整体逆序的才行
 */
class MyStack {

    private Queue<Integer> stack = new LinkedList<>();

    private Queue<Integer> temp = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        temp.add(x);
        while (!stack.isEmpty()) {
            temp.add(stack.poll());
        }
        Queue<Integer> swap = stack;
        stack = temp;
        temp = swap;
    }

    public int pop() {
        return stack.poll();
    }

    public int top() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        int param_1 = obj.top();
        int param_2 = obj.pop();
        boolean param_3 = obj.empty();
        System.out.println(param_2);
    }
}

/**
 * Review1
 * 就是累积构造了栈的顺序，但是这个构造过程和用一个队列需要的操作次数是一样的，还多一个队列空间
 * 好处是只是peek的话不需要一次循环操作了，但是全部pop需要的操作次数是一样的；然后就是确实是栈的顺序结构
 * 这种固定结构的栈和队列相互实现都是添加/弹出时总有一些不需要循环操作的(当然对于两个队列实现栈来说直接pop的也是
 * 要循环的，不过是在push的时候帮它循环了，和一个队列实现栈的循环先后正好相反，一个队列实现栈是push是不循环，pop的时候循环取出)
 * 但是总的来说还是栈实现队列比队列实现栈循环次数更少，这表明它们并不完全是逻辑对称的
 */
//class MyStack {
//
//    private Queue<Integer> stableSortedStack = new LinkedList<>();
//
//    private Queue<Integer> tempStackOnlyOneTop = new LinkedList<>();
//
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        tempStackOnlyOneTop.add(x);
//        while (!stableSortedStack.isEmpty()) {
//            tempStackOnlyOneTop.add(stableSortedStack.poll());
//        }
//        Queue<Integer> temp = tempStackOnlyOneTop;
//        tempStackOnlyOneTop = stableSortedStack;
//        stableSortedStack = temp;
//    }
//
//    public int pop() {
//        return stableSortedStack.poll();
//    }
//
//    public int top() {
//        return stableSortedStack.peek();
//    }
//
//    public boolean empty() {
//        return stableSortedStack.isEmpty();
//    }
//
//    public static void main(String[] args) {
//        MyStack obj = new MyStack();
//        obj.push(1);
//        obj.push(2);
//        int param_1 = obj.top();
//        int param_2 = obj.pop();
//        boolean param_3 = obj.empty();
//        System.out.println(param_2);
//    }
//}

/**
 * Success2
 * 两个队列实现一个栈
 * 效率100%
 */
//class MyStack {
//
//    private LinkedList<Integer> queue1 = new LinkedList<Integer>();
//    private LinkedList<Integer> queue2 = new LinkedList<Integer>();
//
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        queue2.add(x);
//        while (!queue1.isEmpty()) {
//            queue2.add(queue1.poll());
//        }
//        // 交换queue1和queue2只需要交换引用即可
//        LinkedList<Integer> temp = queue1;
//        queue1 = queue2;
//        queue2 = temp;
//    }
//
//    public int pop() {
//        if (!queue1.isEmpty()) {
//            return queue1.poll().intValue();
//        } else {
//            return -1;
//        }
//    }
//
//    public int top() {
//        if (!queue1.isEmpty()) {
//            return queue1.peek().intValue();
//        } else {
//            // 注意对象null返回的int是-1
//            return -1;
//        }
//    }
//
//    public boolean empty() {
//        return queue1.isEmpty();
//    }
//}

/**
 * Review1
 * 一个队列实现栈，虽然感觉这样循环处理很麻烦，但是时间效率已经是100%了，占的空间也少
 */
//class MyStack {
//
//    private Queue<Integer> queue = new LinkedList<>();
//
//    // 注意不能用PriorityQueue，否则它似乎对元素进行了自动重排
////    private Queue<Integer> queue = new PriorityQueue<>();
//
//    // 注意不能用ArrayList，类型转换错误，它应该没有时限Queue的方法，虽然在写代码时提示了这个类，真的好鸡肋啊
////    private Queue<Integer> queue = new ArrayList<>();
//
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        queue.add(x);
//    }
//
//    public int pop() {
//        for (int i = 0; i < queue.size() - 1; i++) {
//            queue.add(queue.poll());
//        }
//        return queue.poll();
//    }
//
//    public int top() {
//        for (int i = 0; i < queue.size() - 1; i++) {
//            queue.add(queue.poll());
//        }
//        int res = queue.poll();
//        queue.add(res);
//        return res;
//    }
//
//    public boolean empty() {
//        return queue.isEmpty();
//    }
//
//    public static void main(String[] args) {
//        MyStack obj = new MyStack();
//        obj.push(1);
//        obj.push(2);
//        int param_1 = obj.top();
//        int param_2 = obj.pop();
//        boolean param_3 = obj.empty();
//        System.out.println(param_2);
//    }
//}

/**
 * Success1
 * 用一个队列实现栈
 * 效率100%
 */
//class MyStack {
//
//    private LinkedList<Integer> queue = new LinkedList<Integer>();
//
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        queue.add(x);
//        for(int i = 0; i < queue.size()-1; i++){
//            queue.add(queue.poll());
//        }
//    }
//
//    public int pop() {
//        return queue.poll().intValue();
//    }
//
//    public int top() {
//        return queue.peek().intValue();
//    }
//
//    public boolean empty() {
//        return queue.isEmpty();
//    }
//}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
