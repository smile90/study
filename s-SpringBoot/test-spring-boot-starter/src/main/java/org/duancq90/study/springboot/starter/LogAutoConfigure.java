package org.duancq90.study.springboot.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 上午11:52
 */
@Slf4j
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfigure {

    @Bean
//    @ConditionalOnProperty(prefix = "sys.log", value = "enable", havingValue = "true")
    LogService logService() {
        log.info("init logService......");
        return new LogService();
    }
}
