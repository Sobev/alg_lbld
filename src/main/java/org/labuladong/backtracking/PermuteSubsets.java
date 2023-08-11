package org.labuladong.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集 组合 排列
 *
 * @author luojx
 * @date 2022/6/24 13:28
 */
public class PermuteSubsets {

    public static void main(String[] args) {
        PermuteSubsets permuteSubsets = new PermuteSubsets();
        permuteSubsets.subsets(new int[]{1, 2, 3});
        for (List<Integer> sub : permuteSubsets.list) {
            for (Integer n : sub) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    List<List<Integer>> list = new LinkedList<>();

    /**
     * 元素无重复不可复选 排列
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsBacktracking(nums, new LinkedList<>(), 0);
        return list;
    }

    /**
     * @param start 使用 start 参数控制树枝的生长避免产生重复的子集
     */
    private void subsetsBacktracking(int[] nums, LinkedList<Integer> subsets, int start) {
        list.add(new LinkedList<>(subsets));
        for (int i = start; i < nums.length; i++) {
            subsets.addLast(nums[i]);
            subsetsBacktracking(nums, subsets, i + 1);
            subsets.removeLast();
        }
    }

    /**
     * 元素无重复不可复选 组合
     */
    private void combine(int[] nums, LinkedList<Integer> subsets, int start, int k) {
        if (subsets.size() == k) {
            list.add(subsets);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            subsets.addLast(nums[i]);
            combine(nums, subsets, i + 1, k);
            subsets.removeLast();
        }
    }

    /**
     * 排列  见Main包
     */
//    ==============================================================================================
    //元素可重不可复选
    static class ElementRepeatable {
        public static void main(String[] args) {
            ElementRepeatable elementRepeatable = new ElementRepeatable();
            elementRepeatable.repeatableSubsets(new int[]{1, 2, 2});
            for (List<Integer> sub : elementRepeatable.repeatableSubsetsList) {
                for (Integer n : sub) {
                    System.out.print(n + " ");
                }
                System.out.println();
            }
        }

        /**
         * 子集（元素可重不可复选）
         */
        List<List<Integer>> repeatableSubsetsList = new LinkedList<>();

        public List<List<Integer>> repeatableSubsets(int[] nums) {
            Arrays.sort(nums);
            repeatableBacktracking(nums, 0, new LinkedList<>());
            return repeatableSubsetsList;
        }

        //1,2,2
        private void repeatableBacktracking(int[] nums, int start, LinkedList<Integer> subsets) {
            repeatableSubsetsList.add(new LinkedList<>(subsets));
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i - 1] == nums[i]) {
                    continue;
                }
                subsets.addLast(nums[i]);
                repeatableBacktracking(nums, i + 1, subsets);
                subsets.removeLast();
            }
        }

        /**
         * 组合 给你输入 candidates 和一个目标和 target，从 candidates 中找出中所有和为 target 的组合
         */
        int trackSum = 0;
        List<List<Integer>> repeatableCombineList = new LinkedList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            combinationSum2Backtracking(candidates, target, 0, new LinkedList<>());
            return repeatableCombineList;
        }

        private void combinationSum2Backtracking(int[] candidates, int target, int start, LinkedList<Integer> subsets) {
            if (trackSum == target) {
                repeatableCombineList.add(subsets);
                return;
            }
            // larger than target return directly
            if (trackSum > target) {
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                if (i > 0 && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                trackSum += candidates[i];
                subsets.add(candidates[i]);

                combinationSum2Backtracking(candidates, target, i + 1, subsets);
                trackSum -= candidates[i];
                subsets.removeLast();
            }
        }

        private void repeatablePermutationBacktracking(int[] nums, LinkedList<Integer> pm, boolean[] used) {
            if (pm.size() == nums.length) {
                //add
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                pm.addLast(nums[i]);
                repeatablePermutationBacktracking(nums, pm, used);
                used[i] = false;
                pm.removeLast();
            }
        }

//        =======================================================================================

        List<List<Integer>> combinationSumList = new ArrayList<>();

        /**
         * 元素无重可复选
         */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            combinationSumBacktracking(candidates, target, 0, new LinkedList<>());
            return combinationSumList;
        }

        int combineSum = 0;

        private void combinationSumBacktracking(int[] candidates, int target, int start, LinkedList<Integer> cbs) {
            if (target == combineSum) {
                combinationSumList.add(cbs);
                return;
            }
            if (combineSum > target) {
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                combineSum += candidates[i];
                cbs.add(candidates[i]);
                combinationSumBacktracking(candidates, target, i, cbs);
                combineSum -= candidates[i];
                cbs.removeLast();
            }
        }


    }
}
