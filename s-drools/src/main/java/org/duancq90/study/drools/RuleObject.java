package org.duancq90.study.drools;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RuleObject<K,V> extends ConcurrentHashMap<K,V> {

  ConcurrentHashMap<String, Integer> ruleResult = new ConcurrentHashMap<>();

  public void ruleMatch(String key, Integer value) {
    ruleResult.put(key, value);
  }

  public ConcurrentHashMap<String, Integer> getRuleResult() {
    return ruleResult;
  }

  public Set<String> getRuleCodes() {
    return ruleResult.keySet();
  }

  public Integer getRuleSum() {
    return ruleResult.values().stream().mapToInt((value) -> value).sum();
  }

  public BigDecimal getBigDecimal(K key) {
    return (BigDecimal) get(key);
  }
  public String getString(K key) {
    return (String) get(key);
  }

  @Override
  public String toString() {
    return "RuleParam:" + super.toString() +
        ",RuleResult:" + ruleResult;
  }
}
