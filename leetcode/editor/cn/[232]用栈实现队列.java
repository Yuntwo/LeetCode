//请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
//
// 实现 MyQueue 类：
//
//
// void push(int x) 将元素 x 推到队列的末尾
// int pop() 从队列的开头移除并返回元素
// int peek() 返回队列开头的元素
// boolean empty() 如果队列为空，返回 true ；否则，返回 false
//
//
// 说明：
//
//
// 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法
//的。
// 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
//
//
//
//
// 示例 1：
//
//
//输入：
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 1, 1, false]
//
//解释：
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false
//
//
//
//
//
//
//
// 提示：
//
//
// 1 <= x <= 9
// 最多调用 100 次 push、pop、peek 和 empty
// 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
//
//
//
//
// 进阶：
//
//
// 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
//
//
// Related Topics 栈 设计 队列 👍 773 👎 0


import java.util.LinkedList;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 注意之所以栈实现队列和队列实现栈的逻辑不是完全对称的，是因为栈和队列本身的性质就不是对称的
 * 压栈之后出栈是逆序，压队列出队列之后是原序；所以栈倒一次之后正常出栈就是顺序，但是队列倒多少次出队列还是原序，所以只能重排结构而不只是倒一次那么简单
 */
class MyQueue {

    /**
     * 注意LinkedList没有继承Stack，用这个效率也是100%
     */
    private Stack<Integer> queueHead = new Stack<>();

    private Stack<Integer> queueTail = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
        queueTail.push(x);
    }

    public int pop() {
        if (!queueHead.isEmpty()) {
            return queueHead.pop();
        } else {
            while (!queueTail.isEmpty()) {
                queueHead.push(queueTail.pop());
            }
        }
        return queueHead.pop();
    }

    public int peek() {
        if (!queueHead.isEmpty()) {
            return queueHead.peek();
        } else {
            while (!queueTail.isEmpty()) {
                queueHead.push(queueTail.pop());
            }
        }
        return queueHead.peek();
    }

    public boolean empty() {
        return queueHead.isEmpty() && queueTail.isEmpty();
    }

}

/**
 * Success1
 */
//class MyQueue {
//    private LinkedList<Integer> tailStack = new LinkedList<Integer>();
//    private LinkedList<Integer> headStack = new LinkedList<Integer>();
//
//    public MyQueue() {
//
//    }
//
//    public void push(int x) {
//        tailStack.push(x);
//    }
//
//    public int pop() {
//        // 注意一定是要headStack为空的时候再把tailStack倒腾过来
//        // 否则会一截一截地乱序
//        if (headStack.isEmpty()) {
//            if (tailStack.isEmpty()) {
//                return -1;
//            }
//            while (!tailStack.isEmpty()) {
//                headStack.push(tailStack.poll());
//            }
//        }
//        return headStack.poll().intValue();
//    }
//
//    // 注意peek也是要和push一样的在headStack为空的时候倒腾
//    public int peek() {
//        if (headStack.isEmpty()) {
//            if (tailStack.isEmpty()) {
//                return -1;
//            }
//            while (!tailStack.isEmpty()) {
//                headStack.push(tailStack.pop());
//            }
//        }
//        return headStack.peek().intValue();
//    }
//
//    public boolean empty() {
//        return tailStack.isEmpty() && headStack.isEmpty();
//    }
//}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
