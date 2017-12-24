package com.isaac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionNormal {

    // 236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    //322. Coin Change
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0){
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] result = new int[amount + 1];
        Arrays.fill(result, Integer.MAX_VALUE - 1);
        result[0] = 0;
        for (int i = 1; i < result.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    result[i] = Math.min(result[i], result[i - coins[j]] + 1);
                }
            }
        }
        return result[amount] > amount ? -1 : result[amount];
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        result.add(temp);
        if (nums.length == 0) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newList = new ArrayList<>();
            for (List<Integer> exist : result) {
                List<Integer> clone = new ArrayList<>();
                clone.addAll(exist);
                clone.add(nums[i]);
                newList.add(clone);
            }
            result.addAll(newList);
        }
        return result;
    }
}
