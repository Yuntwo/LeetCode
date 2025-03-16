//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈
//的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
//
//
//
// 示例 1：
//
// 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
//
//
// 示例 2：
//
// 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
//
//
//
//
// 提示：
//
//
// 0 <= pushed.length == popped.length <= 1000
// 0 <= pushed[i], popped[i] < 1000
// pushed 是 popped 的排列。
//
//
// 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
// Related Topics 栈 数组 模拟 👍 341 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
// 不同的判断逻辑
//class Solution {
//    public boolean validateStackSequences(int[] pushed, int[] popped) {
//        // 注意虽然是判断压入和弹出序列，但是仍需要一个实际的辅助栈来模拟记录压入弹出过程
//        // 注意为空的情况默认是true
//        if (pushed.length == 0) {
//            return true;
//        }
//        if (pushed.length != popped.length) {
//            return false;
//        }
//        LinkedList<Integer> stack = new LinkedList<Integer>();
//        // i表示依次可压入的元素索引，单调递增
//        int i = 0;
//        // j表示要判断弹出的元素索引
//        int j;
//
//        for (j = 0; j < popped.length; j++) {
//            // 想要依次弹出这个元素的几种可能情况，都不满足则返回false
//            // 第一种情况，等于栈顶元素，把栈顶元素弹出去
//            // 注意这里不能直接弹出来判断，因为如果不相等的话就不能弹出来，必须分开写
////            if (stack.size() != 0 && popped[j] == stack.pop().intValue()) {
//            // 注意这应该是判断栈顶元素同时满足的两个条件，不能嵌套成两个if，否则循环判断进入的条件不对
//            if (stack.size() != 0 && popped[j] == stack.getFirst().intValue()) {
//                stack.pop();
//                continue;
//            } else {
//                // 第二种情况，新压入元素，直到找到值相等的压入元素，然后又弹出去；如果找不到就
//                // 注意这里临界条件就是非常不好判断，你不知道它是因为没有找到而一直循环到了最后，还是刚好是最后一个满足条件
//                while (i < pushed.length) {
//                    if (popped[j] == pushed[i]) {
//                        i++;
//                        break;
//                    } else {
//                        stack.push(pushed[i]);
//                        i++;
//                    }
//                }
//                if (i == pushed.length && pushed[i - 1] != popped[j]) {
//                    return false;
//                }
//            }
//            // 第三种情况，既不是栈顶元素，也不能找到值相等的新压入元素，直接返回false了
////            return false;
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        int[] pushed = new int[]{1, 2, 3, 4, 5};
//        int[] poped = new int[]{4, 3, 5, 1, 2};
//        Solution solution = new Solution();
//        boolean res = solution.validateStackSequences(pushed, poped);
//        System.out.println(res);
//    }
//}
/**
 * 注意不管怎样判断序列的条件都是差不多的，但是有以pushed序列和以poped序列为基点的区别
 * 这就是以poped序列为基点找pushed
 * 但是不管以哪种为基点考虑，由于弹出的连续性，只能在栈顶或者后续的pushed找，不能在栈中多弹出几个，否则就破坏了顺序性
 */
//class Solution {
//    public boolean validateStackSequences(int[] pushed, int[] popped) {
//        // 注意虽然是判断压入和弹出序列，但是仍需要一个实际的辅助栈来模拟记录压入弹出过程
//        // 注意为空的情况默认是true
//        if (pushed.length == 0) {
//            return true;
//        }
//        // 题目指出pushed是popped的排列。因此，无需考虑pushed和popped长度不同或包含元素不同的情况
//        // 当然这点对时间效率影响不大
////        if (pushed.length != popped.length) {
////            return false;
////        }
//        LinkedList<Integer> stack = new LinkedList<Integer>();
//        // i表示依次可压入的元素索引，单调递增
//        int i = 0;
//        // j表示要判断弹出的元素索引
//        int j;
//
//        for (j = 0; j < popped.length; j++) {
//            // 想要依次弹出这个元素的几种可能情况，都不满足则返回false
//            // 第一种情况，等于栈顶元素，把栈顶元素弹出去
//            // 注意这应该是判断栈顶元素同时满足的两个条件，不能嵌套成两个if，否则循环判断进入的条件不对
//            if (stack.size() != 0 && popped[j] == stack.getFirst().intValue()) {
//                stack.pop();
//                continue;
//            } else {
//                // 第二种情况，新压入元素，直到找到值相等的压入元素，然后又弹出去；如果找不到就
//                // 注意这里临界条件就是非常不好判断，你不知道它是因为没有找到而一直循环到了最后，还是刚好是最后一个满足条件
//                // 这里是非常经典的判断瓶颈
//                // 默认找不到，只要出现一种情况就能改变判断量
//                // 这样其实是将循环次数控制变量和条件控制变量分开了!!!
//                // 这是很经典的写法
//                boolean exist = false;
////                for (i = 0; i < pushed.length; i++) {
//                // 注意这里还是用while来控制循环吧，因为i的循环控制和想要的索引是具有不同步性的，在某些情况下是兼容的，但是本质上应该还是不同
//                while (i < pushed.length) {
//                    if (pushed[i] == popped[j]) {
//                        exist = true;
//                        i++;
//                        // 如果这里是for循环直接控制，满足条件break的话i不会多+那一次
//                        break;
//                    } else {
//                        stack.push(pushed[i]);
//                        i++;
//                    }
//                }
//                // 第三种情况，既不是栈顶元素，也不能找到值相等的新压入元素，直接返回false了
//                // 这样写把判断变量exist放在压入元素逻辑分支其实短路与了，也可以把它放在第二种情况外面，弄成一般与，但是这样判断次数肯定更多
//                if (!exist) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        int[] pushed = new int[]{1, 2, 3, 4, 5};
//        int[] poped = new int[]{4, 3, 5, 1, 2};
//        Solution solution = new Solution();
//        boolean res = solution.validateStackSequences(pushed, poped);
//        System.out.println(res);
//    }
//}
/**
 * 这是以pushed为基点
 * 是模拟的先压进去，遇到了再弹出去，是很自然的思路
 * 注意虽然是有多种叠加，但实际上只有连续的可能，这样其实就能划分子问题
 * 当然这种方法和上面的方法思路没有本质区别，所以时间效率是一样的
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 注意声明指明了泛型类型在实例化时就可以不指明了
        // 注意用LinkedList时间和空间效率明显高于Stack
        LinkedList<Integer> stack = new LinkedList<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); // num 入栈
            // 注意push是有限次数，但是你有可能要连续多次弹栈顶，所以这里要用while循环，知道不能连续弹再push
            // 注意peek就是getFirst，LinkedList的栈顶是第一个位置
            // 注意这里是短路与
            // 这里的循环很妙很简洁
            while (stack.size() != 0 && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] poped = new int[]{4, 5, 3, 2, 1};
        TestSolution solution = new TestSolution();
        boolean res = solution.validateStackSequences(pushed, poped);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
