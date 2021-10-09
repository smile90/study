package org.duancq90.study.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 下午12:16
 */
@RestController
public class Test {

    @Autowired
    private LogService logService;

    public Test() {
        System.out.println("I'm run...");
    }

    @RequestMapping("/test")
    public Object test() {
        Optional.ofNullable(logService)
                .ifPresent(LogService::println);
        return "success";
    }
}
