package org.labuladong.binarytree;

import org.labuladong.binarytree.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author luojx
 * @date 2022/6/21 10:55
 */
public class Main {
    class Solution {
        /**
        * 二叉树最大深度
        *
        */
        public int maxDepth(TreeNode root) {
            if(root == null)
                return 0;
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            return 1 + Math.max(leftMax, rightMax);
        }

        private List<Integer> list = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            if(root == null){
                return list;
            }
            list.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
            return list;
        }

        /**
         *最大直径
        */
        int res = Integer.MIN_VALUE;
        public int diameterOfBinaryTree(TreeNode root) {
            maxDepthDiameter(root);
            return res;
        }

        public int maxDepthDiameter(TreeNode root){
            if (root == null){
                return 0;
            }
            int leftMax = maxDepthDiameter(root.left);
            int rightMax = maxDepthDiameter(root.right);
            res = Math.max(res, (leftMax + rightMax));
            return 1 + Math.max(leftMax , rightMax);
        }
    }

    /**
     * 最大路径和
     */
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        suffixTraverse(root);
        return res;
    }

    private int suffixTraverse(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftMax = suffixTraverse(root.left);
        int rightMax = suffixTraverse(root.right);
        res = Math.max(res, Math.max(root.val, Math.max(root.val + leftMax + rightMax, Math.max((root.val + leftMax), (root.val + rightMax)))));
        return Math.max(root.val,  Math.max((root.val + leftMax), (root.val + rightMax)));
    }

    /**
     *层序遍历
     */
    public void levelTraverse(TreeNode root){
        if (root == null)
            return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                System.out.println(poll.val);
                if(poll.left != null){
                    queue.offer(poll.left);
                }
                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }
        }
    }

    List<List<Integer>> list = new ArrayList<>();
    public void levelTraverseRecursion(TreeNode root){
        traverse(root, 0);
    }

    private void traverse(TreeNode root, int level){
        if(root == null){
            return;
        }
        if(list.size() <= level){
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }
}
