package com.isaac;

import java.util.*;

public class SolutionEasy {

    //507. Perfect Number
    public boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) {
                    sum += (num / i);
                }
            }
        }
        return sum == num;
    }

    //728. Self Dividing Numbers
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int x = left; x <= right; x++) {
            if (checkDividing(x, getAllNumbers(x))) {
                result.add(x);
            }
        }
        return result;
    }

    private List<Integer> getAllNumbers(int x) {
        List<Integer> result = new ArrayList<>();
        while (x > 0) {
            int num = x % 10;
            if (num == 0) {
                result.clear();
                return result;
            }
            result.add(num);
            x = x / 10;
        }
        return result;
    }

    private boolean checkDividing(int num, List<Integer> digits) {
        if (digits.size() == 0) {
            return false;
        }
        for (int x : digits) {
            if (num % x != 0) {
                return false;
            }
        }
        return true;
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
    }
}
