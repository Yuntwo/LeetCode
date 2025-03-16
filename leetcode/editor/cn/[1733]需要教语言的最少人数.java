//在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
//
// 给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
//
//
// 总共有 n 种语言，编号从 1 到 n 。
// languages[i] 是第 i 位用户掌握的语言集合。
// friendships[i] = [ui, vi] 表示 ui 和 vi 为好友关系。
//
//
// 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少 需要教会多少名用户。
//请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
//
//
//
// 示例 1：
//
//
//输入：n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
//输出：1
//解释：你可以选择教用户 1 第二门语言，也可以选择教用户 2 第一门语言。
//
//
// 示例 2：
//
//
//输入：n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],
//[2,3]]
//输出：2
//解释：教用户 1 和用户 3 第三门语言，需要教 2 名用户。
//
//
//
//
// 提示：
//
//
// 2 <= n <= 500
// languages.length == m
// 1 <= m <= 500
// 1 <= languages[i].length <= n
// 1 <= languages[i][j] <= n
// 1 <= ui < vi <= languages.length
// 1 <= friendships.length <= 500
// 所有的好友关系 (ui, vi) 都是唯一的。
// languages[i] 中包含的值互不相同。
//
// Related Topics 贪心 数组 👍 22 👎 0

import java.util.HashSet;
import java.util.Iterator;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    // 注意这种题的思路是从全局的总体规律出发解的，而不是你按照认为的顺序思考抽取复用程序做的
    // 这在思维上有本质区别
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        HashSet<Integer> strange = new HashSet<>();
        for (int i = 0; i < friendships.length; i++) {
            if (!inter(languages[friendships[i][0] - 1], languages[friendships[i][1] - 1])) {
                strange.add(friendships[i][0]);
                strange.add(friendships[i][1]);
            }
        }

        int[] languageMaster = new int[n];
        Iterator<Integer> iterator = strange.iterator();
        while (iterator.hasNext()) {
            // 一定要注意这里索引和人的标号的区别
            int i = iterator.next() - 1;
            for (int j = 0; j < languages[i].length; j++) {
                languageMaster[languages[i][j] - 1]++;
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            if (languageMaster[i] > max) {
                max = languageMaster[i];
            }
        }

        return strange.size() - max;
    }

    private boolean inter(int[] a, int[] b) {
        HashSet<Integer> set = new HashSet<>();
        boolean isInter = false;
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }

        for (int i = 0; i < b.length; i++) {
            if (set.contains(b[i])) {
                isInter = true;
                break;
            }
        }
        return isInter;
    }

    public static void main(String[] args) {
        int[][] languages = new int[][]{{1}, {2}, {1, 2}};
        int[][] friendships = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        Solution solution = new Solution();
        int result = solution.minimumTeachings(2, languages, friendships);
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
