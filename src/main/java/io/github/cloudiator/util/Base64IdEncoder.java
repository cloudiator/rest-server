package io.github.cloudiator.util;

import com.google.common.base.Charsets;
import org.springframework.security.crypto.codec.Base64;

public class Base64IdEncoder implements IdEncoder {

  private final static Base64IdEncoder INSTANCE = new Base64IdEncoder();

  public static Base64IdEncoder create() {
    return INSTANCE;
  }

  private Base64IdEncoder() {
  }

  @Override
  public String encode(String s) {
    return new String(Base64.encode(s.getBytes(Charsets.UTF_8)));
  }

  @Override
  public String decode(String s) {
    return new String(Base64.decode(s.getBytes(Charsets.UTF_8)));
  }
}
