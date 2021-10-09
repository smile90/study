package org.duancq90.redis.lock;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;

public class Lock {

  // 1. Create config object
  static Config config = new Config();
  static {
    try {
      config = Config.fromYAML(new File("config-file.yaml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public Lock() {
  }

  public void lock() {
    
  }

  public static void main(String[] args) {
    new Lock();
  }
}
