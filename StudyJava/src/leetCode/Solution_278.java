package leetCode;

/**
 * @author hjj199612
 * @date 2021/6/13 4:24 下午
 * @description 278. 第一个错误的版本
 * 是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-bad-version
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_278 {

    public static void main(String[] args) {

    }

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
//            int mid = (l + r) >> 1;有溢出风险
            int mid = l + ((r - l) >> 1);
            if (isBadVersion(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private boolean isBadVersion(int n) {
        return false;
    }
}
