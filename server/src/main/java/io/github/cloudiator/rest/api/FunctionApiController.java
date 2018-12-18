
package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.FunctionConverter;
import io.github.cloudiator.rest.model.Function;
import io.swagger.annotations.ApiParam;
import org.cloudiator.messages.Function.FunctionQueryMessage;
import org.cloudiator.messages.Function.FunctionQueryResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.FunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FunctionApiController implements FunctionApi {

  private static final Logger log = LoggerFactory.getLogger(FunctionApiController.class);
  private final HttpServletRequest request;
  private final FunctionConverter functionConverter = new FunctionConverter();
  private final FunctionService functionService;


  @org.springframework.beans.factory.annotation.Autowired
  public FunctionApiController(HttpServletRequest request, FunctionService functionService) {
    this.request = request;
    this.functionService = functionService;
  }

  @Override
  public ResponseEntity<List<Function>> findFunctions() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      FunctionQueryMessage queryMessage = FunctionQueryMessage.newBuilder()
          .setUserId(UserInfo.of(request).tenant()).build();
      List<Function> functions = queryFunctions(queryMessage);
      return new ResponseEntity<>(functions, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Function> getFunction(@ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      FunctionQueryMessage queryMessage = FunctionQueryMessage.newBuilder()
          .setUserId(UserInfo.of(request).tenant())
          .setFunctionId(id).build();
      List<Function> functions = queryFunctions(queryMessage);
      if (functions.isEmpty()) {
        throw new ApiException(404, "Function not found");
      } else {
        return new ResponseEntity<>(functions.get(0), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  private List<Function> queryFunctions(FunctionQueryMessage query) {
    FunctionQueryResponse response;
    try {
      response = functionService.getFunctions(query);
    } catch (ResponseException e) {
      log.error("Response Error", e);
      throw new ApiException(e.code(), e.getMessage());
    }
    return response.getFunctionsList().stream()
        .map(functionConverter::applyBack).collect(Collectors.toList());
  }

}