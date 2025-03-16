//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
//
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
//
//
//
// 示例 1：
//
//
//输入: n = 5, m = 3
//输出: 3
//
//
// 示例 2：
//
//
//输入: n = 10, m = 17
//输出: 2
//
//
//
//
// 限制：
//
//
// 1 <= n <= 10^5
// 1 <= m <= 10^6
//
//
// Related Topics 递归 数学 👍 778 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 约瑟夫环数学解法
 */
class Solution {

    /**
     * Success2
     * 都是从最后一轮开始，只不过用i表示上一轮的人数，这样不用i+1冗余计算上一轮的人数，虽然似乎也没有更快
     */
    public int lastRemaining(int n, int m) {
        int index = 0;
        for (int i = 2; i <= n; i++) {
            index = (index + m) % i;
        }
        return index;
    }

    /**
     * Success1
     * 从最后剩一个人开始，i表示当前的人数，因为是余的上一轮人数，所以要i+1，这种计算很冗余
     */
//    public int lastRemaining(int n, int m) {
//        int index = 0;
//        for (int i = 1; i < n; i++) {
//            index = (index + m) % (i + 1);
//        }
//        return index;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.lastRemaining(5, 3);
        System.out.println(res);
    }

}

/**
 * Success1
 * 直白的模拟做法，取决于链表底层数据结构的性能
 * LinkedList会超时，ArrayList不会
 */
//class Solution {
//
//    /**
//     * Success2
//     * 简化写法，有些变量可以省略，重复覆盖，还省去了赋值的麻烦
//     */
//    public int lastRemaining(int n, int m) {
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            list.add(i);
//        }
//        int toRemoveInx = 0;
//        while (list.size() != 1) {
//            toRemoveInx = (toRemoveInx + m - 1) % list.size();
//            list.remove(toRemoveInx);
//        }
//        return list.get(0);
//    }
//
//    /**
//     * Success1
//     * ArrayList性能勉强不会超时
//     */
////    public int lastRemaining(int n, int m) {
////        ArrayList<Integer> list = new ArrayList<>();
////        for (int i = 0; i < n; i++) {
////            list.add(i);
////        }
////        int start = 0;
////        while (list.size() != 1) {
////            int toRemoveInx = (start + m - 1) % list.size();
////            list.remove(toRemoveInx);
////            start = toRemoveInx % list.size();
////        }
////        return list.get(0);
////    }
//
//    /**
//     * Failure1
//     * LinkedList会超时
//     */
////    public int lastRemaining(int n, int m) {
////        LinkedList<Integer> list = new LinkedList<>();
////        for (int i = 0; i < n; i++) {
////            list.add(i);
////        }
////        int start = 0;
////        while (list.size() != 1) {
////            int toRemoveInx = (start + m - 1) % list.size();
////            list.remove(toRemoveInx);
////            start = toRemoveInx % list.size();
////        }
////        return list.poll();
////    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.lastRemaining(5, 3);
//        System.out.println(res);
//    }
//
//
//}
//leetcode submit region end(Prohibit modification and deletion)
