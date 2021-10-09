package org.duancq90.suanfa.leetcode;

import com.alibaba.fastjson.JSON;

public class Solution1 {
  public static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[] {i, j};
        }
      }
    }
    return new int[] {};
  }

  public static void main(String[] args) {
    System.out.println(JSON.toJSONString(twoSum(new int[]{1,2,5,7,9,11}, 9)));
  }
}
