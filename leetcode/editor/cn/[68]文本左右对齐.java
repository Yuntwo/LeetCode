//给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
//
// 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
//
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//
// 注意:
//
//
// 单词是指由非空格字符组成的字符序列。
// 每个单词的长度大于 0，小于等于 maxWidth。
// 输入单词数组 words 至少包含一个单词。
//
//
//
//
// 示例 1:
//
//
//输入: words = ["This", "is", "an", "example", "of", "text", "justification."],
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//
//
// 示例 2:
//
//
//输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。
//     第二行同样为左对齐，这是因为这行只包含一个单词。
//
//
// 示例 3:
//
//
//输入:words = ["Science","is","what","we","understand","well","enough","to",
//"explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 2
//0
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
//
//
//
// 提示:
//
//
// 1 <= words.length <= 300
// 1 <= words[i].length <= 20
// words[i] 由小写英文字母和符号组成
// 1 <= maxWidth <= 100
// words[i].length <= maxWidth
//
// Related Topics 字符串 模拟 👍 263 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> res = new ArrayList<String>();
        int start = 0;
        int end = start;
        while (start < words.length) {
            end = findEnd(words, start, maxWidth);
            String temp;
            // 如果不是最后一行中间对齐
            if (end != words.length - 1) {
                temp = centerBalance(words, start, end, maxWidth);
                // 如果是最后一行左对齐
            } else {
                temp = leftBalance(words, start, end, maxWidth);
            }
            res.add(temp);
            // 不要忘记修改start的值
            start = end + 1;
        }
        return res;
    }

    private String centerBalance(String[] words, int start, int end, int M) {
        int len = 0;
        for (int i = start; i <= end; i++) {
            // 注意String获取长度的方法
            len += words[i].length();
        }
        int num = end - start;
        // 注意这个特殊情况要单独处理
        if (num == 0) {
            String res = words[start];
            res += spaceS(M - words[start].length());
            return res;
        }
        int numSpace = (M - len) / num;
        // 注意Java不允许int强转boolean
//        int notBalanced = (M - len) % numSpace;
        // 注意这个值该怎么赋
        int notBalanced = (M - len) % num;
        String res = words[start];
        // 注意这里多处细节
        if (notBalanced != 0) {
            for (int i = 0; i < notBalanced; i++) {
                res += spaceS(numSpace + 1);
                res += words[start + 1 + i];
            }
            for (int i = 0; i < num - notBalanced; i++) {
                res += spaceS(numSpace);
                res += words[start + 1 + notBalanced + i];
            }
        } else {
            for (int i = 0; i < num; i++) {
                res += spaceS(numSpace);
                res += words[start + 1 + i];
            }
        }
        return res;
    }

    // 注意最后一行是左对齐，要单独考虑
    private String leftBalance(String[] words, int start, int end, int M) {
        String res = words[start];
        int tempLength = words[start].length();
        for (int i = start + 1; i <= end; i++) {
            res += spaceS(1);
            res += words[i];
            tempLength = tempLength + 1 + words[i].length();
        }
        if (tempLength < M) {
            res += spaceS(M - tempLength);
        }
        return res;
    }

    private int findEnd(String[] words, int start, int M) {
        // 注意在没有算上length之前应该让rear在head之前，很多都是这样
        int end = start;
        int tempLength = words[start].length();
        // 注意这里应该是<=，只有>才不满足才需要--end
        // 注意两个控制条件，不能越界访问
        // 注意是小于，当达到=的时候没必要再循环了
        // 这样控制的话
        while (tempLength < M && end < words.length - 1) {
            // 注意String获取长度的方法
            // 注意单词之间至少要1个空格，所以需要先将这个长度加上
            tempLength += (1 + words[++end].length());
        }
        // 这里判断情况真是有点绕
        // 如果是tempLength<M，那么跳出循环一定是因为end==words.length-1到达了边界
        if (tempLength < M) {
            return end;
            // 如果是tempLength==M，那么跳出循环一定是因为
        } else if (tempLength == M) {
            return end;
        } else {
            return --end;
        }
        // 注意这里要分情况返回，end的情况不一样
        // 如果是end==words.length - 1，那么不会进到循环里end++，直接返回end
        // 注意这里的if else并不一定是互斥的关系
//        if (end == words.length - 1) {
//            return end;
//        // 如果是tempLength <= M，会进到循环end++，那么返回是就要--end
//        } else {
//            // 注意是前--
//            return --end;
//        }
    }

    private String spaceS(int num) {
        String res = new String();
        for (int i = 0; i < num; i++) {
            res += ' ';
        }
        return res;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        // 注意数组初始化方法
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = test.fullJustify(words, 16);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
