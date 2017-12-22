package com.isaac;

import java.util.HashMap;

/**
 * Created by JHe on 2/15/2017.
 */
public class WordDictionary {

    private HashMap<Character, WordDictionary> nodes;

    private boolean isLeaf;

    /** Initialize your data structure here. */
    public WordDictionary() {
        nodes = new HashMap<>();
        isLeaf = false;
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word != null && !word.isEmpty()) {
            char[] chars = word.toCharArray();
            WordDictionary nodeToInsert = this;
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
                WordDictionary wd = new WordDictionary();
                nodeToInsert.nodes.put(chars[k], wd);
                nodeToInsert = wd;
                k++;
            }
            nodeToInsert.isLeaf = true;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        char[] chars = word.toCharArray();
        return search(this, chars, 0);
    }

    private boolean search(WordDictionary wd, char[] chars, int startIdex) {
        if (startIdex == chars.length) {
            return wd.isLeaf;
        }
        for (int i = startIdex; i < chars.length; i++) {
            if (chars[i] == '.') {
                if (wd.nodes.isEmpty()) {
                    return false;
                }

                for(HashMap.Entry<Character, WordDictionary> entry : wd.nodes.entrySet()) {
                    if (search(entry.getValue(), chars, startIdex + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (wd.nodes.containsKey(chars[i])) {
                    wd = wd.nodes.get(chars[i]);
                    return search(wd, chars, startIdex + 1);
                } else {
                    return false;
                }
            }
        }
        return wd.isLeaf;
    }
}
