package org.dcq.aop;

import java.lang.reflect.Proxy;
import org.dcq.aop.proxy.jdk.ISourceService;
import org.dcq.aop.proxy.jdk.SourceServiceHandler;
import org.dcq.aop.proxy.jdk.SourceServiceImpl2;
import org.junit.jupiter.api.Test;

public class proxySourceServiceHandlerTest {

  @Test
  public void test() {
    ISourceService proxy = (ISourceService) Proxy.newProxyInstance(
        getClass().getClassLoader(),
        new Class[] { ISourceService.class },
        new SourceServiceHandler(new SourceServiceImpl2()));
    proxy.run();
  }
}
