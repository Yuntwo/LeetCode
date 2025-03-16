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
// Related Topics 设计 字典树 哈希表 字符串 👍 62 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 */
class Trie {

    class TreeNode {

        public TreeNode(boolean isEnd) {
            this.isEnd = false;
            child = new TreeNode[27];
        }

        public boolean isEnd;

        public TreeNode[] child;
    }


    private TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode(false);
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.child[word.charAt(i) - 'a'] == null) {
                node.child[word.charAt(i) - 'a'] = new TreeNode(false);
            }
            node = node.child[word.charAt(i) - 'a'];
        }
        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     * 注意如果这个字符串是树中某个字符串的子字符串，不认为它在这个树中
     * 注意如果这个字符串是树中某个字符串的前缀，认为它在这个树中，因为这两种字符串共用一条路径，所以需要额外的isEnd来记录状态
     */
    public boolean search(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.child[word.charAt(i) - 'a'] == null) {
                return false;
            }
            node = node.child[word.charAt(i) - 'a'];
        }
        return node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TreeNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (node.child[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            node = node.child[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
