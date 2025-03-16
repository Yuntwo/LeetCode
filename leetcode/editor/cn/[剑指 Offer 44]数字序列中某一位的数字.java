//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。
//
// 请写一个函数，求任意第n位对应的数字。
//
//
//
// 示例 1：
//
// 输入：n = 3
//输出：3
//
//
// 示例 2：
//
// 输入：n = 11
//输出：0
//
//
//
// 限制：
//
//
// 0 <= n < 2^31
//
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
//
// Related Topics 数学 二分查找 👍 295 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// Q:还是会存在边界值2^31-1时间溢出的问题
// 本地环境还是能算出来且结果正确，就是LeetCode环境不行，那就是这种算法性能还是不行
//class Solution {
//    public int findNthDigit(int n) {
//        // i是正常十进制计数
//        // j是单个数字个数计数
//        int i = 0;
//        int j = 0;
//        while (j != n) {
//            i++;
//            int temp = i;
//            int bit = 1;
//            // 注意这样的话要排除temp = 0的这样只有一位的情况
//            // 统计当前i的单个数字个数
//            while (temp / 10 != 0) {
//                temp /= 10;
//                bit++;
//            }
//            // 注意这里要考虑只有一位的情况
//            // 对每一个i统计单个数字个数加到j中
//            for (int k = 0; k < bit; k++) {
//                j++;
//                // 如果j!=n也就没必要计算具体的数字是哪一个了，所以i只可能用到一次，不用另外存储
//                if (j == n) {
//                    // 或许从高到低第k位的数字(从0开始)
//                    for (int l = 0; l < bit - k - 1; l++) {
//                        i /= 10;
//                    }
//                    return i % 10;
//                }
//            }
//        }
//        // 只有可能是i为单个数字时这里直接返回
//        return i;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.findNthDigit(2147483647);
//        System.out.println(res);
//    }
//}
// 根据统计规律简化计算
// 我的写法没有调通
//class Solution {
//    public int findNthDigit(int n) {
//        // 确定n所在数字的位数
//        int digit = 1;
//        // 先采用加法的形式
//        // 注意从0开始的，单独算
//        int sumBit = 1;
//        while (sumBit < n) {
//            sumBit += Math.pow(10, digit - 1) * digit * 9;
//            digit++;
//        }
//        digit--;
//        sumBit -= Math.pow(10, digit - 1) * digit * 9;
//        // 确定是digit位数字中的第几个
//        int num = 1;
//        while (sumBit < n) {
//            sumBit += digit;
//            num++;
//        }
//        num--;
//        sumBit -= digit;
//        String resNumStr = String.valueOf(Math.pow(10, digit - 1) + num - 1);
//        // 注意这种转int的方法
//        return resNumStr.charAt(n - sumBit - 1) - '0';
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.findNthDigit(3);
////        int res = solution.findNthDigit(15);
////        int res = solution.findNthDigit(2147483647);
//        System.out.println(res);
//    }
//}
// 注意这里的0，计数和索引都是从0开始，所以1位数字x返回的就是x
class Solution {
    public int findNthDigit(int n) {
        // res所在num的数位
        int digit = 1;
        // res所在数位的头一个数字
        long start = 1;
        // digit位所有的数位之和
        // 起始至少一位应该是9而不是0
        // 注意这里应该用long，因为int的最大数所占的位数是大于int的取值范围的
        long count = 9;
        // 这样比较很巧妙，用n剩下的和下一位数所占的所有数位进行比较
        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = start * digit * 9;
        }
        // 这样很巧妙，跳出循环时start和digit刚好是应该的数字
        //100101102103
        // 注意这里这个公式给得很巧妙，第x个数，但是索引是加x-1
        // 对于第x个数的非最后一个数位，刚好是/下取整得到x-1，为了对最后一个数位进行下取整，所以对n-1
        // ！！！妙
        long num = start + (n - 1) / digit;
        // 注意-"0"的操作就可以转char为int了，妙
        // 这里(n-1)%digit也很巧妙地直接得到了以0为开头的数位的索引
        // 其实有时候把索引理解为相对偏移量在很多场景下就很好理解
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}

//leetcode submit region end(Prohibit modification and deletion)
