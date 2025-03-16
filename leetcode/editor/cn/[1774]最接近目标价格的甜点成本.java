//你打算做甜点，现在需要购买配料。目前共有 n 种冰激凌基料和 m 种配料可供选购。而制作甜点需要遵循以下几条规则：
//
//
// 必须选择 一种 冰激凌基料。
// 可以添加 一种或多种 配料，也可以不添加任何配料。
// 每种类型的配料 最多两份 。
//
//
// 给你以下三个输入：
//
//
// baseCosts ，一个长度为 n 的整数数组，其中每个 baseCosts[i] 表示第 i 种冰激凌基料的价格。
// toppingCosts，一个长度为 m 的整数数组，其中每个 toppingCosts[i] 表示 一份 第 i 种冰激凌配料的价格。
// target ，一个整数，表示你制作甜点的目标价格。
//
//
// 你希望自己做的甜点总成本尽可能接近目标价格 target 。
//
// 返回最接近 target 的甜点成本。如果有多种方案，返回 成本相对较低 的一种。
//
//
//
// 示例 1：
//
//
//输入：baseCosts = [1,7], toppingCosts = [3,4], target = 10
//输出：10
//解释：考虑下面的方案组合（所有下标均从 0 开始）：
//- 选择 1 号基料：成本 7
//- 选择 1 份 0 号配料：成本 1 x 3 = 3
//- 选择 0 份 1 号配料：成本 0 x 4 = 0
//总成本：7 + 3 + 0 = 10 。
//
//
// 示例 2：
//
//
//输入：baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
//输出：17
//解释：考虑下面的方案组合（所有下标均从 0 开始）：
//- 选择 1 号基料：成本 3
//- 选择 1 份 0 号配料：成本 1 x 4 = 4
//- 选择 2 份 1 号配料：成本 2 x 5 = 10
//- 选择 0 份 2 号配料：成本 0 x 100 = 0
//总成本：3 + 4 + 10 + 0 = 17 。不存在总成本为 18 的甜点制作方案。
//
//
// 示例 3：
//
//
//输入：baseCosts = [3,10], toppingCosts = [2,5], target = 9
//输出：8
//解释：可以制作总成本为 8 和 10 的甜点。返回 8 ，因为这是成本更低的方案。
//
//
// 示例 4：
//
//
//输入：baseCosts = [10], toppingCosts = [1], target = 1
//输出：10
//解释：注意，你可以选择不添加任何配料，但你必须选择一种基料。
//
//
//
// 提示：
//
//
// n == baseCosts.length
// m == toppingCosts.length
// 1 <= n, m <= 10
// 1 <= baseCosts[i], toppingCosts[i] <= 10⁴
// 1 <= target <= 10⁴
//
//
// Related Topics 数组 动态规划 回溯 👍 72 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 *
 */

// 注意题目说的是“每种类型的配料最多添加两份”，而不是“最多添加两种类型的配料”

/**
 * Success1
 * 题解做法
 * 思路并没有很常规有套路，技巧很多，我是写不出来的
 * 主要是对于加超过了的情况处理很巧妙，没有改变经典的背包问题结构，只是用了一些额外的小手段
 */
