package org.dcq.ioc;

import lombok.extern.slf4j.Slf4j;
import org.dcq.ioc.bean.TestA;
import org.dcq.ioc.bean.TestBeanLife;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class IocRunTest {

  @Test
  public void run() {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.scan("org.dcq.ioc");
//    ctx.register(IocMain.class);
    ctx.refresh();

    TestBeanLife testBeanLife = ctx.getBean("testBean", TestBeanLife.class);
    log.info("test:{}，{}", testBeanLife, testBeanLife.getTestA());
    TestA testA = ctx.getBean("testA", TestA.class);
    log.info("test:{},{}", testA, testA.getTestBeanLife());

    ctx.close();
  }
}
