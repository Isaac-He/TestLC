package com.isaac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SolutionEasy {

    // 645. Set Mismatch
    public int[] findErrorNums(int[] nums) {
        int[] flag = new int[nums.length + 1];
        Arrays.fill(flag, 0);
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            flag[nums[i]]++;
        }
        for (int i = 1; i < flag.length; i++) {
            if (flag[i] > 1) {
                result[0] = i;
            }
            if (flag[i] < 1) {
                result[1] = i;
            }
        }
        return result;
    }

    // 404. Sum of Left Leaves
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                }
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return sum;
    }

    // 235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if ((root.val - p.val) * (root.val - q.val) < 0) {
            return root;
        } else if ((root.val - p.val) * (root.val - q.val) == 0) {
            return root;
        } else if ((root.val - p.val) * (root.val - q.val) > 0) {
            if (root.val - p.val > 0) {
                return lowestCommonAncestor(root.left, p, q);
            } else {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
        return null;
    }

    // 617. Merge Two Binary Trees
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return t1;
        }
        if (t1 == null) {
            return t2;
        }
        TreeNode newNode = new TreeNode(t1.val + t2.val);
        newNode.left = mergeTrees(t1.left, t2.left);
        newNode.right = mergeTrees(t1.right, t2.right);
        return newNode;
    }

    // 657. Judge Route Circle
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.isEmpty()) {
            return true;
        }
        int x = 0;
        int y = 0;
        for (char m : moves.toCharArray()) {
            switch (m) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
                default:
                    break;
            }
        }
        return x == 0 && y == 0;
    }

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
