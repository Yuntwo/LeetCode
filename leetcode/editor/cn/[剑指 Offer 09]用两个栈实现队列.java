//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 )
//
//
//
// 示例 1：
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
//
//
// 示例 2：
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],[],[5],[2],[],[]]
//输出：[null,-1,null,null,5,2]
//
//
// 提示：
//
//
// 1 <= values <= 10000
// 最多会对 appendTail、deleteHead 进行 10000 次调用
//
// Related Topics 栈 设计 队列 👍 421 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
// 这样写思想虽然是对的，但是效率似乎不高，具体应该可以优化
//class CQueue {
//
//    private LinkedList<Integer> tailStack;
//    private LinkedList<Integer> headStack;
//
//    public CQueue() {
//        tailStack = new LinkedList<Integer>();
//        headStack = new LinkedList<Integer>();
//    }
//
//    public void appendTail(int value) {
//        headStackTotailStack();
//        tailStack.push(value);
//
//    }
//
//    public int deleteHead() {
//        tailStackToheadStack();
//        if (headStack.isEmpty()) {
//            // 注意这里一定要对空的情况进行处理判断，否则会throw NoSuchElementException
//            return -1;
//        } else {
//            return headStack.pop();
//        }
//    }
//
//    private void headStackTotailStack() {
//        while (!headStack.isEmpty()) {
//            tailStack.push(headStack.pop());
//        }
//    }
//
//    private void tailStackToheadStack() {
//        while (!tailStack.isEmpty()) {
//            headStack.push(tailStack.pop());
//        }
//    }
//
//}
// 书上的思路，更巧妙更高效；主要是少了队头倒回队尾的步骤
// 时间效率上去了，空间效率没有上去，可能用其它栈类会有所改进
//class CQueue {
//
//    private LinkedList<Integer> tailStack;
//    private LinkedList<Integer> headStack;
//
//    public CQueue() {
//        tailStack = new LinkedList<Integer>();
//        headStack = new LinkedList<Integer>();
//    }
//
//    public void appendTail(int value) {
//        tailStack.push(value);
//    }
//
//    public int deleteHead() {
//        if (headStack.isEmpty()) {
//            tailStackToheadStack();
//            if (headStack.isEmpty()) {
//                // 注意这里一定要对空的情况进行处理判断，否则会throw NoSuchElementException
//                return -1;
//            } else {
//                return headStack.pop();
//            }
//        } else {
//            return headStack.pop();
//        }
//    }
//
//    private void tailStackToheadStack() {
//        while (!tailStack.isEmpty()) {
//            headStack.push(tailStack.pop());
//        }
//    }
//
//}
class CQueue {

    private LinkedList<Integer> headStack = new LinkedList<Integer>();
    private LinkedList<Integer> tailStack = new LinkedList<Integer>();

    public CQueue() {
    }

    public void appendTail(int value) {
        tailStack.push(value);
    }

    public int deleteHead() {
        if(headStack.size()==0){
            // 注意用isEmpty()也可以
            while(tailStack.size()!=0){
                headStack.push(tailStack.pop());
            }
        }
        // 一定要注意判空！！！
        if (headStack.size() != 0) {
            return headStack.pop().intValue();
        } else {
            // 注意没有元素时返回的int是-1，数据类型不能乱
            return -1;
        }
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        cQueue.deleteHead();
        cQueue.deleteHead();
        cQueue.appendTail(4);
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
//leetcode submit region end(Prohibit modification and deletion)
