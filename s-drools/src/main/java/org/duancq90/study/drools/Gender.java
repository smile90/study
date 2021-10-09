package org.duancq90.study.drools;

import lombok.AllArgsConstructor;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/7/7 上午11:40
 */
@AllArgsConstructor
public enum Gender {
    MAN(1, "男"),
    WOMAN(2, "女"),
    OTHER(3, "其他"),
    UNKNOWN(4, "未知");

    private int code;
    private String text;
}
