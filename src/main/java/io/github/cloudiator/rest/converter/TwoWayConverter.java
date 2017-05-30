package io.github.cloudiator.rest.converter;

import java.util.function.Function;

/**
 * Created by volker on 29.05.17.
 */
public interface TwoWayConverter<T, R> extends Function<T, R> {

  T applyBack(R r);

}
