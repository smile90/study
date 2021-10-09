package org.dcq.aop.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SourceServiceHandler implements InvocationHandler {

  private ISourceService sourceService;

  public SourceServiceHandler(ISourceService sourceService) {
    this.sourceService = sourceService;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("Invocation Handler before....");
    Object result = method.invoke(sourceService, args);
    System.out.println("Invocation Handler after....");
    return result;
  }
}
