//给定一个三角形 triangle ，找出自顶向下的最小路径和。
//
// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
//正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
//
//
//
// 示例 1：
//
//
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：如下面简图所示：
//   2
//  3 4
// 6 5 7
//4 1 8 3
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//
//
// 示例 2：
//
//
//输入：triangle = [[-10]]
//输出：-10
//
//
//
//
// 提示：
//
//
// 1 <= triangle.length <= 200
// triangle[0].length == 1
// triangle[i].length == triangle[i - 1].length + 1
// -10⁴ <= triangle[i][j] <= 10⁴
//
//
//
//
// 进阶：
//
//
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
//
// Related Topics 数组 动态规划 👍 1012 👎 0

import java.util.*;
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 自顶向下记忆化搜索
 */
// 其实就连模板函数调用逻辑都差不多啦
//class Solution {
//    public int minimumTotal(List<List<Integer>> triangle) {
//        // 基于这道题的数值特点，默认数值取哪个还真不好办
//        int[] num = new int[triangle.size() * (triangle.size() + 1) / 2];
//        for (int i = 0; i < triangle.size() * (triangle.size() + 1) / 2; i++) {
//            num[i] = 100000;
//        }
//        return mini(0, 0, triangle, num);
//    }
//
//    private int mini(int i, int j, List<List<Integer>> triangle, int[] num) {
//        // 注意这里是索引要-1
//        if (i == triangle.size() - 1) {
//            num[index(i, j)] = triangle.get(i).get(j);
//            return num[index(i, j)];
//        }
//        // 注意这里本来记忆化搜寻就是为了减小一些不必要的递归，但是一定要区分什么时候直接用值，什么时候必须递归赋值
//        // 否则要么记忆化数组不起作用，要么记忆化数组无效!!!
//        int temp1;
//        int temp2;
//        if (num[index(i + 1, j)] == 100000) {
//            temp1 = triangle.get(i).get(j) + mini(i + 1, j, triangle, num);
//        } else {
//            temp1 = triangle.get(i).get(j) + num[index(i + 1, j)];
//        }
//        if (num[index(i + 1, j + 1)] == 100000) {
//            temp2 = triangle.get(i).get(j) + mini(i + 1, j + 1, triangle, num);
//        } else {
//            temp2 = triangle.get(i).get(j) + num[index(i + 1, j + 1)];
//        }
//        // 注意是最小路径和
//        if (temp1 < temp2) {
//            num[index(i, j)] = temp1;
//        } else {
//            num[index(i, j)] = temp2;
//        }
//        return num[index(i, j)];
//    }
//
//    private int index(int i, int j) {
//        return i * (i + 1) / 2 + j;
//    }
//
//    public static void main(String[] args) {
//        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
//        List<Integer> list1 = new ArrayList<Integer>();
//        list1.add(2);
//        List<Integer> list2 = new ArrayList<Integer>();
//        list2.add(3);
//        list2.add(4);
//        List<Integer> list3 = new ArrayList<Integer>();
//        list3.add(6);
//        list3.add(5);
//        list3.add(7);
//        List<Integer> list4 = new ArrayList<Integer>();
//        list4.add(4);
//        list4.add(1);
//        list4.add(8);
//        list4.add(3);
//        triangle.add(list1);
//        triangle.add(list2);
//        triangle.add(list3);
//        triangle.add(list4);
//        Solution solution = new Solution();
//        int res = solution.minimumTotal(triangle);
//        System.out.println(res);
//    }
//}


/**
 * 自底向上动态规划
 */
// 这种思路写起来很爽很快不容易错
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 基于这道题的数值特点，默认数值取哪个还真不好办
        int[] num = new int[triangle.size() * (triangle.size() + 1) / 2];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 注意这里一定是if else的关系，否则会数组访问越界
                if (i == triangle.size() - 1) {
                    num[index(i, j)] = triangle.get(i).get(j);
                } else {
                    int temp1 = triangle.get(i).get(j) + num[index(i + 1, j)];
                    int temp2 = triangle.get(i).get(j) + num[index(i + 1, j + 1)];
                    if (temp1 < temp2) {
                        num[index(i, j)] = temp1;
                    } else {
                        num[index(i, j)] = temp2;
                    }
                }
            }
        }
        return num[0];
    }

    private int index(int i, int j) {
        return i * (i + 1) / 2 + j;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<Integer>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<Integer>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        TestSolution solution = new TestSolution();
        int res = solution.minimumTotal(triangle);
        System.out.println(res);
    }
}
// 注意你定义的类一定要在这两行注释中间，否则系统会识别不了，这样有点奇怪
//leetcode submit region end(Prohibit modification and deletion)
