//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
//
//
// 如果 x == y，那么两块石头都会被完全粉碎；
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
//
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
//
//
//
// 示例 1：
//
//
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
//
//
// 示例 2：
//
//
//输入：stones = [31,26,33,21,40]
//输出：5
//
//
//
//
// 提示：
//
//
// 1 <= stones.length <= 30
// 1 <= stones[i] <= 100
//
//
// Related Topics 数组 动态规划 👍 578 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 注意这个问题的转换需要全局的数学思维和归纳能力，而不是一步一步地要怎么转换
 * 能不能转换为背包问题其实就是看能否全局地把问题转换为求和
 * ！！！问题转换一定是在全局的角度转换，而不是对背包问题本身的形式进行转换
 * 注意01背包和完全背包的问题结构是一样的，只有取值不同而已，不要在它们问题的结构变换上下功夫
 * 问题结构一定是一个一个挨个遍历元素，看每一个元素对于不同的target的影响
 */
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        // 找到最接近sum/2下取整的和，因为始终想找的是较小的那一堆的值
        int target = sum / 2;
        boolean[] res = new boolean[target + 1];
        // 注意一块石头也不看的时候是能达到和为0的
        res[0] = true;
        for (int stone : stones) {
            for (int i = target; i >= 1; i--) {
                if (stone <= i) {
                    res[i] |= res[i - stone];
                }
            }
        }
        int minHeap = 0;
        for (int i = target; i >= 1; i--) {
            if (res[i]) {
                minHeap = i;
                // 注意这里找到最大的就可以break了，否则会被覆盖
                break;
            }
        }
        return sum - minHeap - minHeap;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] stones = new int[]{31, 26, 33, 21, 40};
//        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        int[] stones = new int[]{91};
        int res = solution.lastStoneWeightII(stones);
        System.out.println(res);
    }
}

/**
 * Success1
 * 感觉这个题目用背包问题建模不好理解的地方在于
 * 不是要不要选某个石头，而是每一个石头最后一定会被选，最后所有的石头都会被加进去
 * 区别在于石头被加进去的顺序
 * 注意好好理解题意
 * 所以问题转换为将一堆石头划分会重量接近的两堆 -> 尝试从一堆石头中选出总重量接近sum/2
 * 就可以运用背包问题的思路了
 * 理解为一个正号堆与一个负号堆
 * 没有必要两个堆数量相等，两个石头消掉之后的石头应该是放回原重量较大的那个堆
 * 也没必要指定究竟是哪个石头和哪个石头碰，可以随机不唯一，不影响最后的结果；一定存在某种选择方式使得最终两堆重量相近的石头碰的方式结果最小
 */
//class Solution {
//    public int lastStoneWeightII(int[] stones) {
//        int sum = 0;
//        for (int stone :
//            stones) {
//            sum += stone;
//        }
//        // 因为是接近，所以这里奇数取整也没有关系
//        int target = sum / 2;
//        // 默认应该是初始化为false的
//        boolean[] can = new boolean[target + 1];
//        can[0] = true;
//        for (int i = 0; i < stones.length; i++) {
//            // 注意是--
//            for (int j = target; j >= stones[i]; j--) {
//                can[j] = can[j] || can[j - stones[i]];
//            }
//        }
////        int sub = 0;
////        // 注意是--
////        for (int i = target; i >= 0; i--) {
////            if (can[i]) {
////                sub = i;
////                break;
////            }
////        }
////        return Math.abs(2 * sub - sum);
//        // 可以简化，这样稍稍改一下就100%了
//        for (int i = target; i >= 0; i--) {
//            if (can[i]) {
//                // 因为i是接近target但是小于等于target，所以sum一定大于等于2*i
//                return sum - 2 * i;
//            }
//        }
//        return 0;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] stones = new int[]{31, 26, 33, 21, 40};
////        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
//        int res = solution.lastStoneWeightII(stones);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
