package org.labuladong.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luojx
 * @date 2022/6/23 16:20
 */
public class Sudoku_example {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                new char[]{'3','0','0','0','6','0','2','7','0'},
                new char[]{'0','0','0','0','0','0','0','1','0'},
                new char[]{'8','0','4','0','5','0','0','0','0'},
                new char[]{'1','6','0','0','0','7','0','0','9'},
                new char[]{'7','0','5','0','8','0','0','6','2'},
                new char[]{'4','9','8','0','0','1','3','5','0'},
                new char[]{'2','8','6','0','7','5','0','0','1'},
                new char[]{'0','4','0','2','9','0','0','3','8'},
                new char[]{'9','7','3','0','1','4','5','2','6'}
        };
        Sudoku_example sudoku_example = new Sudoku_example();
        boolean backtrack = sudoku_example.backtrack(board, 0, 0);
        System.out.println("backtrack = " + backtrack);
        for (char[][] re : sudoku_example.res) {
            for (char[] r : re) {
                for (char i : r) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    List<char[][]> res = new ArrayList<>();
    boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return backtrack(board, i + 1, 0);
        }
        if (i == m) {
            // 找到一个可行解，触发 base case
            res.add(cpDeep(board));
            return true;
        }

        if (board[i][j] != '0') {
            // 如果有预设数字，不用我们穷举
            return backtrack(board, i, j + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            // 如果遇到不合法的数字，就跳过
            if (!isValid(board, i, j, ch))
                continue;

            board[i][j] = ch;
            // 如果找到一个可行解，立即结束
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '0';
        }
        // 穷举完 1~9，依然没有找到可行解，此路不通
        return false;
    }

    // 判断 board[i][j] 是否可以填入 n
    boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }

    private char[][] cpDeep(char[][] nums) {
        char[][] deep = new char[nums.length][nums[0].length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                deep[i][j] = nums[i][j];
            }
        }
        return deep;
    }
}
