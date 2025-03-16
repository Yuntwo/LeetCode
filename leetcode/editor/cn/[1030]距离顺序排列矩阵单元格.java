//给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
//
// 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
//
// 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈
//顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
//
//
//
// 示例 1：
//
// 输入：R = 1, C = 2, r0 = 0, c0 = 0
//输出：[[0,0],[0,1]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
//
//
// 示例 2：
//
// 输入：R = 2, C = 2, r0 = 0, c0 = 1
//输出：[[0,1],[0,0],[1,1],[1,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
//[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
//
//
// 示例 3：
//
// 输入：R = 2, C = 3, r0 = 1, c0 = 2
//输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
//其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
//
//
//
//
// 提示：
//
//
// 1 <= R <= 100
// 1 <= C <= 100
// 0 <= r0 < R
// 0 <= c0 < C
//
// Related Topics 排序
// 👍 45 👎 0

/**
 * 问题总结：
 * 一、学习到的排序都是一维数组，但是这里涉及到了二维数组，要先把二维数组展平；不知道有没有好办法可以直接进行
 *    二维数组格式的排序
 * 二、这里要要开辟两个存储空间，一个计排序值，一个计位置，当值交换位置时，位置数组也要交换位置
 *    1）能不能直接用索引方式记录位置，而不单独开辟位置空间
 *    2）能不能用Hash值或者啥键值对，能够直接唯一根据值定位每一个排序元素然后对应位置
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
    int length = R * C;
    int[] distance = new int[length];
    int[][] index = new int[length][2];
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        distance[i * C + j] = this.abs(i - r0) + this.abs(j - c0);
        index[i * C + j] = new int[]{i, j};
      }
    }
    quickSort(distance, index, 0, length - 1);
    return index;
  }

  private int abs(int x) {
    if (x < 0) {
      return -x;
    }
    return x;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private void swap(int[][] array, int i, int j) {
    int[] temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    TestSolution solution = new TestSolution();
    int[][] test = solution.allCellsDistOrder(2, 3, 1, 2);

    System.out.println(test);
  }

  // 其实核心排序过程在partition中，通过排序找到partition
  private int partition(int[] array, int[][] index, int l, int r) {
    // v是标准值而非位置点
    int v = array[l];
    // 这种初始化保证了迭代过程中我初始还未排序时也满足条件[l+1,j]<v,[j+1,i-1]>v
    // 初始情况这两个集合都为空，也就是都没有值
    // j表示已排小数组的上界
    int j = l;
    // i的含义是当前位置，还未排序的元素
    for (int i = l + 1; i <= r; i++) {
      if (array[i] < v) {
        j++;
        swap(array, i, j);
        swap(index, i, j);
      }
    }
    swap(array, l, j);
    swap(index, l, j);
    return j;
  }

  // Q:递归似乎好像没有任何具体的操作
  // A:具体的排序操作在partition中
  private void quickSort(int[] array, int[][] index, int l, int r) {
    if (l >= r) {
      return;
    }
    // Q:但是这样的递归似乎好像就不能对index进行排序了
    // A:可以，还是在partition中
    int p = this.partition(array, index, l, r);
    // 注意这里的上下界没有变
    quickSort(array, index, l, p- 1);
    quickSort(array, index, p + 1, r);
  }
}
//leetcode submit region end(Prohibit modification and deletion)
