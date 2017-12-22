package com.isaac;

import java.util.HashMap;

/**
 * Created by JHe on 2/14/2017.
 */
public class Trie {

    private HashMap<Character, Trie> nodes;

    private boolean isLeaf;
    /** Initialize your data structure here. */
    public Trie() {
        nodes = new HashMap<>();
        isLeaf = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word != null && !word.isEmpty()) {
            char[] chars = word.toCharArray();
            Trie nodeToInsert = this;
            int k = chars.length;
            for (int i = 0; i < chars.length; i++) {
                if (nodeToInsert.nodes.containsKey(chars[i])) {
                    nodeToInsert = nodeToInsert.nodes.get(chars[i]);
                } else {
                    k = i;
                    break;
                }
            }
            while(k < chars.length) {
                Trie trie = new Trie();
                nodeToInsert.nodes.put(chars[k], trie);
                nodeToInsert = trie;
                k++;
            }
            nodeToInsert.isLeaf = true;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
             return true;
        }
        char[] chars = word.toCharArray();
        Trie nodeToInsert = this;
        for (int i = 0; i < chars.length; i++) {
            if (nodeToInsert.nodes.containsKey(chars[i])) {
                nodeToInsert = nodeToInsert.nodes.get(chars[i]);
            } else {
                return false;
            }
        }
        return nodeToInsert.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return true;
        }
        char[] chars = prefix.toCharArray();
        Trie nodeToInsert = this;
        for (int i = 0; i < chars.length; i++) {
            if (nodeToInsert.nodes.containsKey(chars[i])) {
                nodeToInsert = nodeToInsert.nodes.get(chars[i]);
            } else {
                return false;
            }
        }
        return true;
    }
}
