package org.labuladong.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luojx
 * @date 2022/6/23 11:22
 */
public class Main {
    /*
    result = []
    def backtrack(路径, 选择列表):
        if 满足结束条件:
            result.add(路径)
            return

        for 选择 in 选择列表:
            做选择
            backtrack(路径, 选择列表)
            撤销选择
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.nQueens(8);
        System.out.println(main.chess.size());
        for (char[][] chess : main.chess) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    System.out.print(chess[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * 全排列
     */
    List<List<Integer>> list = new LinkedList<>();
    public void permute(int[] nums){
        LinkedList<Integer> per = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, per, used);
    }
    public void backtrack(int[] nums, LinkedList<Integer> per, boolean[] used){
        if(per.size() == nums.length){
            list.add(new LinkedList<>(per));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            used[i] = true;
            per.add(nums[i]);
            backtrack(nums, per, used);
            used[i] = false;
            per.removeLast();
        }
    }

    /**
     * N Queens
     */
    List<char[][]> chess = new ArrayList<>();
    public void nQueens(int n){
        //初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        nQueensBacktracking(n, 0, board);
    }

    private void nQueensBacktracking(int n, int row, char[][] board){
        if(row == n){
            chess.add(cpChar(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            //check valid
            if(!valid(board, row ,col)){
                continue;
            }
            board[row][col] = 'Q';
            nQueensBacktracking(n, row + 1, board);
            board[row][col] = '.';
        }
    }

    private boolean valid(char[][] board, int row, int col){
        //check row
        for (int i = 0; i < board.length; i++) {
            if(board[row][i] == 'Q'){
                return false;
            }
        }
        //check col
        for (int i = 0; i < board.length; i++) {
            if(board[i][col] == 'Q'){
                return false;
            }
        }
        //check left top
        for (int i = row, j = col; i >=0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        //check right top
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    private char[][] cpChar(char[][] chars){
        char[][] deep = new char[chars.length][chars[0].length];
        for(int i = 0;i < deep.length;i++){
            for(int j = 0;j < deep[i].length;j++){
                deep[i][j] = chars[i][j];
            }
        }
        return deep;
    }
}
