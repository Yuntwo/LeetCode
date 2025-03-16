//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
//
//
// 示例 1:
//
// 输入: [10,2]
//输出: "102"
//
// 示例 2:
//
// 输入: [3,30,34,5,9]
//输出: "3033459"
//
//
//
// 提示:
//
//
// 0 < nums.length <= 100
//
//
// 说明:
//
//
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
//
//
// Related Topics 贪心 字符串 排序 👍 552 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 这里是用的StringBuilder字符串拼接的方式合并数字，比较占内存
//class Solution {
//    public String minNumber(int[] nums) {
//        // 这里用快排，实际上用什么排序都可以
//        quickSort(nums, 0, nums.length - 1);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < nums.length; i++) {
//            stringBuilder.append(nums[i]);
//        }
//        return stringBuilder.toString();
//    }
//
//    private void quickSort(int[] nums, int start, int end) {
//        if (start < end) {
//            int pivotPos = partition(nums, start, end);
//            quickSort(nums, start, pivotPos - 1);
//            quickSort(nums,pivotPos + 1, end);
//        }
//    }
//
//    private int partition(int[] nums, int start, int end) {
//        int pivot = nums[start];
//        while (start < end) {
//            while (start < end && !less(nums[end], pivot)) end--;
//            nums[start] = nums[end];
//            while (start < end && (less(nums[start], pivot) || nums[start] == pivot)) start++;
//            nums[end] = nums[start];
//        }
//        nums[start] = pivot;
//        return start;
//    }
//
//    // 这道题的关键在于这个比较规则！！！
//    private boolean less(int x, int y) {
//        // 注意StringBuilder每一次append之后返回的StringBuilder对象是它本身
//        String xy = new StringBuilder().append(x).append(y).toString();
//        String yx = new StringBuilder().append(y).append(x).toString();
//        if (xy.compareTo(yx) < 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = {10, 2};
//        String res = solution.minNumber(nums);
//        System.out.println(res);
//    }
//}
// 用计算的方式合并数字，占内存较少
// 这样做似乎不太行，会数字溢出，不知道那个答案还有哪里进行了改进
class Solution {
    public String minNumber(int[] nums) {
        // 这里用快排，实际上用什么排序都可以
        quickSort(nums, 0, nums.length - 1);
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = merge(res, nums[i]);
        }
        return String.valueOf(res);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivotPos = partition(nums, start, end);
            quickSort(nums, start, pivotPos - 1);
            quickSort(nums,pivotPos + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (start < end && !less(nums[end], pivot)) end--;
            nums[start] = nums[end];
            while (start < end && (less(nums[start], pivot) || nums[start] == pivot)) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    // 这道题的关键在于这个比较规则！！！
    private boolean less(int x, int y) {
        int xy = merge(x, y);
        int yx = merge(y, x);
        if (xy < yx) {
            return true;
        }
        return false;
    }

    // 返回x在y前面的拼接数字
    private int merge(int x, int y) {
        int temp = y;
        // 注意这样计算少一位
        while (temp / 10 != 0) {
            x *= 10;
            // 注意temp也是要除的
            temp /= 10;
        }
        // 注意只有一位的情况(0也是占一位的)
        return x * 10 + y;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {999999998,999999997,999999999};
        String res = solution.minNumber(nums);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
