package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by volker on 30.05.17.
 */

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(ApiException.class)
  @ResponseBody
  public ResponseEntity<Error> handle(ApiException re) {
    return getErrorResponseEntity(re.getCode(), re.getMessage());
  }

  private ResponseEntity<Error> getErrorResponseEntity(int code, String message) {
    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(code);
    error.setMessage(message);

    return new ResponseEntity<>(error, headers,
        HttpStatus.valueOf(error.getCode()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handle(Exception e) {

    return getErrorResponseEntity(500, e.getMessage());

  }

}

