//爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
//
// 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
//
// 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
//
// 游戏一直持续到所有石子都被拿走。
//
// 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
//
//
//
// 示例 1：
//
//
//输入：piles = [2,7,9,4,4]
//输出：10
//解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那
//么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
//
//
// 示例 2:
//
//
//输入：piles = [1,2,3,4,5,100]
//输出：104
//
//
//
//
// 提示：
//
//
// 1 <= piles.length <= 100
//
// 1 <= piles[i] <= 10⁴
//
//
// Related Topics 数组 数学 动态规划 博弈 👍 275 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * BEGIN
 */

/**
 * Review1
 * M不好确定，逻辑还是有点复杂
 */
class Solution {
    public int stoneGameII(int[] piles) {
        // 这里dp数组第二维的长度不好确定
        // sum的累加机制决定了它表示考虑从i到piles.length-1堆石头，所有石头数
        int sum = 0;
        // dp[i][M]表示考虑从i到piles.length-1堆石头，M的情况下的先手赢得游戏所能拿到的最大石头数(不管是Alice还是Bob)
        // M最大为piles.length
        int[][] dp = new int[piles.length][piles.length + 1];
        for (int i = piles.length - 1; i >= 0; i--) {
            sum += piles[i];
            for (int M = 1; M <= piles.length; M++) {
                // i-piles.length表示在限制考虑从i到piles.length-1堆石头下可拿的最大堆数，如果2M大于这个数，那么先手直接拿完获胜
                if (2 * M >= (piles.length - i)) {
                    dp[i][M] = sum;
                } else {
                    // 如果不能全部拿完则考虑1-2*M的所有情况，先手拿后手赢总体赢所能获得的最大石头数，就是sum - dp[i + x][Math.max(M, x)]
                    // 注意dp[i + x][Math.max(M, x)]并不是i+x到piles.length-1的所有石头数，其中一些被先手拿了
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                    }
                }
            }
        }
        // 返回
        return dp[0][1];
    }
}

/**
 * Success3
 * 和前面的一样，终极简化优化写法
 * 1、每次访问i的时候加sum就可以了
 * 2、在x内部循环时直接将要赋的值和原值比较，这样不用额外的temp空间，代码也简洁；很多动态规划的题都是这么操作的，本来也是与原状态有关
 * 3、M循环时变量名命名为M有助于逻辑理解，同时多开辟一个列，省去M的内涵和索引转换的麻烦
 *    在逻辑方便和存储空间优先选择逻辑方便，因为正确是最重要的，有些细节导致逻辑混乱的话很难排查
 */
//class Solution {
//    public int stoneGameII(int[] piles) {
//        int len = piles.length, sum = 0;
//        int[][] dp = new int[len][len + 1];
//        for (int i = len - 1; i >= 0; i--) {
//            sum += piles[i];
//            for (int M = 1; M <= len; M++) {
//                if (i + 2 * M >= len) {
//                    dp[i][M] = sum;
//                } else {
//                    for (int x = 1; x <= 2 * M; x++) {
//                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
//                    }
//                }
//            }
//        }
//        return dp[0][1];
//    }
//}

/**
 * Success2
 * 和Success1一样，简化注释，优化sum的计算
 */
//class Solution {
//    public int stoneGameII(int[] piles) {
//        int[][] sums = new int[piles.length][piles.length];
//        int sum = 0;
//        for (int i = piles.length - 1; i >= 0; i--) {
//            sum += piles[i];
//            for (int j = 0; j <= piles.length - 1; j++) {
//                if (2 * (j + 1) >= piles.length - i) {
//                    sums[i][j] = sum;
//                } else {
//                    // 注意这里两种写法均可
////                    for (int k = i; k < i + 2 * (j + 1); k++) {
////                        sums[i][j] = Math.max(sums[i][j], sum - sums[k + 1][Math.max(j + 1, k - i + 1) - 1]);
////                    }
//                    // 注意由于这个分支的情况只有在i比较小可选的堆比较多、且M比较小的时候出现，所以i+x不会超出索引
//                    // 具体来说也就是2M<piles.length-i，x<=2M<piles.length-i，i+x<piles.length
//                    for (int x = 1; x <= 2 * (j + 1); x++) {
//                        // 注意这里Math.max(j + 1, x) - 1才是实际的M的索引
//                        sums[i][j] = Math.max(sums[i][j], sum - sums[i + x][Math.max(j + 1, x) - 1]);
//                    }
//                }
//            }
//        }
//        return sums[0][0];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] piles = new int[]{2, 7, 9, 4, 4};
//        int res = solution.stoneGameII(piles);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 注意动态规划的核心是状态定义和转移
 * 注意这道题的动态规划状态转移计算非得倒着来才行
 * 逻辑正确，写得有点复杂
 */
//class Solution {
//    public int stoneGameII(int[] piles) {
//        // 这里行表示可选石头为[i:piles.length-1]，闭区间，索引数
//        // 这里列表示M，最大为piles.length
//        int[][] sums = new int[piles.length][piles.length];
//        for (int i = piles.length - 1; i >= 0; i--) {
//            // 注意这里M=j+1，j表示M的索引，M超过可选石头范围的长度，那个时候会进入第一个分支直接赋值不会进入第二个分支从而出现索引越界的情况
//            // 而且这里你的M还必须完备，这样才不会漏掉第一个分支的情况
////            for (int j = 0; j < piles.length; j++) {
//            // 这里M正反访问都可以
//            for (int j = piles.length - 1; j >= 0; j--) {
//                // 最大能取的2M堆大于等于剩下的堆数，那么就能全部取完
//                if (2 * (j + 1) >= piles.length - i) {
//                    sums[i][j] = sum(piles, i, piles.length - 1);
//                    // 取不完的话就要在可能的情况中找出最大的
//                } else {
//                    int res = 0;
//                    // k表示我从i取到k，则我取的堆数x=k-i+1
//                    // 注意这里k最大为piles.length - 2，如果是piles.length - 1就是取完的情况了，不符合逻辑也会导致后面取的超出索引
////                    for (int k = i; k < piles.length - 1; k++) {
//                    // 但是注意这里也不能最大到piles.length - 2，因为有x<2M的限制，所以上面这种写法其实是取多了
//                    for (int k = i; k < i + 2 * (j + 1); k++) {
//                        // 这种情况下少的就是我没有取完被拿走的那部分
//                        // 当然外层循环的多种可能情况是必不可少的
//                        int temp = sum(piles, i, piles.length - 1) - sums[k + 1][Math.max(j + 1, k - i + 1) - 1];
//                        if (temp > res) {
//                            res = temp;
//                        }
//                    }
//                    sums[i][j] = res;
//                }
//            }
//        }
//        // 注意返回的不是sums[0][1]，M是1，但是索引是0
//        return sums[0][0];
//    }
//
//    /**
//     * 计算和，左闭右闭
//     */
//    private int sum(int[] piles, int start, int end) {
//        int res = 0;
//        for (int i = start; i <= end; i++) {
//            res += piles[i];
//        }
//        return res;
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
