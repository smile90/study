package org.dcq.ioc.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("testA")
@Slf4j
public class TestA {

  private TestBeanLife testBeanLife;

  public TestA() {
    log.info("test bean a constructor");
  }

  public TestBeanLife getTestBeanLife() {
    return testBeanLife;
  }

  @Autowired
  public void setTestBeanLife(TestBeanLife testBeanLife) {
    this.testBeanLife = testBeanLife;
  }
}
