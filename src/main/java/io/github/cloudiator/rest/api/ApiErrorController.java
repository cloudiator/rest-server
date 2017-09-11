package io.github.cloudiator.rest.api;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by volker on 06.07.17.
 */
@RestController
public class ApiErrorController implements ErrorController {

  private static final String ERROR_MAPPING = "/error";

  @RequestMapping(value = ERROR_MAPPING)
  public ResponseEntity<String> error() {
    StringBuilder teapot =new StringBuilder();
     teapot.append("`                           (  \n")
           .append("              _            ) ) \n")
           .append("           _,(_)._         ((  \n");
     teapot.append("       __,(_______).        )) \n")
           .append("  __,/ __. /        \\     /\\_  \n");
     teapot.append(" / ,' /  |\"\"|        \\   /  /   \n");
     teapot.append("| |  |   |__|         |,'  /    \n");
     teapot.append(" \\ `.|                  ,;'      \n");
     teapot.append("  `. :                 ;'       \n");
     teapot.append("    `.              .,'        \n");
     teapot.append("      `-.________,-'          \n");

    return new ResponseEntity<String>(teapot.toString(), HttpStatus.I_AM_A_TEAPOT);
  }

  @Override
  public String getErrorPath() {
    return ERROR_MAPPING;
  }
}
