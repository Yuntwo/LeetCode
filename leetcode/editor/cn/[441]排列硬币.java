//你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
//
// 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
//
//
//
// 示例 1：
//
//
//输入：n = 5
//输出：2
//解释：因为第三行不完整，所以返回 2 。
//
//
// 示例 2：
//
//
//输入：n = 8
//输出：3
//解释：因为第四行不完整，所以返回 3 。
//
//
//
//
// 提示：
//
//
// 1 <= n <= 2³¹ - 1
//
//
// Related Topics 数学 二分查找 👍 249 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2-plus
 * 二分查找，只能用long逻辑才清楚理得顺，注意类型比较和转换
 */
//class Solution {
//
//    public int arrangeCoins(int n) {
//        // 注意这里left和right都定义为大于的位置
//        long pivot = (long) n;
//        long left = 1;
//        long right = pivot + 1;
//        while (left < right) {
//            long mid = (right - left) / 2 + left;
//            // 注意这样计算一定是精确的
//            if ((mid + 1) * mid / 2 <= pivot) {
//                // 注意这个值小于，说明对应的值一定是小于等于的
////            if (((mid + 1) / 2) * mid <= n) {
//                left = mid + 1;
//                // 注意这个值大于，说明对应的值一定是大于的
//            } else {
//                right = mid;
//            }
//        }
//        // 注意跳出循环时，left一定等于right
//        return (int) (left - 1);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.arrangeCoins(8);
//        System.out.println(res);
//    }
//
//}

/**
 * Failure1
 * 二分查找使用int型，由于除法的向下取整特性，还是不好处理
 */
//class Solution {
//
//    public int arrangeCoins(int n) {
//        int res = 0;
//        int left = 1;
//        int right = n;
//        while (left < right) {
//            // 注意因为除法是向下取整，所以left一定要+1，否则会卡起跳不出循环，对应改变left的定义即可
//            int mid = (right - left) / 2 + left;
//            // 注意这个值小于，说明对应的值一定是小于的
//            // 所以这里left定义为大于等于的值位置
//            if (((mid - 1) / 2 + 1) < (n / mid)) {
//                left = mid + 1;
//            // 这个值大于等于，说明对应的值是大于等于的(大于的值也有可能去余导致结果等于)
//            // 这里right定义为  大于等于的值位置
//            } else {
//                if ((mid - 1) % 2 == 0 && n % mid == 0) {
//                    left = mid + 1;
//                } else {
//                    right = mid;
//                }
//            }
//        }
//        // 注意跳出循环时，left一定等于right
//        return left - 1;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.arrangeCoins(8);
//        System.out.println(res);
//    }
//
//}

/**
 * Success3-Reivew1
 * 直接递增试
 */
//class Solution {
//
//    public int arrangeCoins(int n) {
//        int res = 0;
//        int row = 1;
//        // 注意这里用减法很重要，避免溢出
//        while (res <= n - row) {
//            res += row;
//            row++;
//        }
//        row--;
//        return row;
//    }
//
//}

/**
 * Success3
 * 最笨的方法，不用公式还累加
 * 注意题目说的是返回完整阶梯行的数目
 * 数据大了会有溢出问题，比如n=2147483647时，可能试的值就超出了这个表示范围
 * 除非你sum用long来存储
 */
//class Solution {
//    public int arrangeCoins(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        long sum = 0;
//        int i;
//        for (i = 0; sum < n; i++) {
//            sum += i;
//        }
//        if (sum == n) {
//            return i - 1;
//        }
//        return i - 2;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.arrangeCoins(6);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 不挨着试而用二分试
 * 那么计算和直接用公式而不是挨着迭加
 * 感觉不愿意用二分法主要是临界条件处理起来很麻烦
 */
//class Solution {
//    public int arrangeCoins(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        // 为了配合sum的计算，声明为long而不是int
//        long l = 1;
//        // 最多n行，这还是往宽了算
//        long r = n;
//        // 注意也必须用long类型
//        long sum = 0;
//        long mid = 0;
//        // 注意二分法的循环跳出条件是l>=r
//        while (l < r) {
//            // Q:不太懂这里为啥直接(r+l)/2会死循环，其它写法也能避免这个问题
//            mid = (l + r + 1) / 2;
//            // 除以2可以用右移运算
////            mid = l + r + 1 >> 1;
//            // 注意二分的除法本来就是下取整
//            sum = mid * (mid + 1) / 2;
//            // 注意是小于等于将mid赋给左边
//            // 由于mid可能是等于的，所以没有赋mid+1
//            if (sum <= n) {
//                l = mid;
//            } else {
//                // 注意这里r是赋的mid-1而不是mid，因为到这一步mid一定是大于n的
//                r = mid-1;
//            // 这个逻辑和临界条件是灵活可变的
//            }
//        }
//        return (int) r;
//    }
//
//}

/**
 * Success1
 * 直接用数学公式做，效率最高
 */
//class Solution {
//    public int arrangeCoins(int n) {
//        // Q:不知道为啥这里必须要用8.0，用8就会报错
//        // 应该是用8会当成int计算传入int参数会溢出，用8.0就会当成float型不会溢出
//        return (int) ((Math.sqrt(8.0 * n + 1) - 1) / 2);
//    }
//
//    /**
//     * 不太清楚提交main函数会不会占用内存
//     */
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.arrangeCoins(6);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
