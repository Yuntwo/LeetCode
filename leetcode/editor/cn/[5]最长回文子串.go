//给你一个字符串 s，找到 s 中最长的 回文 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 Manacher 算法 👍 8201 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
func longestPalindrome(s string) string {
	start, end := 0, 0
	expand := func(left, right int) {
		for left >= 0 && right < len(s) && s[left] == s[right] {
			left--
			right++
        }
		if right - left - 2 > end - start {
			start = left + 1
			end = right - 1
        }
	}
	for i := 0; i < len(s); i++ {
		expand(i, i)
		expand(i, i+1)
    }
	return s[start:end+1]
}
//leetcode submit region end(Prohibit modification and deletion)
