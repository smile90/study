package org.duancq90.suanfa.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 寻找两个正序数组的中位数
 * 数组,二分查找,分治
 * 思路：
 *  1、新建一个数组用来组合两个数组all
 *  2、两个数组和sum：
 *      1）sum=0，则直接为0
 *      2）sum%2=0，则取( all[sum/2 - 1] + all[sum/2] ) / 2
 *      3）sum%2=1，则取( all[(sum + 1)/2 - 1]
 * @author dcq
 * @version V1.0
 * @date 2021/7/2 下午3:46
 */
@Slf4j
public class Solution4 {

    public static double process(int[] one, int[] two) {
        int result = 0;
        int sum = one.length + two.length;
        // 1）
        if (sum <= 0) {
            return result;
        }

        int oneFlag = 0;
        int twoFlag = 0;

        int[] all = new int[sum];
        for (int i = 0; i < all.length; i++) {
            int oneVal = (oneFlag >= one.length ? Integer.MAX_VALUE : one[oneFlag]);
            int twoVal = (twoFlag >= two.length ? Integer.MAX_VALUE : two[twoFlag]);
            log.info("\noneFlag:{},twoFlag:{}\noneVal:{},twoVal:{}",
                    oneFlag, twoFlag, oneVal, twoVal);
            if (oneVal < twoVal) {
                all[i] = oneVal;
                oneFlag++;
            } else {
                all[i] = twoVal;
                twoFlag++;
            }
        }
        log.info("\nall:{}", JSON.toJSONString(all));

        if (sum % 2 == 0) {
            return Double.valueOf(all[sum / 2 - 1] + all[sum / 2]) / 2;
        } else {
            return Double.valueOf(all[(sum + 1)/2 - 1]);
        }
    }

    public static void main(String[] args) {
        int[] one = new int[] {1, 2};
        int[] two = new int[] {3, 4};
        log.info("\n ----{}\n ----{}\n ----{}", one, two, process(one, two));
    }
}
