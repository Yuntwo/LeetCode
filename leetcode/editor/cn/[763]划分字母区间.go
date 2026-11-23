//给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", 
//"cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。 
//
// 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。 
//
// 返回一个表示每个字符串片段的长度的列表。 
//
// 
//示例 1：
//
// 
//输入：s = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。 
//
// 示例 2： 
//
// 
//输入：s = "eccbbbbdec"
//输出：[10]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 贪心 哈希表 双指针 字符串 👍 1456 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 复习
func partitionLabels(s string) []int {
	// pos[c]表示c所在的片段至少应该涵盖的位置，注意是最少
	pos := map[rune]int{}
	for i, c := range s {
		pos[c] = i
    }
	res := []int{}
	// start和end表示片段的上下边界，far表示片段总所以字符应该至少涵盖的位置的最大值
	start, far:= 0, pos[0]
	// 这里是为了遍历所有片段并判断什么时候应该切
	// end递增，它的判断肯定是涵盖了start的
	for end, c := range(s) {
		far = max(far, pos[c])
		if end == far {
			res = append(res, end - start + 1)
			start = end + 1
        }
    }
	return res
}

// 精简版
// func partitionLabels(s string) []int {
// 	pos := map[rune]int{}
// 	for i, c := range s {
// 		pos[c] = i
// 	}
// 	res := []int{}
// 	// right表示当前看到的片段中应该到达的最远位置
// 	// 遍历的时候一方面要更新right，一方面如果当前片段到达了right的位置说明全部满足条件了，并且为了切更多这个时候就应该切了
// 	start, right:= 0, 0
// 	for i, c := range s {
// 		right = max(pos[c], right)
// 		if i == right {
// 			res = append(res, i - start + 1)
// 			start = i + 1
// 		}
// 	}
// 	return res
// }

// 粗糙版
// func partitionLabels(s string) []int {
//     pos := map[rune]int{}
// 	for i, c := range s {
// 		// 这里其实可以不用判断，直接用递增的最大值覆盖即可
// 		p, ok := pos[c]
// 		if ok {
//            pos[c] = max(p, i)
//         }  else {
// 			pos[c] = i
//         }
//     }
// 	res := []int{}
// 	temp := 0
// 	right := 0
// 	for i, c := range s {
// 		p, _ := pos[c]
// 		right = max(p, right)
// 		if i == right {
// 			res = append(res, i - temp + 1)
// 			temp = i + 1
//         }
//     }
// 	return res
// }
//leetcode submit region end(Prohibit modification and deletion)
