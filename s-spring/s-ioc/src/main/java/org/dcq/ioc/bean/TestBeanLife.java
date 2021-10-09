package org.dcq.ioc.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component("testBean")
@Slf4j
public class TestBeanLife implements
    BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, BeanPostProcessor {

  private TestA testA;

  private BeanFactory beanFactory;
  private String beanName;

  public TestA getTestA() {
    return testA;
  }
  public BeanFactory getBeanFactory() {
    return beanFactory;
  }
  public String getBeanName() {
    return beanName;
  }

  // 构造函数最早调用
  public TestBeanLife() {
    log.info("[constructor method]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
  }

  // 然后调用注入属性
  @Autowired
  public void setTestA(TestA testA) {
    log.info("[autowired set method]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
    this.testA = testA;
  }

  // 设置bean与bean factory[1]
  @Override
  public void setBeanName(String beanName) {
    log.info("[set bean name]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
    this.beanName = beanName;
  }

  // 设置bean与bean factory[2]
  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    log.info("[set bean factory]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
    this.beanFactory = beanFactory;
  }

  // 初始化方法，注解比实现接口早一点
  @PostConstruct
  public void postConstruct() {
    log.info("[@PostConstruct]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
  }

  // 初始化方法
  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("[after properties set]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
  }

  // 以上的步骤是在bean真正实例化前执行的扩展
  // 以下的步骤是在bean真正实例化后执行的扩展

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    log.info("[post process before initialization method]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
    log.info("[post process after initialization method]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
    return bean;
  }

  // 销毁方法，注解比实现接口早一点
  @PreDestroy
  public void preDestroy() {
    log.info("[@PreDestroy]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
  }

  // 销毁方法
  @Override
  public void destroy() throws Exception {
    log.info("[destroy]beanName:{},beanFactory:{},testBeanA:{},this:{}", beanName, beanFactory,
        testA, this);
  }
}
