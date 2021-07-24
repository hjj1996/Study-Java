package leetCode;

import java.util.Arrays;

/**
 * @author hjj199612
 * @date 2021/5/4 4:41 下午
 * @description 1473. 粉刷房子 III
 * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
 * <p>
 * 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
 * <p>
 * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 * <p>
 * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 * cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target  个街区。如果没有可用的涂色方案，请返回 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/paint-house-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_1473 {

    public static void main(String[] args) {

    }

    public int minCost(int[] houses, int[][] cost, int m, int n,int target) {
        int INF = Integer.MAX_VALUE;
        int[][][] f = new int[m][n][target];
        for (int i = 0; i < m; i++) houses[i]--;
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) Arrays.fill(f[i][j], INF);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i] == -1) {
                    for (int k = 0; k <= i && k < target; k++) {
                        if (i == 0) {
                            f[i][j][k] = cost[i][j];
                            continue;
                        }
                        for (int pj = 0; pj < n; pj++) {
                            if (pj == j) {
                                f[i][j][k] = Math.min(f[i][j][k], f[i - 1][pj][k]);
                            } else {
                                if (k >= 1) {
                                    f[i][j][k] = Math.min(f[i][j][k], f[i - 1][pj][k - 1]);
                                }
                            }
                        }
                        if (f[i][j][k] != INF) f[i][j][k] += cost[i][j];
                    }
                } else {
                    if (houses[i] != j) continue;
                    for (int k = 0; k <= i && k < target; k++) {
                        if (i == 0) {
                            f[i][j][k] = 0;
                            continue;
                        }
                        for (int pj = 0; pj < n; pj++) {
                            if (pj == j) {
                                f[i][j][k] = Math.min(f[i][j][k], f[i - 1][pj][k]);
                            } else if (k >= 1) {
                                f[i][j][k] = Math.min(f[i][j][k], f[i - 1][pj][k - 1]);
                            }
                        }
                    }
                }
            }
        }
        int ans = INF;
        for (int j = 0; j < n; j++) ans = Math.min(ans, f[m - 1][j][target - 1]);
        return ans == INF ? -1 : ans;
    }
}