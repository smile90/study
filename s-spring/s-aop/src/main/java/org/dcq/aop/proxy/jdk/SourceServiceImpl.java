package org.dcq.aop.proxy.jdk;

public class SourceServiceImpl implements ISourceService {

  @Override
  public void run() {
    System.out.println("source service impl run...");
  }
}
