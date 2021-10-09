package org.dcq.ioc.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestBeanInstantiation implements
    InstantiationAwareBeanPostProcessor {

  // 构造函数最早调用
  public TestBeanInstantiation() {
    log.info("[constructor method]");
  }

  // 实例化前执行，可以控制这个类是否进行实际的实例化
  // 如果返回null，则正常实例化属性（注入）
  // 如果返回不为null，则跳过实例化属性（注入），直接跳到postProcessAfterInitialization方法
  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
      throws BeansException {
    log.info("[post process before instantiation method]");
    return null;
  }

  // 实例化后执行，可以控制这个类是否可以被别人注入(false:不注入,true:注入)
  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    log.info("[post process after instantiation method]");
    return true;
  }

  // 完成对属性的填充
  @Override
  public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
      throws BeansException {
    log.info("[post process properties method]");
    return null;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    log.info("[post process before initialization method]");
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
    log.info("[post process after initialization method]");
    return bean;
  }
}
