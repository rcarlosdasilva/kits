package io.github.rcarlosdasilva.kits.convert.collect;

import java.util.Map.Entry;

public class Pair<K, V> implements Entry<K, V> {

  private K key;
  private V value;

  public Pair(K key, V value) {
    super();
    this.key = key;
    this.value = value;
  }

  @Override
  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public V setValue(V value) {
    this.value = value;
    return value;
  }

}
