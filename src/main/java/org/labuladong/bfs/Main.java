package org.labuladong.bfs;

import org.labuladong.binarytree.common.TreeNode;

import java.util.*;

/**
 * @author luojx
 * @date 2022/6/24 8:55
 */
public class Main {

    /**
     * 最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public int openLock(String[] deadends, String target) {
        String start = "0000";
        if (target.equals(start)) {
            return 0;
        }
        Queue<String> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        int time = 0;
        queue.offer("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (set.contains(poll)) {
                    continue;
                }
                if (poll.equals(target)) {
                    return time;
                }
                for (int j = 0; j < 4; j++) {
                    String plus = plusOne(poll, j);
                    String minus = minusOne(poll, j);
                    if (!set.contains(plus) && !visited.contains(plus)) {
                        queue.offer(plus);
                        visited.add(plus);
                    }
                    if (!set.contains(minus) && !visited.contains(minus)) {
                        queue.offer(minus);
                        visited.add(minus);
                    }
                }
            }
            time++;
        }
        return -1;
    }

    private String plusOne(String s, int j) {
        char[] array = s.toCharArray();
        if (array[j] == '9') {
            array[j] = '0';
        } else {
            array[j] += 1;
        }
        return new String(array);
    }

    private String minusOne(String s, int j) {
        char[] array = s.toCharArray();
        if (array[j] == '0') {
            array[j] = '9';
        } else {
            array[j] -= 1;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        Main main = new Main();
        int[][] board = new int[][]{
          new int[]{4,1,2},
          new int[]{5,0,3}
        };
        int times = main.slidingPuzzle(board);
        System.out.println("times = " + times);
    }

    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        List<List<Integer>> neighbors = getNeighbors(row, col);
        //generate string puzzle
        StringBuilder builder = new StringBuilder();
        for (int[] r : board) {
            for (int c : r) {
                builder.append(c);
            }
        }
        String puzzleRes = getPuzzleRes(row, col);
        String puzzle = builder.toString();
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(puzzle);
        visited.add(puzzle);
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String puz = queue.poll();
                if (puz.equals(puzzleRes)) {
                    return time;
                }
                //find '0' idx
                int idx = 0;
                for (; puz.charAt(idx) != '0'; idx++) ;
                List<Integer> moveAbleNeighbors = neighbors.get(idx);
                for (Integer neighborIdx : moveAbleNeighbors) {
                    char[] array = puz.toCharArray();
                    array[idx] = array[neighborIdx];
                    array[neighborIdx] = '0';
                    String p = new String(array);
                    if (!visited.contains(p)) {
                        queue.offer(p);
                        visited.add(p);
                    }
                }
            }
            time++;
        }
        return -1;
    }

    /*
        从m行n列获取各个下标的邻居
     */
    private List<List<Integer>> getNeighbors(int m, int n) {
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> list = new ArrayList<>();
                //left
                if ((j - 1) >= 0) {
                    list.add(i * n + j - 1);
                }
                //right
                if ((j + 1) < n) {
                    list.add(i * n + j + 1);
                }
                //up
                if ((i - 1) >= 0) {
                    list.add(i * n + j - n);
                }
                //down
                if ((i + 1) < m) {
                    list.add(i * n + j + n);
                }
                neighbors.add(list);
            }
        }
        return neighbors;
    }
    /**
     * 获取m行n列的解
     */
    private String getPuzzleRes(int m, int n){
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < m*n; i++) {
            builder.append(i);
        }
        builder.append(0);
        return builder.toString();
    }

}
