//给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
//
//
// 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
// 总成本必须恰好等于 target 。
// 添加的数位中没有数字 0 。
//
//
// 由于答案可能会很大，请你以字符串形式返回。
//
// 如果按照上述要求无法得到任何整数，请你返回 "0" 。
//
//
//
// 示例 1：
//
//
//输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
//输出："7772"
//解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要
//求的数字，但 "7772" 是较大的数字。
// 数字     成本
//  1  ->   4
//  2  ->   3
//  3  ->   2
//  4  ->   5
//  5  ->   6
//  6  ->   7
//  7  ->   2
//  8  ->   5
//  9  ->   5
//
//
// 示例 2：
//
//
//输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
//输出："85"
//解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
//
//
// 示例 3：
//
//
//输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
//输出："0"
//解释：总成本是 target 的条件下，无法生成任何整数。
//
//
// 示例 4：
//
//
//输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
//输出："32211"
//
//
//
//
// 提示：
//
//
// cost.length == 9
// 1 <= cost[i] <= 5000
// 1 <= target <= 5000
//
//
// Related Topics 数组 动态规划 👍 161 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Review1
 */
class Solution {

    public String largestNumber(int[] cost, int target) {
        String[] dp = new String[target + 1];
        dp[0] = "";
        for (int i = 1; i <= target; i++) {
            // 注意这里不能将其赋为空字符串进行判断，只能判null，否则就认为是这个成本上有一个空字符串可供拼接
//            dp[i] = "";
            for (int j = cost.length; j >= 1; j--) {
//                if (cost[j - 1] <= i) {
                // 注意加上这个条件
                if (cost[j - 1] <= i && dp[i - cost[j - 1]] != null) {
//                    dp[i] = max(dp[i], String.valueOf(j) + dp[i - cost[j - 1]]);
                    // 这里直接比较还方便一点，String类型支持compareTo方法直接比较
                    // 注意因为compareTO比较的是字典序，所以只有长度相同的时候才有用
                    // 字典序规则："9".compareTo("1111")>0，"1234".compareTo("123456")<0，"123456".compareTo("1234")>0
                    if (dp[i] == null || dp[i].length() <= dp[i - cost[j - 1]].length() || dp[i].compareTo(String.valueOf(j) + dp[i - cost[j - 1]]) < 0) {
                        dp[i] = String.valueOf(j) + dp[i - cost[j - 1]];
                    }
                }
            }
        }
//        return dp[target];
        // 注意这里为null的话返回"0"
        return dp[target] == null ? "0" : dp[target];
    }

    private String max(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return s1;
        } else if (s2.length() > s1.length()) {
            return s2;
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) > s2.charAt(i)) {
                    return s1;
                } else if (s2.charAt(i) > s1.charAt(i)) {
                    return s2;
                }
            }
        }
        return s1;
    }

}

/**
 * Success2
 * 写法、思路更简单，效率一般
 */
//class Solution {
//
//    public String largestNumber(int[] cost, int target) {
//        String[] dp = new String[target + 1];
//        dp[0] = "";
//        for (int i = 1; i <= target; i++) {
//            for (int j = 0; j < cost.length; j++) {
//                // 注意这里要判null
//                if (cost[j] <= i && dp[i - cost[j]] != null) {
//                    // 注意这里两种方式怎么拼都可以，不影响，这种拼接不保证遍历排列，但是保证最优解构造
////                    String candidate = dp[i - cost[j]] + (j + 1);
//                    String candidate = (j + 1) + dp[i - cost[j]];
//                    // 注意candidate.length() == dp[i].length()判断是必须的
//                    // 因为compareTo比较的是字典序，字典序不直接比较字符串长度，只是从第一个字符逐个比较直到有不同的或者其中一个字符串终结
//                    if (dp[i] == null || candidate.length() > dp[i].length() || (candidate.length() == dp[i].length() && candidate.compareTo(dp[i]) > 0)) {
//                        dp[i] = candidate;
//                    }
//                }
//            }
//        }
//        return dp[target] == null ? "0" : dp[target];
//    }
//
//}

/**
 * Failure1
 * 这样想简化反而让事情变得更复杂了
 */
//class Solution {
//    public String largestNumber(int[] cost, int target) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < cost.length; i++) {
//            hashMap.put(cost[i], Math.max(hashMap.getOrDefault(cost[i], i + 1), i + 1));
//        }
//        String[][] dp = new String[hashMap.size()][target + 1];
//        int[] costs = new int[hashMap.size()];
//        Iterator<Integer> iterator = hashMap.keySet().iterator();
//        for (int i = 0; i < hashMap.size(); i++) {
//            costs[i] = iterator.next();
//        }
//        for (int i = 1; i <= target; i++) {
//            for (int j = 0; j < hashMap.size(); j++) {
//                if (costs[j] <= i) {
//                    dp[j][i] = max(dp[j][i], insert(dp[j][i - 1], hashMap.get(costs[j])));
//                }
//            }
//        }
//        return dp[hashMap.size() - 1][target];
//    }
//
//    private String max(String a, String b) {
//        if (a == null || b == null) {
//            return a == null ? b : a;
//        } else if (a.length() > b.length()) {
//            return a;
//        } else if (a.length() < b.length()) {
//            return b;
//        } else {
//            for (int i = 0; i < a.length(); i++) {
//                if (a.charAt(i) < b.charAt(i)) {
//                    return b;
//                }
//            }
//        }
//        return a;
//    }
//
//    private String insert(String s, Integer c) {
//        if (s == null || s.length() == 0) {
//            return String.valueOf(c);
//        }
//        int index = 0;
//        while (index < s.length() && s.charAt(index) - '0' > c) {
//            index++;
//        }
//        String res = "";
//        if (index > 0) {
//            res += s.substring(0, index - 1);
//        }
//        res += String.valueOf(c);
//        if (index < s.length()) {
//            res += s.substring(index, s.length() - 1);
//        }
//        return res;
//    }
//}

