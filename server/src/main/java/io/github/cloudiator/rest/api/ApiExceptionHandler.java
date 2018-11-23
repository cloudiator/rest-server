package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;

import java.net.SocketTimeoutException;
import java.sql.Timestamp;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Iterator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by volker on 30.05.17.
 */

@ControllerAdvice
public class ApiExceptionHandler {


  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<Error> handle(MethodArgumentNotValidException ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(400);
    //Cutting Error Message
    Integer excount = ex.getBindingResult().getErrorCount();
    Iterator iterator = ex.getBindingResult().getAllErrors().iterator();
    StringBuilder sb = new StringBuilder();
    sb.append("Input validation: ");
    sb.append(excount).append(" error(s) in field(s): ");
    while (iterator.hasNext()) {
      FieldError fieldError = (FieldError) iterator.next();
      sb.append(fieldError.getField());
      if (iterator.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append(".");

    error.setMessage(sb.toString());

    System.out.println("------------------");
    System.out.println(ex.getMessage());
    System.out.println("------------------");
    System.out.println(error.toString());
    System.out.println("------------------");
    return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<Error> handle(HttpMessageNotReadableException ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(400);
    error.setMessage(ex.getMessage().substring(0, ex.getMessage().indexOf('\n')));

    System.out.println("------------------");
    System.out.println(ex.getMessage());
    System.out.println("------------------");
    System.out.println(error.toString());
    System.out.println("------------------");
    return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<Error> handle404(NotFoundException e) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(404);
    error.setMessage(e.getMessage());

    System.out.println("----------------------------------------------");
    System.out.println(error.toString());
    System.out.println("----------------------------------------------");
    return new ResponseEntity<>(error, headers, HttpStatus.valueOf(error.getCode()));
  }

  @ExceptionHandler(ApiException.class)
  @ResponseBody
  public ResponseEntity<Error> handle(ApiException re) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    System.out.println(timestamp + " : Server Exception! ");
    System.err.println("ApiException: " + re.getCode() + ", " + re.getMessage());

    Error error = new Error();
    error.setCode(re.getCode());

    switch (re.getCode()) {
      case 409:
        String org = re.getMessage()
            .substring(re.getMessage().indexOf('='), re.getMessage().indexOf(','));
        error.setMessage("Cloud already exists: id" + org);
        break;
      default:
        error.setMessage(re.getMessage());
    }

    System.out.println("----------------------------------------------");
    System.out.println(error.toString());
    System.out.println("----------------------------------------------");
    ResponseEntity<Error> test = new ResponseEntity<Error>(error, headers,
        HttpStatus.valueOf(error.getCode()));
    System.out.println(test);
    return test;
  }

  @ExceptionHandler(SocketTimeoutException.class)
  @ResponseBody
  public ResponseEntity<Error> handle(SocketTimeoutException re) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(504);
    error.setMessage(re.getMessage());

    HttpStatus httpStatus = HttpStatus.valueOf(error.getCode());
    System.out.println("----------------------------------------------");
    System.out.println(error.toString());
    System.out.println("----------------------------------------------");
    return new ResponseEntity<>(error, headers, httpStatus);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<Error> handle(IllegalArgumentException iae) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error();
    error.setCode(404);
    error.setMessage(iae.getMessage());

    System.out.println("----------------------------------------------");
    System.out.println(error.toString());
    System.out.println("----------------------------------------------");
    return new ResponseEntity<>(error, HttpStatus.valueOf(error.getCode()));
  }

}