class Solution {
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        /* 一种特殊情况，讨论所有基料都小于target的情况 */
        // 如果最小的基料都大于target，直接返回它
        int x = Arrays.stream(baseCosts).min().getAsInt();
        if (x >= target) {
            return x;
        }
        /* 这里的can还是设置的添加的情况总体小于等于target的分支 */
        boolean[] can = new boolean[target + 1];
        /* 这里初始化了一种特殊情况，使用最小基料 */
        // 并且添加辅料超过这个情况的话一定不是最优解，还不如直接只用最小基料
        /* 这是超过target的情况，利用与它比较就可以达到大于target的分支 */
        int res = 2 * target - x;
        /* 同时考虑了两个分支 */
        for (int b : baseCosts) {
            if (b <= target) {
                can[b] = true;
            } else {
                res = Math.min(res, b);
            }
        }
        /* 在所有小于target的情况为初始讨论，但是会处理到超出target的分支 */
        // t和i的循环倒过来应该也行，但是这样逻辑不对了
        // 背包问题是对于每一个石头加或者不加对于每一种总和的影响
        // 而不是对于要达到每一种总和每次都遍历一遍每一个石头加或者不加
        // 思维逻辑是不一样的
        for (int t : toppingCosts) {
            // 这是每种辅料尝试加两次
            for (int count = 0; count < 2; ++count) {
                // 这也是从大到小一维dp防止覆盖
                // 注意虽然每一次count都尝试了不同的target，也用到了之前target的组合数
                // 但在一次count中如果不会不会出现叠加了另一target上一次的这个数
                // 因为它对应的是上一次count的未更新的值，所以没有重叠使用
                for (int i = target; i > 0; --i) {
                    // 这是加超过了的分支
                    if (can[i] && i + t > target) {
                        res = Math.min(res, i + t);
                    }
                    if (i - t > 0) {
                        // 注意这里一定不会数组索引越界
                        // 因为如果想用它的话就一定是找差值是否can，而不是一定要在上一个can的基础上
                        // 从根本上来说还是以target为递推基础的，而不是挨个挨个一定要加或者不加
                        can[i] = can[i] || can[i - t];
                    }
                }
            }
        }
        /* 这个是最终的大于target与小于target比较 */
        // 如果能在小于target里面找到与大于target同样的差值，那么肯定是选小于
        for (int i = 0; i <= res - target; ++i) {
            if (can[target - i]) {
                return target - i;
            }
        }
        return res;
    }
}

/**
 * Failure1
 * 这样思路有点混乱
 */
//class Solution {
//    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
//        for (int m = 0; m < baseCosts.length; m++) {
//            if (baseCosts[m] >= target) {
//                continue;
//            }
//            // 最多加这么多料就够了，再多就不在最优解考虑范围内了
//            // 注意这是加的料的数量
//            int toppingMax = 2 * target - 2 * baseCosts[m];
//            boolean[] can = new boolean[toppingMax + 1];
//            can[0] = true;
//            int[] topping = new int[toppingCosts.length * 2];
//            for (int i = 0; i < toppingCosts.length; i++) {
//                topping[i] = toppingCosts[i];
//                topping[2 * i] = toppingCosts[i];
//            }
//            for (int i = 0; i < topping.length; i++) {
//                for (int j = toppingMax; j >= topping[i]; j--) {
//                    can[j] = can[j] || can[j - topping[i]];
//                }
//            }
//            int less, more;
//            for (int i = target - baseCosts[m]; i >= 0; i--) {
//                if (can[i]) {
//                    less = i;
//                }
//            }
//            for (int i = target - baseCosts[m]; i <= toppingMax; i++) {
//                if (can[i]) {
//                    more = i;
//                }
//            }
//            if (less == more) {
//                baseCosts[m] += less;
//            } else {
//                baseCosts += Math.min(target - baseCosts[m] - less , )
//            }
//        }
//    }
//}

/**
 *
 */
//leetcode submit region end(Prohibit modification and deletion)

/**
 * Failure1
 * "如果有多种方案，返回 成本相对较低 的一种"应该是指在大于target和小于target之间选小于
 * baseCosts和toppingCosts应该本身是无序的
 * 由于每种类型的调料最多可以加两份，相当于每个石头可以放两次，动态规划的约束条件更多，情况更复杂
 */
//class Solution {
//    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
//        int global = 0;
//        int globalDiff = global - target;
//        for (int i = 0; i < baseCosts.length; i++) {
//            // 先加基料
//            int local = baseCosts[i];
//            int localDiff = base - target;
//            // 不加配料看能够比上一次全局更优
//            if (Math.abs(localDiff) < Math.abs(globalDiff)) {
//                global = local;
//                globalDiff = localDiff;
//            }
//            if (globalDiff == 0) {
//                return global;
//            } else if (globalDiff > 0) {
//                break;
//            }
//            // 加配料看能够比上一次全局更优
//            int[] topping = new int[toppingCosts.length];
//            // 上一次最优配料是加了几种
//            int flag = 0;
//        }
//
//    }
//}
