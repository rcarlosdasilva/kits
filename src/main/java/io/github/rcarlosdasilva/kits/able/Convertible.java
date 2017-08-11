package io.github.rcarlosdasilva.kits.able;

public interface Convertible<F, T> {

  T convert(F original);

}
