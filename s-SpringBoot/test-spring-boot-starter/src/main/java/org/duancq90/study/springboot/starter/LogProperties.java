package org.duancq90.study.springboot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 上午11:55
 */
@Data
@ConfigurationProperties("sys.log")
public class LogProperties {

    private boolean enable = true;
    private String name;
}
