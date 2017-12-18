package io.github.rcarlosdasilva.kits.convert.collect.impl;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import io.github.rcarlosdasilva.kits.convert.collect.AbstractMapConverter;
import io.github.rcarlosdasilva.kits.convert.collect.MapInternalConverter;

public class List2MapConverter<C, K, V> extends AbstractMapConverter<List<C>, Map<K, V>, C, K, V> {

  public List2MapConverter(MapInternalConverter<C, K, V> internalConverter) {
    super(internalConverter);
  }

  @Override
  public Map<K, V> mapInstance() {
    return Maps.newHashMap();
  }

}
