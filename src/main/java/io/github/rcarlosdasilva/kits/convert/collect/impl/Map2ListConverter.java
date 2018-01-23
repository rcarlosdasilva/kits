package io.github.rcarlosdasilva.kits.convert.collect.impl;

import com.google.common.collect.Lists;
import io.github.rcarlosdasilva.kits.convert.collect.AbstractCollectionConverter;
import io.github.rcarlosdasilva.kits.convert.collect.CollectionInternalConverter;

import java.util.List;
import java.util.Map;

public class Map2ListConverter<K, V, C>
    extends AbstractCollectionConverter<Map<K, V>, List<C>, K, V, C> {

  public Map2ListConverter(CollectionInternalConverter<K, V, C> internalConverter) {
    super(internalConverter);
  }

  @Override
  public List<C> collectionInstance() {
    return Lists.newArrayList();
  }

}
