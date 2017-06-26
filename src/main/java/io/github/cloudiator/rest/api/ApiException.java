package io.github.cloudiator.rest.api;


public class ApiException extends RuntimeException {

  private int code;

  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }

  public int getcode() {
    return code;
  }

}