/**
 * Success1
 * 动态规划，时间和空间效率都挺低的
 */
//class Solution {
//    public String largestNumber(int[] cost, int target) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        String[] res = new String[target + 1];
//        for (int i = 0; i < cost.length; i++) {
//            hashMap.put(cost[i], Integer.max(hashMap.getOrDefault(cost[i], Integer.MIN_VALUE), i + 1));
//            // 注意这里题目中的cost[i]可能大于target，要控制这种情况
//            if (cost[i] <= target) {
//                res[cost[i]] = String.valueOf(hashMap.get(cost[i]));
//            }
//        }
//        for (int i = 1; i < target + 1; i++) {
//            for (int j = 1; j <= i; j++) {
//                // 注意如果res[i-j]为null的话，说明根本没有这种方案，因为不能浪费必须是恰好，所以也不能把它当做""处理
//                if (hashMap.containsKey(j) && res[i - j] != null) {
//                    res[i] = max(res[i], insert(res[i - j], hashMap.get(j)));
//                }
//            }
//        }
//        return res[target] == null ? "0" : res[target];
//    }
//
//    private String max(String a, String b) {
//        if (a == null) {
//            return b;
//        } else if (b == null) {
//            return a;
//        }
//        if (a.length() > b.length()) {
//            return a;
//        } else if (a.length() < b.length()) {
//            return b;
//        } else {
//            for (int i = 0; i < a.length(); i++) {
//                if (a.charAt(i) > b.charAt(i)) {
//                    return a;
//                } else if (a.charAt(i) < b.charAt(i)) {
//                    return b;
//                }
//            }
//        }
//        return a;
//    }
//
//    private String insert(String a, int b) {
//        StringBuilder res = new StringBuilder();
//        int i = 0;
//        while (i < a.length() && Integer.valueOf(a.charAt(i)) > b) {
//            res.append(a.charAt(i));
//            i++;
//        }
//        res.append(b);
//        while (i < a.length()) {
//            res.append(a.charAt(i));
//        }
//        return res.toString();
//    }
//}

/**
 * Failure1
 * 超时
 */
//class Solution {
//    public String largestNumber(int[] cost, int target) {
//        String[] res = new String[target + 1];
//        // 注意这个赋初值也必须有，注意这里的循环次数是target+1，下面的循环次数是cost.length
//        // 内涵不一样
//        for (int i = 0; i <= target; i++) {
//            res[i] = "";
//        }
//        // 注意必须有这个初始值判断，否则根本凑不了
//        for (int i = 0; i < cost.length; i++) {
//            if (cost[i] <= target) {
//                res[cost[i]] = max(res[cost[i]], String.valueOf(i + 1));
//            }
//        }
//        boolean change = true;
//        int count = 0;
//        // 这里确实是不知道要循环多少次啊
//        // 最多循环这么多次吧
//        // 但是这样循环确实太久了
////        while (count <= target) {
//        while (change) {
//            change = false;
//            for (int i = target; i >= 1; i--) {
//                for (int j = cost.length; j >= 1; j--) {
//                    if (cost[j - 1] <= i) {
//                        // 注意必须要有个判断条件，否则根本凑不了
//                        if (!res[i - cost[j - 1]].equals("")) {
//                            // 在这种情况下才认为这一趟循环改变了什么，一趟循环只要有一个改变了就行
//                            // 注意一定要是这两个条件同时满足才行
//                            if (!res[i].equals(String.valueOf(j) + res[i - cost[j - 1]]) && !res[i].equals(max(res[i], String.valueOf(j) + res[i - cost[j - 1]]))) {
//                                res[i] = String.valueOf(j) + res[i - cost[j - 1]];
////                                res[i] = max(res[i], String.valueOf(j) + res[i - cost[j - 1]]);
//                                change = true;
//                                // 注意因为是从大到小的数位寻找的，所以找到了就可以跳出内层循环，不用再往后面找了
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//            count++;
//        }
//        if (res[target].equals("")) {
//            return "0";
//        }
//        return res[target];
//    }
//
//    private String max(String a, String b) {
//        if (a.length() != b.length()) {
//            return a.length() > b.length() ? a : b;
//        } else {
//            // 注意从高位开始比较
//            for (int i = 0; i <= a.length() - 1; i++) {
//                // 注意char不能直接比较大小
//                if ((a.charAt(i) - '0') > (b.charAt(i) - '0')) {
//                    return a;
//                } else if ((a.charAt(i) - '0') < (b.charAt(i) - '0')) {
//                    return b;
//                }
//            }
//        }
//        return a;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] cost = new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5};
////        int target = 9;
////        int[] cost = new int[]{7, 6, 5, 5, 5, 6, 8, 7, 8};
////        int target = 12;
////        int[] cost = new int[]{6, 39, 65, 35, 21, 397, 366, 328, 396};
////        int target = 871;
//        int[] cost = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
//        int target = 5000;
//        String res = solution.largestNumber(cost, target);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
