package org.duancq90.study.drools;

import java.math.BigDecimal;
import java.util.Objects;

public class NumberUtil {

  public static BigDecimal add(BigDecimal... params) {
    Objects.requireNonNull(params, "params is null.");
    BigDecimal sum = BigDecimal.ZERO;
    for (BigDecimal param : params) {
      sum = sum.add(param);
    }
    return sum;
  }

  public static boolean le(BigDecimal source, BigDecimal target) {
    Objects.requireNonNull(source, "source is null.");
    Objects.requireNonNull(target, "target is null.");
    return source.compareTo(target) <= 0;
  }

  public static boolean ge(BigDecimal source, BigDecimal target) {
    Objects.requireNonNull(source, "source is null.");
    Objects.requireNonNull(target, "target is null.");
    return source.compareTo(target) >= 0;
  }
}
