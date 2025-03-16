//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
//值排序之后中间两个数的平均值。
//
// 例如，
//
// [2,3,4] 的中位数是 3
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5
//
// 设计一个支持以下两种操作的数据结构：
//
//
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。
// double findMedian() - 返回目前所有元素的中位数。
//
//
// 示例 1：
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
//
//
// 示例 2：
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000]
//
//
//
// 限制：
//
//
// 最多会对 addNum、findMedian 进行 50000 次调用。
//
//
// 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-
//stream/
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 379 👎 0


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
// 这样写应该是正确的，但是会超时
//class MedianFinder {
//
//    private LinkedList<Integer> list;
//
//    /** initialize your data structure here. */
//    public MedianFinder() {
//        list = new LinkedList<Integer>();
//    }
//
//    public void addNum(int num) {
//        if (list.size() == 0) {
//            list.add(num);
//            return;
//        }
//        // 注意这样比较是最高效简洁的
//        for (int i = 0; i < list.size(); i++) {
//            if (num <= list.get(i)) {
//                // 注意LinkedList的add(int index, E value)的规定
//                list.add(i, num);
//                return;
//            }
//        }
//        // 注意这一句不应该放在for循环里
//        list.add(num);
//    }
//
//    public double findMedian() {
//        // 注意和list==null是不同的含义
//        if (list.size() == 0) {
//            return 0.0;
//        }
//        double res;
//        if (list.size() % 2 == 0) {
//            // 注意哪里要用浮点数2哪里要用下取整2
//            res = (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0;
//        } else {
//            res = list.get(list.size() / 2);
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        MedianFinder medianFinder = new MedianFinder();
//        medianFinder.addNum(6);
//        medianFinder.addNum(10);
//        medianFinder.addNum(2);
//        double res = medianFinder.findMedian();
//        System.out.println(res);
//    }
//}
// 这种Hard的题目就是不容易想到，但是给了思路的话就很容易理解
class MedianFinder {

    /**
     * 小顶堆，保存较大的一半
     */
    Queue<Integer> A;

    /**
     * 大顶堆，保存较小的一半
     */
    Queue<Integer> B;

    public MedianFinder() {
        A = new PriorityQueue<>();
        // 注意小顶堆的实现方式及表达，Q:不太懂后面这个y-x
        B = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        if (A.size() == B.size()) {
            B.add(num);
            // 注意这里Queue的peek和poll的head元素就是堆顶元素
            A.add(B.poll());
        } else {
            A.add(num);
            B.add(A.poll());
        }
    }

    public double findMedian() {
        // 注意这种就一个表达式的完全可以用三目运算符简洁表达
        // 这里A.peek()返回int型应该会进行隐式数据类型转换
        return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : A.peek();
    }

}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
