package com.isaac;

import java.util.ArrayList;
import java.util.List;

public class SolutionNormal {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        return result;
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
