package org.duancq90.study.springboot.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 上午11:54
 */
@Slf4j
public class LogService {

    @Autowired
    private LogProperties logProperties;

    public LogService() {
    }

    public void println() {
        log.info("----------test starter:{}---------", logProperties.getName());
        log.info("----------test starter:{}---------", logProperties.getName());
        log.info("----------test starter:{}---------", logProperties.getName());
        log.info("----------test starter:{}---------", logProperties.getName());
        log.info("----------test starter:{}---------", logProperties.getName());
        log.info("----------test starter:{}---------", logProperties.getName());
    }
}
