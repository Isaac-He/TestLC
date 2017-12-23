package com.isaac;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SolutionEasy {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        return result;
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null || root.right == null) {
            return -1;
        }
        int second = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode current = stack.pop();
            if (current.left != null) {
                if (current.val == current.left.val) {
                    stack.push(current.left);
                } else {
                    second = Math.min(second, current.left.val);
                }
                if (current.val == current.right.val) {
                    stack.push(current.right);
                } else {
                    second = Math.min(second, current.right.val);
                }
            }
        }
        if (second < Integer.MAX_VALUE) {
            return second;
        } else {
            return -1;
        }


//        else if (root.left.val == root.right.val) {
//            int left = findSecondMinimumValue(root.left);
//            int right = findSecondMinimumValue(root.right);
//            if (left < 0) {
//                return right;
//            } else {
//                if (left < right) {
//                    return left;
//                } else {
//                    return right;
//                }
//            }
//        } else {
//            if (root.left.val > root.right.val) {
//                int secondVal = root.left.val;
//                int treeSecond = findSecondMinimumValue(root.right);
//                if (treeSecond > 0 && treeSecond < secondVal) {
//                    return treeSecond;
//                } else {
//                    return secondVal;
//                }
//            } else {
//                int secondVal = root.right.val;
//                int treeSecond = findSecondMinimumValue(root.left);
//                if (treeSecond > 0 && treeSecond < secondVal) {
//                    return treeSecond;
//                } else {
//                    return secondVal;
//                }
//            }
//        }
    }
}
