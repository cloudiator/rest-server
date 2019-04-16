package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ApiExceptionHandler.class);

  @ExceptionHandler(ApiException.class)
  @ResponseBody
  public ResponseEntity<Error> handle(ApiException re) {
    return getErrorResponseEntity(re.getCode(), re);
  }

  private ResponseEntity<Error> getErrorResponseEntity(int code, Throwable t) {
    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(code);
    error.setMessage(t.getMessage());

    LOGGER.error(String.format("Error while processing request: %s", t.getMessage()), t);

    return new ResponseEntity<>(error, headers,
        HttpStatus.valueOf(error.getCode()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handle(Exception e) {

    return getErrorResponseEntity(500, e);

  }

}

