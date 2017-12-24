package com.isaac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SolutionNormal {

    // 287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        //easy approach.
        /*Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;*/

        //fast approach.
        int first = nums[0];
        int second = nums[0];
        do {
            first = nums[first];
            second = nums[nums[second]];
        } while (first != second);

        second = nums[0];
        while (first != second) {
            first = nums[first];
            second = nums[second];
        }
        return first;
    }

    // 654. Maximum Binary Tree
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int index = getMaxIndex(nums);
        TreeNode root = new TreeNode(nums[index]);
        int[] left = Arrays.copyOf(nums, index);
        int[] right = Arrays.copyOfRange(nums, index + 1, nums.length);
        root.left = constructMaximumBinaryTree(left);
        root.right = constructMaximumBinaryTree(right);
        return root;
    }

    private int getMaxIndex(int[] nums) {
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    // 513. Find Bottom Left Tree Value
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        stack.push(root);
        while (!stack.empty()) {
            list.clear();
            while (!stack.empty()) {
                list.add(stack.pop());
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                TreeNode node = list.get(i);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return list.get(0).val;
    }

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
