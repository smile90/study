package org.duancq90.suanfa.leetcode;

import lombok.Data;

public class Solution2 {
  @Data
  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int one = 0;
    ListNode temp1 = l1;
    for (int i = 1; temp1 != null; i *= 10) {
      one += temp1.val * i;
      temp1 = temp1.next;
    }
    System.out.println("one:" + one);

    int two = 0;
    ListNode temp2 = l2;
    for (int i = 1; temp2 != null; i *= 10) {
      two += temp2.val * i;
      temp2 = temp2.next;
    }
    System.out.println("two:" + two);

    int sum = one + two;
    System.out.println("sum:" + sum);

    ListNode result = new ListNode();
    ListNode next = result;
    while (next != null) {
      next.setVal(sum % 10);

      sum = sum/10;
      if (sum != 0) {
        next.setNext(new ListNode());
      }
      next = next.getNext();
    }
    return result;
  }

  public static void main(String[] args) {
    int[] i1 = new int[] {9,9,9,9,9,9,9};
    ListNode l1 = new ListNode();
    ListNode temp1 = l1;
    for (int i = 0; i < i1.length; i++) {
      temp1.setVal(i1[i]);
      if (i != i1.length) {
        temp1.setNext(new ListNode());
      }
      temp1 = temp1.getNext();
    }

    int[] i2 = new int[] {9,9,9,9};
    ListNode l2 = new ListNode();
    ListNode temp2 = l2;
    for (int i = 0; i < i2.length; i++) {
      temp2.setVal(i2[i]);
      if (i != i2.length) {
        temp2.setNext(new ListNode());
      }
      temp2 = temp2.getNext();
    }

    ListNode result = addTwoNumbers(l1, l2);
    while (result != null) {
      System.out.print(" " + result.getVal());
      result = result.getNext();
    }
    System.out.println();
  }
}
