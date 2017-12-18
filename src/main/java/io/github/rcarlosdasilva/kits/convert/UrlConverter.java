package io.github.rcarlosdasilva.kits.convert;

import java.io.File;
import java.net.URI;
import java.net.URL;

import io.github.rcarlosdasilva.kits.able.Convertible;

public class UrlConverter implements Convertible<Object, URL> {

  @Override
  public URL convert(Object original) {
    return convert(original, null);
  }

  @Override
  public URL convert(Object original, URL defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof URL) {
      return (URL) original;
    }

    try {
      if (original instanceof File) {
        return ((File) original).toURI().toURL();
      } else if (original instanceof URI) {
        return ((URI) original).toURL();
      } else {
        return new URL(original.toString());
      }
    } catch (Exception ex) {
      return defaultValue;
    }
  }

}
