package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewCloud;
import io.github.cloudiator.rest.model.Cloud;

import java.net.SocketTimeoutException;
import java.sql.Timestamp;
import org.cloudiator.messaging.ResponseException;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.ValidationException;

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

    Error error = new Error(400, "");
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

    Error error = new Error(400, "");
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

    Error error = new Error(404, e.getMessage());

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

    Error error = new Error(re.getCode(), "");

    myHttpStatus httpStatus = null;

    switch (re.getCode()) {
      case 409:
        String org = re.getMessage()
            .substring(re.getMessage().indexOf('='), re.getMessage().indexOf(','));
        error.setMessage("Cloud already exists: id" + org);
        break;
      default:
        error.setMessage(re.getMessage());
        httpStatus = new myHttpStatus(error.getCode(), error.getMessage());
    }

    System.out.println("----------------------------------------------");
    System.out.println(error.toString());
    System.out.println("----------------------------------------------");
    ResponseEntity<Error> test = new ResponseEntity<Error>(error,headers,HttpStatus.CONFLICT);
    System.out.println(test);
    return test;
  }

  @ExceptionHandler(SocketTimeoutException.class)
  @ResponseBody
  public ResponseEntity<Error> handle(SocketTimeoutException re) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    Error error = new Error(504, re.getMessage());

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

    Error error = new Error(404, iae.getMessage());

    System.out.println("----------------------------------------------");
    System.out.println(error.toString());
    System.out.println("----------------------------------------------");
    return new ResponseEntity<>(error, HttpStatus.valueOf(error.getCode()));
  }

}

class myHttpStatus {

  private int value;
  private String reasonPhrase;

  public myHttpStatus(int value, String reasonPhrase) {
    this.value = value;
    this.reasonPhrase = reasonPhrase;
  }

  /**
   * Return the integer value of this status code.
   */
  public int value() {
    return this.value;
  }

  /**
   * Return the reason phrase of this status code.
   */
  public String getReasonPhrase() {
    return this.reasonPhrase;
  }
}
