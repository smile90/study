package org.duancq90.suanfa.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 最长无重复字符串
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 下午3:55
 */
@Slf4j
public class Solution3 {

    public static List<Integer[]> run (String s) {
        List<Integer[]> results = new ArrayList<>();
        if (s == null || s.trim().isEmpty()) {
            return results;
        }

        Map<Character, Integer> result = new LinkedHashMap<>();
        int maxSize = 0;
        int begin = 0;

        // 开始位
        while(begin < s.length()) {
            int current = begin;

            // 结束位
            while (current < s.length()) {
                Character flagChar = s.charAt(current);
                Integer temp = result.get(flagChar);

                // 未重复：放入到结果集
                if (temp == null) {
                    result.put(flagChar, current++);
                }
//
//                // 重复了 或者 到最后一位了：
//                // 1）判断是否大于之前的最大
//                //    如果大：清空之前的，保存新的
//                //    如果相等：额外增加
//                //    如果小：什么也不做
//                // 2）判断重复的字母位是多少，然后从那个位置开始循环
//                if (temp != null) {
//                    if (current - begin > maxSize) {
//                        results = new ArrayList<>();
//                        maxSize = result.size();
//                        results.add(new Integer[] {maxSize, begin, current});
//                    } else if (current - begin == maxSize) {
//                        results.add(new Integer[] {maxSize, begin, current});
//                    }
//
//                    // 清空，重新开始
//                    result = new LinkedHashMap<>();
//                    begin = (temp != null ? temp + 1 : current + 1);
//                    break;
//                }

                if (temp != null) {
                    log.info("clear. begin:{}, current:{}, temp:{}", begin, current, temp);
                    // 清空，重新开始
                    result = new LinkedHashMap<>();
                    begin = (temp != null ? temp + 1 : s.length());
                    log.info("clear end. begin:{}, current:{}, temp:{}", begin, current, temp);
                    break;
                }
                log.info("{}", result);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        String s = "asdasdasf";
        List<Integer[]> results = run(s);
        log.info("\n ----{}\n ----{}", s, JSON.toJSONString(results));
        Optional.ofNullable(results).ifPresent(temps ->
            temps.forEach(temp -> {
                log.info("\n ----{},{},{}", temp[0], s.substring(temp[1], temp[2] + 1), JSON.toJSONString(temp));
            })
        );
    }


}
