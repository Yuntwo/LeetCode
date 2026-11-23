// 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
//
// 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
//
// 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
//
// 请你返回「表现良好时间段」的最大长度。
//
//
//
// 示例 1：
//
//
// 输入：hours = [9,9,6,0,6,6,9]
// 输出：3
// 解释：最长的表现良好时间段是 [9,9,6]。
//
// 示例 2：
//
//
// 输入：hours = [6,6,6]
// 输出：0
//
//
//
//
// 提示：
//
//
// 1 <= hours.length <= 10⁴
// 0 <= hours[i] <= 16
//
//
// Related Topics 栈 数组 哈希表 前缀和 单调栈 👍 595 👎 0

// leetcode submit region begin(Prohibit modification and deletion)
// func longestWPI(hours []int) int {
// 	// 这题的核心不是递归、动态规划或贪心，
// 	// 而是：
// 	//  1. 先把原问题做“离散化/去均值式”转换
// 	//  2. 再把区间和问题转成前缀和大小关系
// 	//  3. 最后用单调栈把 O(n^2) 的枚举优化到 O(n)
// 	//
// 	// 对每一天：
// 	//   - 劳累的一天(hours[i] > 8) 记为 +1
// 	//   - 不劳累的一天(hours[i] <= 8) 记为 -1
// 	//
// 	// 这样一来，某个区间“劳累天数 > 不劳累天数”
// 	// 就等价于该区间内这些 +1/-1 的和 > 0。
// 	//
// 	// 这一步和很多“平均值 / 大于小于比较”的题是同一类套路：
// 	// 先把原本不好直接维护的条件，转换成方便求和的数值形式。
// 	//
// 	// 令 prefixSum[i] 表示前 i 天的离散值之和。
// 	// 那么区间 [l, r] 的和为：
// 	//   prefixSum[r+1] - prefixSum[l]
// 	//
// 	// 区间表现良好 <=> prefixSum[r+1] - prefixSum[l] > 0
// 	//                 <=> prefixSum[r+1] > prefixSum[l]
// 	//
// 	// 如果记：
// 	//   i = l
// 	//   j = r + 1
// 	// 则问题变成：
// 	//   找 i < j，满足 prefixSum[j] > prefixSum[i]，并让 j-i 最大。
// 	prefixSum := make([]int, len(hours)+1)
//
// 	// 注意这里必须保留一个“冗余前缀和” prefixSum[0] = 0。
// 	// 它表示“一个数都不取时的前缀和”。
// 	//
// 	// 没有这个冗余位，会漏掉所有从下标 0 开始的区间。
// 	// 例如某个合法区间恰好是 [0, r]，它对应的是：
// 	//   prefixSum[r+1] - prefixSum[0]
// 	// 如果 prefixSum[0] 不存在，这类答案就没法统一表达。
// 	for i := 1; i <= len(hours); i++ {
// 		if hours[i-1] > 8 {
// 			prefixSum[i] = prefixSum[i-1] + 1
// 		} else {
// 			prefixSum[i] = prefixSum[i-1] - 1
// 		}
// 	}
//
// 	// 单调递减栈里存的是“前缀和下标”，不是前缀和值本身。
// 	// 我们只保留那些“前缀和值不断创新低”的位置作为候选左端点。
// 	//
// 	// 为什么要单调递减栈，而不是递增栈？
// 	// 因为我们的目标条件是：
// 	//   prefixSum[j] > prefixSum[i]
// 	// 也就是说，左端点 i 的前缀和越小，越容易被右端点 j 满足。
// 	//
// 	// 如果存在 a < b 且 prefixSum[a] <= prefixSum[b]，
// 	// 那么 b 就不可能比 a 更优：
// 	//   1. a 更靠左，区间长度潜力更大
// 	//   2. a 的前缀和更小(或相等)，更容易满足 prefixSum[j] > prefixSum[a]
// 	// 所以 b 会被 a“支配”，没必要保留。
// 	//
// 	// 这正是单调递减栈的意义：
// 	// 栈里留下的，都是“更靠左且前缀和更小”的有效候选左端点。
// 	stack := make([]int, 0, len(hours)+1)
// 	for i := 0; i <= len(hours); i++ {
// 		if len(stack) == 0 || prefixSum[i] < prefixSum[stack[len(stack)-1]] {
// 			stack = append(stack, i)
// 		}
// 	}
//
// 	ans := 0
//
// 	// 从右往左枚举右端点对应的前缀下标 j。
// 	//
// 	// 之所以从右往左，是因为我们要让 j-i 最大，
// 	// 那么对固定的 i 来说，显然越靠右的 j 越好。
// 	//
// 	// 当某个 i 第一次被某个 j 满足时，
// 	// 这个 j 一定就是它能匹配到的最靠右的 j，
// 	// 因此这个 i 的最优答案已经确定，可以直接弹栈。
// 	//
// 	// 这也是为什么看起来像双层循环，复杂度却仍然是 O(n)：
// 	// 每个下标最多入栈一次、出栈一次。
// 	for j := len(hours); j >= 1; j-- {
// 		for len(stack) > 0 && prefixSum[j] > prefixSum[stack[len(stack)-1]] {
// 			ans = max(ans, j-stack[len(stack)-1])
// 			stack = stack[:len(stack)-1]
// 		}
// 	}
// 	return ans
// }

// 用prefixSum[i+1] = prefixSum[i]这种理解方式，但是本质后半部分是一样的，i的含义也是应该是一样的
func longestWPI(hours []int) int {
	// preixSum[i]表示hours[0]...hours[i-1]左闭右必的和，hours中前i个值的和
	prefixSum := make([]int, len(hours)+1)
	for i := 0; i < len(hours); i++ {
		if hours[i] > 8 {
			prefixSum[i+1] = prefixSum[i] + 1
		} else {
			prefixSum[i+1] = prefixSum[i] - 1
		}
    }
	stack := []int{}
	for i := 0; i <= len(hours); i++ {
		if len(stack) == 0 || prefixSum[stack[len(stack)-1]] > prefixSum[i] {
			stack = append(stack, i)
		}
	}
	ans := 0
	for j := len(hours); j >= 0; j-- {
		for len(stack) > 0 && prefixSum[j] > prefixSum[stack[len(stack)-1]] {
			ans = max(ans, j - stack[len(stack)-1])
			stack = stack[:len(stack) - 1]
		}
	}
	return ans
}

// leetcode submit region end(Prohibit modification and deletion)
