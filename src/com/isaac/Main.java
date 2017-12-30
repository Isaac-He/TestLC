package com.isaac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SolutionEasy se = new SolutionEasy();
        se.lengthOfLastWord("Hello World");
        se.selfDividingNumbers(19, 20);

        SolutionNormal sn = new SolutionNormal();
        sn.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
        sn.coinChange(new int[]{2}, 3);

        Solution s = new Solution();

        Movie movie1 = new Movie(1, (float)1.2);
        Movie movie2 = new Movie(2, (float)3.6);
        Movie movie3 = new Movie(3, (float)2.4);
        movie1.addSimilarMovie(movie2);
        movie1.addSimilarMovie(movie3);

        s.getMovieRecommendations(movie1, 1);

        List<List<String>> codeList = new ArrayList<>();
        List<String> code1 = new ArrayList<>();
        code1.add("orange");
        List<String> code2 = new ArrayList<>();
        code2.add("apple");
        code2.add("apple");
        List<String> code3 = new ArrayList<>();
        code3.add("banana");
        code3.add("orange");
        code3.add("apple");
        List<String> code4 = new ArrayList<>();
        code4.add("banana");
        codeList.add(code1);
        codeList.add(code2);
        codeList.add(code3);
        codeList.add(code4);


        List<String> shopping = new ArrayList<>();
        shopping.add("orange");
        shopping.add("apple");
        shopping.add("apple");
        shopping.add("banana");
        shopping.add("orange");
        shopping.add("apple");
        shopping.add("banana");


        int result = s.checkWinner(codeList, shopping);

        try {
            int line = s.count("E:\\Share\\1MRows_11Columns3.csv");
            System.out.println(String.format("Total lines %d.", line));
        } catch (IOException e) {
            System.out.println("Error.");
        }
        System.out.println(s.search(new int[]{1}, 1));

        s.generateParenthesis(4);
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        s.isValidBST(root);
        s.gameOfLife(new int[][] {{1,1,1,1,1,1,1,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}});
        s.intersect(new int[]{1,2,1}, new int[]{2,2});
        s.characterReplacement("ABAB", 2);
        s.hIndexSorted(new int[] {0,0,0});
        s.numberToWords(2147483647);
        int x = s.lengthLongestPath("dir\n    file.txt");
        System.out.println(x);
        WordDictionary wd = new WordDictionary();
        wd.addWord("and");
        wd.addWord("a");
        wd.addWord("a");
        wd.addWord("bad");
        System.out.println(wd.search("b."));
    }
}
