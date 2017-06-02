package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewCloud;
import io.github.cloudiator.rest.model.Cloud;

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

/**
 * Created by volker on 30.05.17.
 */

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(MethodArgumentNotValidException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Error error = new Error();
        error.code(400);
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();

            Integer excount = ex.getBindingResult().getErrorCount();
            Iterator iterator = ex.getBindingResult().getAllErrors().iterator();
            StringBuilder sb = new StringBuilder();

            sb.append("Input validation: ");
            sb.append(excount).append(" error(s) in field(s): ");

           while (iterator.hasNext()){
            FieldError fieldError = (FieldError)iterator.next();
            sb.append(fieldError.getField());
            if(iterator.hasNext()){
                sb.append(", ");
            }
           }
           sb.append(".");
           error.setMessage(sb.toString());
           json = mapper.writeValueAsString(error);

           System.out.println("------------------");
           System.out.println(error.toString());
           System.out.println("------------------");
           System.out.println(ex.getMessage());
           System.out.println("------------------");



        } catch (JsonGenerationException ej) {
            ej.printStackTrace();
        } catch (JsonMappingException ej) {
            ej.printStackTrace();
        } catch (IOException ej) {
            ej.printStackTrace();
        }


        return new ResponseEntity<>(json, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(HttpMessageNotReadableException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Error error = new Error();
        error.code(400);
        error.setMessage(ex.getMessage().substring(0, ex.getMessage().indexOf('\n')));
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(error);

            System.out.println("------------------");
            System.out.println(error.toString());
            System.out.println("------------------");
            System.out.println(ex.getMessage());
            System.out.println("------------------");



        } catch (JsonGenerationException ej) {
            ej.printStackTrace();
        } catch (JsonMappingException ej) {
            ej.printStackTrace();
        } catch (IOException ej) {
            ej.printStackTrace();
        }


        return new ResponseEntity<>(json, headers, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<String> handle404(NotFoundException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //headers.setContentType(MediaType.TEXT_PLAIN);
        String jsonout = "";
        StackTraceElement elements[] = e.getStackTrace();
        Error error = new Error();
        error.code(404);
        error.setMessage(e.getMessage());

        try {
            ObjectMapper mapper = new ObjectMapper();

            // convert map to JSON string
            jsonout = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(error);

            System.out.println("----------------------------------------------");
            System.out.println(error.toString());
            System.out.println("----------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------------");

        } catch (JsonGenerationException ej) {
            ej.printStackTrace();
        } catch (JsonMappingException ej) {
            ej.printStackTrace();
        } catch (IOException ej) {
            ej.printStackTrace();
        }


        return new ResponseEntity<>(jsonout, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    //@ResponseBody
    public ResponseEntity<String> handle(ResponseException re) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //headers.setContentType(MediaType.TEXT_PLAIN);
        String jsonout = "";
        StackTraceElement elements[] = re.getStackTrace();
        Error error = new Error();
        error.code(re.code());
        error.setMessage(re.getMessage());

        try {
            ObjectMapper mapper = new ObjectMapper();

            // convert map to JSON string
            jsonout = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(error);

            System.out.println("----------------------------------------------");
            System.out.println(error.toString());
            System.out.println("----------------------------------------------");
            System.out.println("");

        } catch (JsonGenerationException ej) {
            ej.printStackTrace();
        } catch (JsonMappingException ej) {
            ej.printStackTrace();
        } catch (IOException ej) {
            ej.printStackTrace();
        }

        return new ResponseEntity<>(jsonout, headers, HttpStatus.NOT_ACCEPTABLE);
    }
}
