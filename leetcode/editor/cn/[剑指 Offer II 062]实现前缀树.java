//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。
//
// 请你实现 Trie 类：
//
//
// Trie() 初始化前缀树对象。
// void insert(String word) 向前缀树中插入字符串 word 。
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回
//false 。
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。
//
//
//
//
// 示例：
//
//
//输入
//inputs = ["Trie", "insert", "search", "search", "startsWith", "insert",
//"search"]
//inputs = [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
//
//
//
//
// 提示：
//
//
// 1 <= word.length, prefix.length <= 2000
// word 和 prefix 仅由小写英文字母组成
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 10⁴ 次
//
//
//
//
//
//
//
// 注意：本题与主站 208 题相同：https://leetcode-cn.com/problems/implement-trie-prefix-tree/
//
//
// Related Topics 设计 字典树 哈希表 字符串 👍 46 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 */
class Trie {

    class TreeNode {

        public boolean isEnd = false;

        public TreeNode[] dict = new TreeNode[26];

    }

    private TreeNode root = new TreeNode();

    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode p = root;
        for (int i = 0; i < word.length(); i++) {
            if (p.dict[word.charAt(i) - 'a'] == null) {
                p.dict[word.charAt(i) - 'a'] = new TreeNode();
            }
            p = p.dict[word.charAt(i) - 'a'];
        }
        p.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode p = root;
        for (int i = 0; i < word.length(); i++) {
            if (p.dict[word.charAt(i) - 'a'] == null) {
                return false;
            }
            p = p.dict[word.charAt(i) - 'a'];
        }
        return p.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * 注意区别search和prefix的区别，prefix必须非一个单词的结束，所以需要额外标记这个信息
     * 注意startWith包含了一个单词的结束或者非结束两种情况
     */
    public boolean startsWith(String prefix) {
        TreeNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (p.dict[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            p = p.dict[prefix.charAt(i) - 'a'];
        }
        return true;

    }
}


/**
 * Success1
 * 效率没有100%
 */
//class Trie {
//
//    /**
//     * 注意TrieTree需要特殊的TreeNode定义
//     */
//    class TreeNode {
//        private boolean isEnd = false;
//        private TreeNode[] next = new TreeNode[26];
//    }
//
//    private TreeNode root;
//
//    /** Initialize your data structure here. */
//    public Trie() {
//        // root就是单TreeNode节点，没啥实质性作用，但是作为起点必不可少
//        root = new TreeNode();
//    }
//
//    /** Inserts a word into the trie. */
//    public void insert(String word) {
//        TreeNode currNode = root;
//        for (int i = 0; i < word.length(); i++) {
//            if (currNode.next[word.charAt(i) - 'a'] == null) {
//                currNode.next[word.charAt(i) - 'a'] = new TreeNode();
//            }
//            currNode = currNode.next[word.charAt(i) - 'a'];
//        }
//        // 注意计数规律，这里currNode直接就是单词最后一个字母的位置
//        currNode.isEnd = true;
//    }
//
//    /** Returns if the word is in the trie. */
//    public boolean search(String word) {
//        // 注意对于一个控制变量来说，它的内涵并不一直是它名称表示的内涵，而是有两种属性进行变换才能控制变量
//        TreeNode currNode = root;
//        for (int i = 0; i < word.length(); i++) {
//            if (currNode.next[word.charAt(i) - 'a'] == null) {
//                return false;
//            }
//            // 到了这里currNode才是真正对应的第i个字母的Node位置
//            currNode = currNode.next[word.charAt(i) - 'a'];
//        }
//        // 这个判断是必须的
//        if (!currNode.isEnd) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Returns if there is any word in the trie that starts with the given prefix.
//     */
//    public boolean startsWith(String prefix) {
//        TreeNode currNode = root;
//        for (int i = 0; i < prefix.length(); i++) {
//            if (currNode.next[prefix.charAt(i) - 'a'] == null) {
//                return false;
//            }
//            currNode = currNode.next[prefix.charAt(i) - 'a'];
//        }
//        // 注意前缀包含这个单词本身，所以这个判断不需要
////        if (currNode.isEnd) {
////            return false;
////        }
//        return true;
//    }
//}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
