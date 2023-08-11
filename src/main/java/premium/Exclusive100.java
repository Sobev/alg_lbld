package premium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luojx
 * @date 2023/4/17 11:22
 */
public class Exclusive100 {
    /**
     * 给定m个数组，每个数组都已经按照升序排好序了。
     * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
     * 两个整数a和b之间的距离定义为它们差的绝对值|a-b|。你的任务就是去找到最大距离
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * [[1,2,3],
     * [4,5],
     * [1,2,3]]
     * 输出： 4
     * 解释：
     * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
     * <p>
     * <p>
     * 注意：
     * <p>
     * 每个给定数组至少会有 1 个数字。列表中至少有两个非空数组。
     * 所有m个数组中的数字总数目在范围 [2, 10000] 内。
     * m个数组中所有整数的范围在 [-10000, 10000] 内。
     *
     * @param arrays
     * @return
     */
    public int maxDistance(List<List<Integer>> arrays) {
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int md = 0;
        for (int i = 1; i < arrays.size(); i++) {
            int size = arrays.get(i).size() - 1;
            int ef = Math.max(md, Math.abs(max - arrays.get(i).get(0)));
            int fe = Math.max(md, Math.abs(min - arrays.get(i).get(size)));
            md = Math.max(ef, fe);
            min = Math.min(min, arrays.get(i).get(0));
            max = Math.max(max, arrays.get(i).get(size));
        }
        return md;
    }

    public static void main(String[] args) {
        Exclusive100 exclusive100 = new Exclusive100();
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(Arrays.asList(1, 2, 3));
        arrays.add(Arrays.asList(4, 5));
        arrays.add(Arrays.asList(1, 2, 3));
        int d = exclusive100.maxDistance(arrays);
        System.out.println("d = " + d);
    }
}
