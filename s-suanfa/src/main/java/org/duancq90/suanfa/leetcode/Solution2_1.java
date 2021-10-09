package org.duancq90.suanfa.leetcode;

import lombok.Data;

public class Solution2_1 {
  @Data
  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
      return val + " " + (next != null ? next : "");
    }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode result = new ListNode();
    ListNode next = result;
    int sumNext = 0;

    ListNode l1Temp = l1;
    ListNode l2Temp = l2;
    while (l1Temp != null || l2Temp != null || sumNext != 0) {
      int sum = (l1Temp != null ? l1Temp.getVal() : 0)
          + (l2Temp != null ? l2Temp.getVal() : 0)
          + sumNext;

      // 个位数
      next.setVal(sum % 10);

      // 更新值循环
      if (l1Temp != null) { l1Temp = l1Temp.getNext(); }
      if (l2Temp != null) { l2Temp = l2Temp.getNext(); }
      sumNext = sum / 10;

      // 下一位
      if (l1Temp != null || l2Temp != null || sumNext != 0) {
        next.setNext(new ListNode());
        next = next.getNext();
      }
    }
    return result;
  }

  public static void main(String[] args) {
//    int[] i1 = new int[] {9,9,9,9,9,9,9};
//    int[] i2 = new int[] {9,9,9,9};

    int[] i1 = new int[] {1,2,3,1};
    int[] i2 = new int[] {8,9,6};

    ListNode l1 = new ListNode();
    ListNode temp1 = l1;
    for (int i = 0; i < i1.length; i++) {
      temp1.setVal(i1[i]);
      if (i < i1.length - 1) {
        temp1.setNext(new ListNode());
        temp1 = temp1.getNext();
      }
    }
    ListNode l2 = new ListNode();
    ListNode temp2 = l2;
    for (int i = 0; i < i2.length; i++) {
      temp2.setVal(i2[i]);
      if (i < i2.length - 1) {
        temp2.setNext(new ListNode());
        temp2 = temp2.getNext();
      }
    }

    ListNode result = addTwoNumbers(l1, l2);
    System.out.println("l1：" + l1);
    System.out.println("l2：" + l2);
    System.out.println("result：" + result);
  }
}
