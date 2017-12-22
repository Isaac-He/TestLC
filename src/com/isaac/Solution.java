package com.isaac;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by JHe on 12/23/2016.
 */
public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        return root;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> sumSet = new HashSet<>();
        int x = n;
        while (true) {
            x = getSquarSum(x);
            if (x == 1) {
                return true;
            } else {
                if (sumSet.contains(x)) {
                    return false;
                } else {
                    sumSet.add(x);
                }
            }
        }
    }

    private int getSquarSum(int n) {
        int sum = 0;
        while (n > 0) {
            int s = n % 10;
            sum += s * s;
            n = n / 10;
        }
        return sum;
    }

    public int addDigits(int num) {
        if(num == 0) {
            return 0;
        }
        return num - 9 * ((num - 1) / 9);
    }

    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (word.toUpperCase().equals(word) || word.toLowerCase().equals(word)) {
            return true;
        } else {
            if (word.toUpperCase().substring(0, 1).equals(word.substring(0, 1))
                    && word.toLowerCase().substring(1, word.length()).equals(word.substring(1, word.length()))) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if(findNums.length == 0) {
            return new int[0];
        }
        int[] result = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            int j = 0;
            while(nums[j] != findNums[i]) {
                j++;
            }
            j++;
            while(j < nums.length && nums[j] < findNums[i]) {
                j++;
            }
            if (j < nums.length) {
                result[i] = nums[j];
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public int missingNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        boolean flip0 = false;
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) < nums.length) {
                nums[Math.abs(nums[i])] *= -1;
                if (nums[Math.abs(nums[i])] == 0) {
                    flip0 = true;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i;
            }
            if (nums[i] == 0 && !flip0) {
                return i;
            }
        }
        return nums.length;
    }

    public int findComplement(int num) {
        if (num == 0) {
            return 1;
        }
        int mask = 0;
        int numCopy = num;
        while (num > 0) {
            num = num >> 1;
            mask = mask << 1;
            mask = mask | 1;
        }
        return numCopy ^ mask;
    }





    public Set<Movie> getMovieRecommendations (Movie movie, int N)
    {
        // WRITE YOUR CODE HERE
        if (N <= 0) {
            return null;
        }
        if (movie == null) {
            return null;
        }
        int currentId = movie.getId();
        Set<Movie> allRelatedMovies = new HashSet<>();
        Stack<Movie> stackMovie = new Stack<>();
        stackMovie.push(movie);
        while (!stackMovie.isEmpty()) {
            Movie tmpMovie = stackMovie.pop();
            if (!allRelatedMovies.contains(tmpMovie)) {
                allRelatedMovies.add(tmpMovie);
            }
            for (Movie similar : tmpMovie.getSimilarMovies()) {
                if (!allRelatedMovies.contains(similar)) {
                    stackMovie.push(similar);
                }
            }
        }
        List<Movie> allMovies = new ArrayList<>(allRelatedMovies);
        Set<Movie> result = new HashSet<>();
        Collections.sort(allMovies, MovieComparator);
        for (int i = allMovies.size() - 1; i >= 0; i--) {
            if (allMovies.get(i).getId() != currentId) {
                result.add(allMovies.get(i));
            }
            if (result.size() >= N) {
                break;
            }
        }
        return result;

    }

    public static Comparator<Movie> MovieComparator = new Comparator<Movie>() {
        public int compare(Movie movie1, Movie movie2) {
            return (int)(movie1.getRating() - movie2.getRating());
        }
    };

    public int checkWinner(List<List<String>> codeList,
                           List<String> shoppingCart)
    {
        // WRITE YOUR CODE HERE
        if (codeList.size() <= 0) {
            return 0;
        }
        if (shoppingCart.size() <= 0) {
            return 0;
        }
        int currentShoppingPos = 0;
        int matchPos = 0;
        for (int i = 0; i < codeList.size(); i++) {
            List<String> subList = codeList.get(i);
            if (subList.size() <= 0) {
                continue;
            }
            int j = 0;
            matchPos = currentShoppingPos;
            while (j < subList.size() && currentShoppingPos < shoppingCart.size()) {
                if (subList.get(j).equals("anything")) {
                    currentShoppingPos++;
                    j++;
                } else {
                    if (subList.get(j).equals(shoppingCart.get(currentShoppingPos))) {
                        currentShoppingPos++;
                        j++;
                    } else {
                        j = 0;
                        currentShoppingPos = matchPos + 1;
                        matchPos = currentShoppingPos;
                    }
                }
            }
            if (j == subList.size()) {
                matchPos = currentShoppingPos;
                //currentShoppingPos;
            } else {
                return 0;
            }

        }
        return 1;
    }

    public int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[4096];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }


    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;
        while (l <= h) {
            int mid = (h - l) / 2;
            if (isBadVersion(h - mid)) {
                h -= mid;
            } else {
                l += mid;
            }
            if (mid == 0){
                if (isBadVersion(l)) {
                    return l;
                } else {
                    return h;
                }
            }
        }
        if (isBadVersion(l)) {
            return l;
        } else {
            return h;
        }
    }

    private boolean isBadVersion(int version) {
        return true;
    }

    public int searchInsert(int[] nums, int target) {
        if (nums.length < 1) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else {
                if (nums[i] < target) {
                    continue;
                } else {
                    return i;
                }
            }
        }
        return nums.length;
    }

    public String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        String[] results = new String[n];
        results[0] = "1";
        for (int i = 1; i < n; i++) {
            results[i] = say(results[i - 1]);
        }
        return results[n - 1];
    }

    private String say(String n) {
        char[] nArray = n.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char current = nArray[0];
        for (int i = 0; i < nArray.length - 1; i++) {
            if (nArray[i + 1] == nArray[i]) {
                count++;
                continue;
            } else {
                sb.append(count);
                sb.append(current);
                current = nArray[i + 1];
                count = 1;
            }
        }
        sb.append(count);
        sb.append(current);
        return sb.toString();
    }

    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        boolean find = false;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                int mark = i;
                for (int j = nums.length - 1; j >= i; j--) {
                    if (nums[i - 1] < nums[j]) {
                        mark = j;
                        break;
                    }
                }
                int temp = nums[i - 1];
                nums[i - 1] = nums[mark];
                nums[mark] = temp;
                int j = i;
                int k = nums.length - 1;
                while (j < k) {
                    temp = nums[j];
                    nums[j] = nums[k];
                    nums[k] = temp;
                    j++;
                    k--;
                }
                find = true;
                break;
            }
        }
        if (!find) {
            Arrays.sort(nums);
        }
    }

    public int search(int[] nums, int target) {
        if (nums.length < 1) {
            return -1;
        }
        int len = nums.length;
        int startIdx = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                startIdx = i + 1;
                break;
            }
        }
        int endIdx = (startIdx - 1 + len) % len;
        int low = startIdx;
        int high = endIdx;
        while (low != high) {
            int mid = ((high - low + len) % len) / 2;
            if (nums[(mid + low) % len] == target) {
                return (mid + low) % len;
            } else if (nums[(mid + low) % len] < target) {
                low = (mid + low) % len;
            } else {
                high = (high - mid + len) % len;
            }
            if (mid == 0) {
                if (nums[high] == target) {
                    return high;
                } else {
                    break;
                }
            }
        }
        if (nums[low] == target) {
            return low;
        } else {
            return -1;
        }
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        if (n < d) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return -dividend;
            }
        }
        int ans = 0;
        while (n >= d) {
            long x = d;
            long y = 1;
            while (x << 1 < n) {
                x = x << 1;
                y = y << 1;
            }
            n -= x;
            ans += y;
        }
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            return -ans;
        }
        return ans;
    }

    public int removeElement(int[] nums, int val) {
        int result = nums.length;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                result--;
            } else {
                nums[j] = nums[i];
                j++;
            }
        }
        return result;
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        if (n <= 0) {
            return result;
        }
        helper(result, stack, n, n, n);
        return result;
    }

    private void helper(List<String> result, Stack<Character> stack, int left, int right, int n) {
        if (left > 0) {
            stack.push('(');
            left--;
            helper(result, stack, left, right, n);
            left++;
            stack.pop();
        }
        if (right > 0 && right > left) {
            stack.push(')');
            right--;
            helper(result, stack, left, right, n);
            right++;
            stack.pop();
        }
        if (stack.size() == n * 2) {
            StringBuilder sb = new StringBuilder();
            for (Character c : stack) {
                sb.append(c);
            }
            result.add(sb.toString());
        }
    }

    public String intToRoman(int num) {
        if (num < 1) {
            return "";
        }
        String result = "";
        StringBuilder sb = new StringBuilder();
        int nT = num / 1000;
        for (int i = 0; i < nT; i++) {
            sb.append("M");
        }
        num = num % 1000;
        int nH = num / 100;
        if (nH == 9) {
            sb.append("CM");
        } else if (nH == 4) {
            sb.append("CD");
        } else {
            if (nH >= 5) {
                sb.append("D");
                nH -= 5;
            }
            for (int i = 0; i < nH; i++) {
                sb.append("C");
            }

        }
        num = num % 100;
        int n2 = num / 10;
        if (n2 == 9) {
            sb.append("XC");
        } else if (n2 == 4) {
            sb.append("XL");
        } else {
            if (n2 >= 5) {
                sb.append("L");
                n2 -= 5;
            }
            for (int i = 0; i < n2; i++) {
                sb.append("X");
            }
        }
        num = num % 10;
        int n = num;
        if (n == 9) {
            sb.append("IX");
        } else if (n == 4) {
            sb.append("IV");
        } else {
            if (n >= 5) {
                sb.append("V");
                n -= 5;
            }
            for (int i = 0; i < n; i++) {
                sb.append("I");
            }
        }
        result = sb.toString();
        return result;
    }

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        List<Map.Entry<Integer, List<TreeLinkNode>>> levelList = new ArrayList<>();
        levelList.add(new HashMap.SimpleEntry<>(0, Arrays.asList(root)));
        int level = 0;
        while (level < levelList.size()) {
            List<TreeLinkNode> nodes = levelList.get(level).getValue();
            level++;
            List<TreeLinkNode> nextLevel = new ArrayList<>();
            for (TreeLinkNode node : nodes) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            if (nextLevel.size() > 0) {
                levelList.add(new HashMap.SimpleEntry<>(level, nextLevel));
            }
        }
        for (int i = 0; i < levelList.size(); i++) {
            List<TreeLinkNode> nodes = levelList.get(i).getValue();
            for (int j = 0; j < nodes.size(); j++) {
                if (j + 1 < nodes.size()) {
                    nodes.get(j).next = nodes.get(j + 1);
                } else {
                    nodes.get(j).next = null;
                }
            }
        }

    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<Integer, RandomListNode> copyMap = new HashMap<>();
        RandomListNode x = head;
        RandomListNode newHead = new RandomListNode(x.label);
        RandomListNode y = newHead;
        copyMap.put(x.hashCode(), y);
        while (x.next != null) {
            y.next = new RandomListNode(x.next.label);
            y = y.next;
            x = x.next;
            copyMap.put(x.hashCode(), y);
        }
        x = head;
        y = newHead;
        while(x != null) {
            if (x.random != null) {
                y.random = copyMap.get(x.random.hashCode());
            }
            x = x.next;
            y = y.next;
        }
        return newHead;
    }

    public void solve(char[][] board) {
        int maxX = board.length;
        if (maxX <= 2) {
            return;
        }
        int maxY = board[0].length;
        if (maxY <= 2) {
            return;
        }
        for (int i = 0; i < maxX; i++) {
            if (board[i][0] == 'O') {
                markConnected(board, i, 0);
            }
            if (board[i][maxY - 1] == 'O') {
                markConnected(board, i, maxY - 1);
            }
        }
        for (int j = 0; j < maxY; j++) {
            if (board[0][j] == 'O') {
                markConnected(board, 0, j);
            }
            if (board[maxX - 1][j] == 'O') {
                markConnected(board, maxX - 1, j);
            }
        }
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void markConnected(char[][] board, int i, int j) {
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        stack.push(new HashMap.SimpleEntry<>(i, j));
        while(!stack.isEmpty()) {
            Map.Entry<Integer, Integer> point = stack.pop();
            board[point.getKey()][point.getValue()] = '*';
            updateStack(board, point.getKey(), point.getValue(), stack);
        }
    }
//
//    private boolean findConnected(char[][] board, int i, int j, HashSet<String> hs) {
//        boolean surrounded = true;
//        if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) {
//            surrounded = false;
//        }
//        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
//        stack.push(new HashMap.SimpleEntry<>(i, j));
//        while(!stack.isEmpty()) {
//            Map.Entry<Integer, Integer> point = stack.pop();
//            if (surrounded) {
//                if (point.getKey() == 0 || point.getKey() == board.length - 1 || point.getValue() == 0 || point.getValue() == board[i].length - 1) {
//                    surrounded = false;
//                }
//            }
//            updateStack(board, point.getKey(), point.getValue(), hs, stack);
//        }
//        return surrounded;
//    }

    private void updateStack(char[][] board, int i, int j, Stack<Map.Entry<Integer, Integer>> stack) {
        if (i - 1 >= 0 && board[i - 1][j] == 'O') {
            stack.push(new HashMap.SimpleEntry<>(i - 1, j));
        }
        if (i + 1 < board.length && board[i + 1][j] == 'O') {
            stack.push(new HashMap.SimpleEntry<>(i + 1, j));
        }
        if (j - 1 >= 0 && board[i][j - 1] == 'O') {
            stack.push(new HashMap.SimpleEntry<>(i, j - 1));
        }
        if (j + 1 < board[i].length && board[i][j + 1] == 'O') {
            stack.push(new HashMap.SimpleEntry<>(i, j + 1));
        }
    }
//
//    private String getCordStr(int x, int y) {
//        return String.format("%d|%d", x, y);
//    }
//
//    private int[] getCordInt(String cord) {
//        int[] result = new int[2];
//        String[] cordStr = cord.split("\\|");
//        result[0] = Integer.parseInt(cordStr[0]);
//        result[1] = Integer.parseInt(cordStr[1]);
//        return result;
//    }

    public int[] constructRectangle(int area) {
        int[] result = new int[2];
        if (area <= 0) {
            return result;
        }
        int base = (int)Math.sqrt(area);
        while(area % base != 0) {
            base--;
        }
        result[0] = area / base;
        result[1] = base;
        return result;
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        for (int i = 0; i < n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }


    public boolean isValidBST(TreeNode root) {
        boolean result = true;
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        int max;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode x = root;
        while (x != null) {
            stack.push(x);
            x = x.left;
        }
        x = stack.pop();
        max = x.val;
        while(!stack.isEmpty() || x.right != null)
        {
            if (x.right == null) {
                x = stack.pop();
                if (max >= x.val) {
                    return false;
                } else {
                    max = x.val;
                }
            } else {
                x = x.right;
                while (x != null) {
                    stack.push(x);
                    x = x.left;
                }
                x = stack.pop();
                if (max >= x.val) {
                    return false;
                } else {
                    max = x.val;
                }
            }
        }
        return result;
    }

    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            if (left || isOdd(remaining)) {
                head += step;
            }
            remaining = remaining / 2;
            step *= 2;
            left = !left;
        }
        return head;
    }

    private boolean isOdd(int x) {
        return (x & 1) == 1;
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        int power3_19 = 1162261467;
        return power3_19 % n == 0;
    }

    public int longestPalindrome2(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }
        HashMap<Character, Integer> sMap = new HashMap<>();
        char[] sChars = s.toCharArray();
        for (char c : sChars) {
            if (sMap.containsKey(c)) {
                sMap.put(c, sMap.get(c) + 1);
            } else {
                sMap.put(c, 1);
            }
        }
        boolean singleFlag = false;
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {
            if ((entry.getValue() & 1) == 0) {
                result += entry.getValue();
            } else {
                if (!singleFlag) {
                    result += entry.getValue();
                    singleFlag = true;
                } else {
                    result += entry.getValue() - 1;
                }
            }
        }
        return result;
    }

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = nextStatus(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int nextStatus(int[][] board, int i, int j) {
        int count = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y ==0) {
                    continue;
                }
                if (i + x >= 0 && i + x < board.length) {
                    if (j + y >= 0 && j + y < board[i + x].length) {
                        if (Math.abs(board[i + x][j + y]) == 1) {
                            count++;
                        }
                    }
                }
            }
        }
        if (count < 2 || count > 3) {
            if (board[i][j] == 1) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (count == 3 && board[i][j] == 0) {
                return 2;
            } else {
                return board[i][j];
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        HashSet<Integer> zeroRow = new HashSet<>();
        HashSet<Integer> zeroCol = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow.add(i);
                    zeroCol.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (zeroRow.contains(i) || zeroCol.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length <= 0 || nums2.length <= 0) {
            return new int[0];
        }
        HashSet<Integer> num1 = new HashSet<>();
        for (int x : nums1) {
            num1.add(x);
        }
        List<Integer> result = new ArrayList<>();
        HashSet<Integer> num2 = new HashSet<>();
        for (int x : nums2) {
            if (!num2.contains(x)) {
                num2.add(x);
                if (num1.contains(x)) {
                    result.add(x);
                }
            }
        }
        return result.stream().mapToInt(x->x).toArray();
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length <= 0 || nums2.length <= 0) {
            return new int[0];
        }
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while(i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return result.stream().mapToInt(x->x).toArray();
    }

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++) {
            int d = k;
            int j = i;
            while (d >= 0) {
                j++;
                if (j < c.length) {
                    if (c[i] == c[j]) {
                    } else {
                        d--;
                    }
                } else {
                    break;
                }
            }
            if (d < 0) {
                max = Math.max(max, j - i);
            } else {
                if (d <= i) {
                    max = Math.max(max, j - i + d);
                } else {
                    max = Math.max(max, j);
                }
            }
        }
        return max;
    }

    //// TODO: 2/24/2017
    public String removeDuplicateLetters(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        List<Character> resultList = new ArrayList<>();
        HashMap<Character, Integer> index = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!index.containsKey(c)) {
                resultList.add(c);
                index.put(c, resultList.size() - 1);
            } else {
                int position = index.get(c);
                if (resultList.size() - 1 > position) {
                    if (resultList.get(position + 1) < c) {
                        resultList.remove(position);
                        resultList.add(c);
                        index.put(c, resultList.size() - 1);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        resultList.forEach(sb::append);
        return sb.toString();
    }

    public char findTheDifference(String s, String t) {
        if (t == null || t.isEmpty()) {
            return ' ';
        }
        if (s == null || s.isEmpty()) {
            return t.charAt(0);
        }
        HashMap<Character, Integer> sMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (sMap.containsKey(c)) {
                sMap.put(c, sMap.get(c) + 1);
            } else {
                sMap.put(c, 1);
            }
        }
        for (char c : t.toCharArray()) {
            if (!sMap.containsKey(c)) {
                return c;
            } else {
                if (sMap.get(c) <= 0) {
                    return c;
                } else {
                    sMap.put(c, sMap.get(c) - 1);
                }
            }
        }
        return ' ';
    }

    public int hIndexSorted(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }

        int h = citations.length;
        int l = 0;
        int i;
        int len = citations.length;

        while (l < h) {
            i = l + (h - l) / 2;
            if (citations[i] == len - i) {
                return citations[i];
            } else if (citations[i] > len - i) {
                h = i;
            } else {
                l = i + 1;
            }

        }
        return len - l;
    }

    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int count = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] > count) {
                count++;
                continue;
            } else {
                break;
            }
        }
        return count;
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int temp = n;
        do {
            temp = temp >> 1;
            if ((temp & n) != 0) {
                return false;
            }
        } while (temp > 0);
        return true;
    }

    public static final String S0 = "Zero";
    public static final String S1 = "One";
    public static final String S2 = "Two";
    public static final String S3 = "Three";
    public static final String S4 = "Four";
    public static final String S5 = "Five";
    public static final String S6 = "Six";
    public static final String S7 = "Seven";
    public static final String S8 = "Eight";
    public static final String S9 = "Nine";
    public static final String S10 = "Ten";
    public static final String S11 = "Eleven";
    public static final String S12 = "Twelve";
    public static final String S13 = "Thirteen";
    public static final String S14 = "Fourteen";
    public static final String S15 = "Fifteen";
    public static final String S16 = "Sixteen";
    public static final String S17 = "Seventeen";
    public static final String S18 = "Eighteen";
    public static final String S19 = "Nineteen";
    public static final String S20 = "Twenty";
    public static final String S30 = "Thirty";
    public static final String S40 = "Forty";
    public static final String S50 = "Fifty";
    public static final String S60 = "Sixty";
    public static final String S70 = "Seventy";
    public static final String S80 = "Eighty";
    public static final String S90 = "Ninety";
    public static final String S100 = "Hundred";
    public static final String S1000 = "Thousand";
    public static final String SM = "Million";
    public static final String SB = "Billion";
    public static final String SN = "Negative";

    public String numberToWords(int num) {
        if (num == 0) {
            return S0;
        }
        boolean neg = num < 0;
        num = Math.abs(num);
        String s = "";
        if (num < 1000) {
            s = numberLessToWord(num);
        } else {
            int newnum = num;
            List<Integer> numParts = new ArrayList<>();
            while (newnum >= 1000) {
                numParts.add(newnum % 1000);
                newnum = newnum / 1000;
            }
            numParts.add(newnum);
            StringBuilder sb = new StringBuilder();
            for (int i = numParts.size() - 1; i >= 0; i--) {
                String part = numberLessToWord(numParts.get(i));
                if (part.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append(" ");
                    }
                    sb.append(part);
                    switch (i) {
                        case 1:
                            sb.append(" ");
                            sb.append(S1000);
                            break;
                        case 2:
                            sb.append(" ");
                            sb.append(SM);
                            break;
                        case 3:
                            sb.append(" ");
                            sb.append(SB);
                            break;
                    }
                }
            }
            s = sb.toString();
        }
        if (neg) {
            s = SN + s;
        }
        return s;
    }

    private String numberLessToWord(int num) {
        if (num == 0) {
            return "";
        }
        int[] nums = new int[3];
        nums[0] = num % 10;
        nums[1] = (num % 100) / 10;
        nums[2] = num / 100;
        String s = "";
        String temp = "";
        if (nums[2] > 0) {
            switch (nums[2]) {
                case 1:
                    temp = S1;
                    break;
                case 2:
                    temp = S2;
                    break;
                case 3:
                    temp = S3;
                    break;
                case 4:
                    temp = S4;
                    break;
                case 5:
                    temp = S5;
                    break;
                case 6:
                    temp = S6;
                    break;
                case 7:
                    temp = S7;
                    break;
                case 8:
                    temp = S8;
                    break;
                case 9:
                    temp = S9;
                    break;
            }
        }
        if (temp.length() > 0) {
            s = String.format("%s %s", temp, S100);
            temp = "";
        }
        if (nums[1] > 1) {
            switch(nums[1]) {
                case 2:
                    temp = S20;
                    break;
                case 3:
                    temp = S30;
                    break;
                case 4:
                    temp = S40;
                    break;
                case 5:
                    temp = S50;
                    break;
                case 6:
                    temp = S60;
                    break;
                case 7:
                    temp = S70;
                    break;
                case 8:
                    temp = S80;
                    break;
                case 9:
                    temp = S90;
                    break;
            }
        } else if (nums[1] == 1) {
            switch(nums[0]) {
                case 0:
                    temp = S10;
                    break;
                case 1:
                    temp = S11;
                    break;
                case 2:
                    temp = S12;
                    break;
                case 3:
                    temp = S13;
                    break;
                case 4:
                    temp = S14;
                    break;
                case 5:
                    temp = S15;
                    break;
                case 6:
                    temp = S16;
                    break;
                case 7:
                    temp = S17;
                    break;
                case 8:
                    temp = S18;
                    break;
                case 9:
                    temp = S19;
                    break;
            }
        }
        if (temp.length() > 0) {
            if (s.length() > 0) {
                s = String.format("%s %s", s, temp);
            } else {
                s = temp;
            }
            temp = "";
        }
        if (nums[1] != 1 && nums[0] > 0) {
            switch(nums[0]) {
                case 1:
                    temp = S1;
                    break;
                case 2:
                    temp = S2;
                    break;
                case 3:
                    temp = S3;
                    break;
                case 4:
                    temp = S4;
                    break;
                case 5:
                    temp = S5;
                    break;
                case 6:
                    temp = S6;
                    break;
                case 7:
                    temp = S7;
                    break;
                case 8:
                    temp = S8;
                    break;
                case 9:
                    temp = S9;
                    break;
            }
        }
        if (temp.length() > 0) {
            if (s.length() > 0) {
                s = String.format("%s %s", s, temp);
            } else {
                s = temp;
            }
        }
        return s;
    }

    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
        }
        return matrix[m][n];
        //return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    public int lengthLongestPath(String input) {
        int max = 0;
        if (input == null || input.isEmpty()) {
            return max;
        }
        boolean isFile = false;
        Stack<Map.Entry<Integer, String>> stack = new Stack<>();
        String[] inputParts = input.split("\n");
        int length = 0;
        for (int i = 0; i < inputParts.length; i++) {
            Map.Entry<Integer, String> part = getPartPath(inputParts[i]);
            isFile = part.getValue().contains(".");
            if (isFile) {
                while (!stack.isEmpty() && part.getKey() <= stack.peek().getKey()) {
                    length -= stack.peek().getValue().length();
                    length--;
                    stack.pop();
                }
                if (length == 0) {
                    max = Math.max(max, part.getValue().length());
                } else {
                    max = Math.max(max, length + 1 + part.getValue().length());
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(part);
                    length = part.getValue().length();
                } else {
                    if (part.getKey() > stack.peek().getKey()) {
                        length += 1;
                        length += part.getValue().length();
                        stack.push(part);
                    } else {
                        while (!stack.isEmpty() && part.getKey() <= stack.peek().getKey()) {
                            length -= stack.peek().getValue().length();
                            length--;
                            stack.pop();
                        }
                        length++;
                        length += part.getValue().length();
                        stack.push(part);
                    }
                }
            }
        }
        return max;
    }

    public Map.Entry<Integer, String> getPartPath(String input) {
        int level = 0;
        String value = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\t') {
                level++;
            } else {
                break;
            }
        }
        value = input.substring(level);
        if (level == 0) {
            if (value.startsWith("    ")) {
                level++;
                value = value.substring(4);
            }
        }
        return new HashMap.SimpleEntry<Integer, String>(level, value);
    }

    public String simplifyPath(String path) {
        String result = "/";
        if (path == null || path.isEmpty()) {
            return result;
        }
        String[] pathParts = path.split("/");
        Stack<String> pathStack = new Stack<>();
        for (int i = 0; i < pathParts.length; i++) {
            if (pathParts[i].equals(".") || pathParts[i].isEmpty()) {
                continue;
            } else if (pathParts[i].equals("..")) {
                if (!pathStack.isEmpty()) {
                    pathStack.pop();
                }
            } else {
                pathStack.push(pathParts[i]);
            }
        }
        Stack<String> reversePart = new Stack<>();
        while(!pathStack.isEmpty()) {
            reversePart.push(pathStack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while(!reversePart.isEmpty()) {
            sb.append("/");
            sb.append(reversePart.pop());
        }
        if (sb.length() > 0) {
            result = sb.toString();
        }
        return result;
    }

    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else {
                while(!stack.isEmpty()) {
                    if (stack.peek() >= nums[i]) {
                        break;
                    }
                    stack.pop();
                    if (stack.pop() > nums[i]) {
                        return true;
                    }
                }
                stack.push(nums[i]);
                stack.push(min);
            }
        }
        return false;
    }

    public int nthUglyNumber(int n) {
        int[] primes = {2,3,5};
        return nthSuperUglyNumber(n, primes);
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] result = new int[n];
        result[0] = 1;
        int i = 1;
        int[] primeIndex = new int[primes.length];

        while (i < n) {
            result[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                result[i] = Math.min(result[i], primes[j] * result[primeIndex[j]]);
            }
            for (int j = 0; j < primes.length; j++) {
                if (result[i] == primes[j] * result[primeIndex[j]]) {
                    primeIndex[j] += 1;
                }
            }
            i++;
        }
        return result[n - 1];
    }

    private int minInList(List<Integer> listOfInt) {
        int min = Integer.MAX_VALUE;
        for (Integer value : listOfInt) {
            if (min > value) {
                min = value;
            }
        }
        return min;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length < 1) {
            return false;
        }
        int maxI = matrix.length;
        int maxJ = matrix[0].length;
        if (maxJ < 1) {
            return false;
        }
        return searchMatrix(matrix, 0, 0, maxI, maxJ, target);
    }

    private boolean searchMatrix(int[][] matrix, int startI, int startJ, int maxI, int maxJ, int target) {
        if (startI == maxI && startJ == maxJ) {
            return matrix[startI][startJ] == target;
        }
        int i = startI;
        int j = startJ;
        while (true) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                if (i >= maxI - 1 && j >= maxJ - 1) {
                    return false;
                }
                if (i < maxI - 1) {
                    i++;
                }
                if (j < maxJ - 1) {
                    j++;
                }
            } else {
                if (i <= startI && j <= startJ) {
                    return false;
                }
                else {
                    if (j > startJ) {
                        if (searchMatrix(matrix, i, startJ, maxI, j, target)) {
                            return true;
                        } else {
                            if (i == startI) {
                                return false;
                            } else {
                                return searchMatrix(matrix, startI, j, i, maxJ, target);
                            }
                        }
                    } else {
                        if (searchMatrix(matrix, startI, j, i, maxJ, target)) {
                            return true;
                        } else {
                            if (j == startJ) {
                                return false;
                            } else {
                                return searchMatrix(matrix, i, startJ, maxI, j, target);
                            }
                        }
                    }
                }

            }
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        if (digits.contains("0") || digits.contains("1") || digits.contains("*") || digits.contains("#")) {
            return result;
        }
        String[] digitStrs = new String[digits.length()];
        int[] index = new int[digits.length()];
        //initialize
        for (int i = 0; i < digits.length(); i++) {
            digitStrs[i] = getDigitString(digits.charAt(i));
        }

        while (index[0] < digitStrs[0].length()) {
            String part = "";
            for (int i = 0; i < digits.length(); i++) {
                part += digitStrs[i].charAt(index[i]);
            }
            result.add(part);
            //increase index
            int j = digits.length() - 1;
            index[j]++;
            while (j >= 0 && index[j] >= digitStrs[j].length()) {
                if (j == 0) {
                    break;
                }
                index[j] = 0;
                j--;
                if (j >= 0) {
                    index[j]++;
                }
            }
        }
        return result;
    }

    private String getDigitString(char digit) {
        if (digit > '1' && digit <= '9') {
            String result = "";
            switch (digit) {
                case '2':
                    result = "abc";
                    break;
                case '3':
                    result = "def";
                    break;
                case '4':
                    result = "ghi";
                    break;
                case '5':
                    result = "jkl";
                    break;
                case '6':
                    result = "mno";
                    break;
                case '7':
                    result = "pqrs";
                    break;
                case '8':
                    result = "tuv";
                    break;
                case '9':
                    result = "wxyz";
                    break;
            }
            return result;
        } else {
            return "";
        }
    }

    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (board[i].length != 9) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            List<Integer> checkList = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] <= '9' && board[i][j] >= '0') {
                    checkList.add(board[i][j] - '0');
                }
            }
            if (!validList(checkList)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            List<Integer> checkList = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] <= '9' && board[j][i] >= '0') {
                    checkList.add(board[j][i] - '0');
                }
            }
            if (!validList(checkList)) {
                return false;
            }
        }
        int k = 0;
        while (k < 9) {
            int scaleI = k / 3;
            int scaleJ = k % 3;
            List<Integer> checkList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i + scaleI * 3][j + scaleJ * 3] <= '9' && board[i + scaleI * 3][j + scaleJ * 3] >= '0') {
                        checkList.add(board[i + scaleI * 3][j + scaleJ * 3] - '0');
                    }
                }
            }
            if (!validList(checkList)) {
                return false;
            }
            k++;
        }
        return true;
    }

    private boolean validList(List<Integer> checkList) {
        int[] array = new int[10];
        for (Integer num : checkList) {
            array[num]++;
        }
        for (int i = 0; i < 10; i++) {
            if (array[i] > 1) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        int i = 0;
        while (i <= nums.length - 3) {
            int j = i + 1;
            int k = nums.length - 1;
            while(j <= nums.length - 2) {
                while(k > j && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }
                if (k > j && nums[i] + nums[j] + nums[k] == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
                do {
                    j++;
                } while (j <= nums.length - 2 && nums[j - 1] == nums[j]);
            }
            do {
                i++;
            } while(i <= nums.length - 3 && nums[i - 1] == nums[i]);
        }
        return result;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = "";
        int index = 0;
        while (true) {
            for (int i = 0; i < strs.length; i++) {
                if (index >= strs[i].length()) {
                    return prefix;
                }
            }
            char x = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (x != strs[i].charAt(index)) {
                    return prefix;
                }
            }
            prefix += x;
            index++;
        }
    }

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while(i < j) {
            if (height[i] < height[j]) {
                maxArea = Math.max(maxArea, height[i] * (j - i));
                i++;
            } else if (height[i] > height[j]) {
                maxArea = Math.max(maxArea, height[j] * (j - i));
                j--;
            } else {
                maxArea = Math.max(maxArea, height[i] * (j - i));
                i++;
                j--;
            }
        }
        return maxArea;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int startIndex = 0;
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {
            int k = i;
            int j = i;
            while (k < s.length() - 1 && s.charAt(k) == s.charAt(k + 1)) {
                k++;
            }
            while (j > 0 && k < s.length() - 1 && s.charAt(j - 1) == s.charAt(k + 1)) {
                j--;
                k++;
            }
            if (k - j + 1 > maxLength) {
                maxLength = k - j + 1;
                startIndex = j;
            }
            if (s.length() - i < maxLength / 2) {
                break;
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        char[] charset = str.toCharArray();
        int startIndex = 0;
        int flag = 1;
        if (charset[startIndex] == '-') {
            flag = -1;
            startIndex++;
        } else if (charset[startIndex] == '+') {
            flag = 1;
            startIndex++;
        }
        int result = 0;
        int temp = 0;
        boolean overflow = false;
        while(startIndex < charset.length) {
            if (charset[startIndex] >= '0' && charset[startIndex] <= '9') {
                temp = result;
                result = result * 10 + (int) (charset[startIndex] - '0');
                startIndex++;
                if (temp > 1000000 && result / temp != 10) {
                    result = Integer.MAX_VALUE;
                    overflow = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (flag == -1 && overflow) {
            return Integer.MIN_VALUE;
        }
        return flag * result;
    }

    public int reverse(int x) {

        if (x == 0) {
            return 0;
        }

        if (x > -10 && x < 10) {
            return x;
        }

        int y = 0;
        List<Integer> digits = new ArrayList<>();
        int flag = x > 0 ? 1 : -1;
        int xcopy = flag * x;
        while (xcopy > 0) {
            digits.add(xcopy % 10);
            xcopy /= 10;
        }

        for (int i = 0; i < digits.size(); i++) {
            if (y <= Integer.MAX_VALUE / 10) {
                y = y * 10 + digits.get(i);
            } else {
                return 0;
            }
        }
        return y * flag;
    }

    public String convert(String s, int numRows) {
        if (numRows <= 0) {
            return null;
        }
        if (numRows == 1) {
            return s;
        }
        if (s == null) {
            return null;
        } else if (s.length() == 0) {
            return "";
        }

        char[] result = new char[s.length()];
        int index = 0;
        int baseStep = 2 * (numRows - 1);

        for (int i = 1; i <= numRows; i++) {
            int step1 = baseStep - (i - 1) * 2 == 0 ? baseStep : baseStep - (i - 1) * 2;
            int step2 = baseStep - step1 == 0 ? step1 : baseStep - step1;
            boolean firstStep = true;
            int strIndex = i - 1;
            while(strIndex < s.length()) {
                result[index] = s.charAt(strIndex);
                index++;
                strIndex += firstStep ? step1 : step2;
                firstStep = !firstStep;
            }
        }
        return new String(result);
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode a = head;
        ListNode b = head;
        boolean result = false;
        do {
            a = a.next;
            b = b.next.next;
            if (a.equals(b)) {
                result = true;
                break;
            }
        } while (a != null && b != null && b.next != null);
        return result;
    }


    public int findKthNumber(int n, int k) {
        int curr = 1;
        while (k > 1) {
            int steps = calSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr++;
                k -= steps;
            } else {
                curr *= 10;
                k--;
            }
        }
        return curr;
    }

    public int calSteps(int n, int n1, long n2) {
        int steps = 0;
        int value = 1;
        int ten = 10;
        while (n / 10 > value) {
            value += ten;
            ten *= 10;
        }
        steps = value + n - ten * n1 + 1;
//
//		int steps = 0;
//		while (n1 <= n) {
//			steps += Math.min(n + 1, n2) - n1;
//			n1 *= 10;
//			n2 *= 10;
//		}
        return steps;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashSet<Character> subCharSet = new HashSet<>();
        int i = 0;
        int max = 0;
        for (int j = 0; j < s.length(); j ++) {
            if (!subCharSet.contains(s.charAt(j))) {
                subCharSet.add(s.charAt(j));
                max = Math.max(max, subCharSet.size());
            } else {
                while(s.charAt(i) != s.charAt(j)) {
                    subCharSet.remove(s.charAt(i));
                    i++;
                }
                subCharSet.remove(s.charAt(i));
                i++;
                subCharSet.add(s.charAt(j));
            }
        }
        return max;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numsHash = new HashMap<>();
        int[] result = new int[2];
        int minus = 0;
        for (int i = 0; i < nums.length; i++) {
            minus = target - nums[i];
            if (numsHash.containsKey(minus)) {
                result[0] = numsHash.get(minus);
                result[1] = i;
                return result;
            } else {
                numsHash.put(nums[i], i);
            }
        }
        return result;
    }

    public int getFirstNoneZeroBit(int x) {
        return 0;
    }

    public int findKthNumber2(int n, int k) {
        int currentVal = 1;
        int i = 1;
        if (k < 1 || n < k) {
            return currentVal;
        }

        int top = n;
        int digits = 1;
        while (top >= 10) {
            top /= 10;
            digits++;
        }

        int baseSum = 0;
        if (top > 1) {
            for (int j = 0; j < digits; j++) {
                baseSum += (int)Math.pow(10, j);
            }
            int baseTime = 1;
            while (k - i >= baseSum && currentVal < top) {
                i += baseSum;
                currentVal++;
            }
        }

        while (i < k) {
            i++;
            if (n / 10 >= currentVal) {
                currentVal *= 10;
                continue;
            }
            if (currentVal + 1 <= n) {
                currentVal += 1;
                while (currentVal % 10 == 0) {
                    currentVal /= 10;
                }
                continue;
            } else {
                currentVal = carryOver(n);
                continue;
            }
        }
        return currentVal;
    }

    public int carryOver(int n) {
        int high = n;
        if (high >= 10) {
            high /= 10;
        }
        return high + 1;
    }

}

