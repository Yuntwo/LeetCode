//请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不
//会升高，请在该位置用 0 来代替。
//
//
//
// 示例 1:
//
//
//输入: temperatures = [73,74,75,71,69,72,76,73]
//输出: [1,1,4,2,1,1,0,0]
//
//
// 示例 2:
//
//
//输入: temperatures = [30,40,50,60]
//输出: [1,1,1,0]
//
//
// 示例 3:
//
//
//输入: temperatures = [30,60,90]
//输出: [1,1,0]
//
//
//
// 提示：
//
//
// 1 <= temperatures.length <= 10⁵
// 30 <= temperatures[i] <= 100
//
//
//
//
//
// 注意：本题与主站 739 题相同： https://leetcode-cn.com/problems/daily-temperatures/
//
// Related Topics 栈 数组 单调栈 👍 93 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Success2
 * 单调栈，很巧妙的思路，想不到
 * 其实不太明白最底层原理，怎么想到的
 */
class Solution {

    /**
     * Success3
     * 数组模拟栈，效率很大提升
     */
//    public int[] dailyTemperatures(int[] temperatures) {
//        int n = temperatures.length, top = -1;
//        int[] stack = new int[n], res = new int[n];
//        for (int i = 0; i < n; i++) {
//            int t = temperatures[i];
//            while (top != -1 && t > temperatures[stack[top]]) {
//                res[stack[top]] = i - stack[top];
//                top--;
//            }
//            stack[++top] = i;
//        }
//        return res;
//    }

    /**
     * Success2
     * Deque栈，接口和LinkedList一样，时间效率没有LinkedList高
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int t = temperatures[i];
            // 这里其实不需要if分支
            while(!stack.isEmpty() && t > temperatures[stack.peek()]){
                res[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return res;
    }


    /**
     * Success1
     * LinkedList
     */
//    public int[] dailyTemperatures(int[] temperatures) {
//        int[] res = new int[temperatures.length];
//        LinkedList<Integer> stack = new LinkedList<>();
//        for (int i = 0; i < temperatures.length; i++) {
//            // 这个if分支其实有点多余
//            if (stack.isEmpty() || temperatures[i] <= temperatures[stack.peek()]) {
//                stack.push(i);
//            } else {
//                // 注意这里要判空
//                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
//                    int temp = stack.poll();
//                    res[temp] = i - temp;
//                }
//                stack.push(i);
//            }
//        }
//        return res;
//    }
}

/**
 * Success1
 * 双重循环，时间和空间效率都很低
 */
//class Solution {
//    public int[] dailyTemperatures(int[] temperatures) {
//        int[] res = new int[temperatures.length];
//        for (int i = 0; i < temperatures.length - 1; i++) {
//            for (int j = i + 1; j < temperatures.length; j++) {
//                if (temperatures[j] > temperatures[i]) {
//                    res[i] = j - i;
//                    break;
//                }
//            }
//        }
//        res[temperatures.length - 1] = 0;
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
