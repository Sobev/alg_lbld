package org.labuladong.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luojx
 * @date 2022/6/23 14:53
 */
public class Sudoku {

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{3,0,0,0,6,0,2,7,0},
                new int[]{0,0,0,0,0,0,0,1,0},
                new int[]{8,0,4,0,5,0,0,0,0},
                new int[]{1,6,0,0,0,7,0,0,9},
                new int[]{7,0,5,0,8,0,0,6,2},
                new int[]{4,9,8,0,0,1,3,5,0},
                new int[]{2,8,6,0,7,5,0,0,1},
                new int[]{0,4,0,2,9,0,0,3,8},
                new int[]{9,7,3,0,1,4,5,2,6}
        };
        Sudoku sudoku = new Sudoku();
        sudoku.sudoku(nums);
        for (int[][] re : sudoku.res) {
            for (int[] r : re) {
                for (int i : r) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    List<int[][]> res = new ArrayList<>();

    public void sudoku(int[][] nums) {
        sudokuBacktracking(nums, 0, 0);
    }

    public void sudokuBacktracking(int[][] nums, int row, int col) {
        if(col == nums.length){
            sudokuBacktracking(nums, row + 1, 0);
            return;
        }
        if(row == nums.length){
            //add
            res.add(cpDeep(nums));
            System.out.println("Add");
            return;
        }
        if(nums[row][col] != 0){
            sudokuBacktracking(nums, row, col + 1);
            return;
        }
        for (int n = 1; n <= 9; n++) {
            if(!valid(nums, n ,row, col)){
                continue;
            }
            nums[row][col] = n;
            sudokuBacktracking(nums, row, col + 1);
            nums[row][col] = 0;
        }
    }

    private boolean valid(int[][] nums, int n, int row, int col) {
        //check the 9 block
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (nums[i][j] == n) {
                    return false;
                }
            }
        }
        //check row
        for (int i = 0; i < nums.length; i++) {
            if (nums[row][i] == n) {
                return false;
            }
        }
        //check col
        for (int i = 0; i < nums.length; i++) {
            if (nums[i][col] == n) {
                return false;
            }
        }
        return true;
    }

    private int[][] cpDeep(int[][] nums) {
        int[][] deep = new int[nums.length][nums[0].length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                deep[i][j] = nums[i][j];
            }
        }
        return deep;
    }
}
