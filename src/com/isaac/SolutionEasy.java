package com.isaac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SolutionEasy {

    // 219. Contains Duplicate II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> distinct = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (distinct.containsKey(nums[i])) {
                if (i - distinct.get(nums[i]) <= k) {
                    return true;
                } else {
                    distinct.put(nums[i], i);
                }
            } else {
                distinct.put(nums[i], i);
            }
        }
        return false;
    }

    // 217. Contains Duplicate
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        Set<Integer> distinct = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (distinct.contains(nums[i])) {
                return true;
            } else {
                distinct.add(nums[i]);
            }
        }
        return false;
    }

    // 747. Min Cost Climbing Stairs
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) {
            return 0;
        }
        int[] minCost = new int[cost.length];
        minCost[0] = cost[0];
        minCost[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            minCost[i] = Math.min(minCost[i - 1], minCost[i - 2]) + cost[i];
        }
        return Math.min(minCost[minCost.length - 1], minCost[minCost.length - 2]);
    }

    // 70. Climbing Stairs
    public int climbStairs(int n) {
        if (n < 0) {
            return 0;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    // 69. Sqrt(x)
    public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        int i = 0;
        int j = x;
        int k = Math.max(i + (j - i ) / 2, 1);
        while (true) {
            if (k <= x / k && (k + 1) > x / (k + 1)) {
                return k;
            } else if (k > x / k) {
                j = k;
                k = Math.max(i + (j - i ) / 2, 1);
            } else {
                i = k;
                k = Math.max(i + (j - i ) / 2, 1);
            }
        }
    }

    // 67. Add Binary
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        char[] temp = new char[Math.max(ca.length, cb.length)];
        int i = ca.length - 1;
        int j = cb.length - 1;
        int k = temp.length - 1;
        int flag = 0;
        while (i >= 0 || j >= 0) {
            int bit;
            if (i >= 0 && j >= 0) {
                bit = ca[i] - '0' + cb[j] - '0' + flag;
            } else if (i < 0) {
                bit = cb[j] - '0' + flag;
            } else {
                bit = ca[i] - '0' + flag;
            }
            if (bit > 1) {
                flag = 1;
            } else {
                flag = 0;
            }
            switch (bit) {
                case 0:
                case 2:
                    temp[k] = '0';
                    break;
                case 1:
                case 3:
                    temp[k] = '1';
                    break;
                default:
                    break;
            }
            i--;
            j--;
            k--;
        }
        if (flag == 1) {
            char[] result = new char[temp.length + 1];
            System.arraycopy(temp, 0, result, 1, temp.length);
            result[0] = '1';
            return String.valueOf(result);
        }
        return String.valueOf(temp);
    }

    // 606. Construct String from Binary Tree
    public String tree2str(TreeNode t) {
        String result = "";
        if (t == null) {
            return result;
        }
        result = t.val + "";//String.valueOf(t.val);
        if (t.left != null) {
            if (t.right != null) {
                result = result + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
                //result = String.format(result + "(%s)(%s)", tree2str(t.left), tree2str(t.right));
            } else {
                result = result + "(" + tree2str(t.left) + ")";
                //result = String.format(result + "(%s)", tree2str(t.left));
            }
        } else {
            if (t.right != null) {
                result = result + "()(" + tree2str(t.right) + ")";
                //result = String.format(result + "()(%s)", tree2str(t.right));
            }
        }
        return result;
    }

    // 66. Plus One
    public int[] plusOne(int[] digits) {
        boolean flag = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] >= 10) {
                flag = true;
                digits[i] -= 10;
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        }
        return digits;
    }

    // 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sChar = s.toCharArray();
        int len = 0;
        boolean start = false;
        for (int i = sChar.length - 1; i >= 0; i--) {
            if (!start) {
                if (sChar[i] != ' ') {
                    start = true;
                }
            }
            if (start) {
                if (sChar[i] != ' ') {
                    len++;
                } else {
                    break;
                }
            }
        }
        return len;
    }

    // 453. Minimum Moves to Equal Array Elements
    public int minMoves(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int moves = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min) {
                moves += nums[i] - min;
            }
        }
        return moves;
    }

    // 122. Best Time to Buy and Sell Stock II
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int max = 0;
        int current = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < current) {
                current = prices[i];
            } else if (prices[i] > current) {
                max += prices[i] - current;
                current = prices[i];
            }
        }
        return max;
    }

    // 121. Best Time to Buy and Sell Stock
    public int maxProfit1(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    // 53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] + sum < 0) {
                sum = 0;
                max = Math.max(max, nums[i]);
            } else {
                sum += nums[i];
                max = Math.max(sum, max);
            }
            i++;
        }
        return max;
    }

    // 717. 1-bit and 2-bit Characters
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            if (bits[i] == 1) {
                i += 2;
            } else {
                i++;
            }
        }
        if (i == bits.length) {
            return false;
        }
        if (bits[i] == 1) {
            return false;
        } else {
            return true;
        }
    }

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
