package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.HardwareConverter;
import io.github.cloudiator.rest.model.Hardware;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Hardware.HardwareQueryRequest;
import org.cloudiator.messages.Hardware.HardwareQueryRequest.Builder;
import org.cloudiator.messages.Hardware.HardwareQueryResponse;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.HardwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class HardwareApiController implements HardwareApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public HardwareApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private UserServiceOld userService;

  @Autowired
  private HardwareService hardwareService;


  @Override
  public ResponseEntity<Hardware> editHardware(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Hardware to update ", required = true) @Valid @RequestBody Hardware hardware) {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

  public ResponseEntity<List<Hardware>> findHardware(
      @ApiParam(value = "(Optional) Unique identifier to filter a specific cloud") @Valid @RequestParam(value = "cloudId", required = false) String cloudId) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        //Preparation
        System.out.println("------------------ find Hardware -------------------");
        final Builder builder = HardwareQueryRequest.newBuilder()
            .setUserId(UserInfo.of(request).tenant());

        if (!Strings.isNullOrEmpty(cloudId)) {
          builder.setCloudId(cloudId);
        }

        HardwareConverter hardwareConverter = new HardwareConverter();
        List<Hardware> hardwareList = new ArrayList<>();
        HardwareQueryResponse response = null;
        // to Kafka

        response = hardwareService.getHardware(builder.build());

        for (IaasEntities.HardwareFlavor hardware : response.getHardwareFlavorsList()) {
          hardwareList.add(hardwareConverter.applyBack(hardware));
        }
        System.out.println("--------------------- " + hardwareList.size()
            + " item(s) found ---------------------------");

        return new ResponseEntity<List<Hardware>>(hardwareList, HttpStatus.OK);
      } catch (ResponseException re) {
        throw new ApiException(re.code(), re.getMessage());
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Hardware>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Hardware>>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Hardware> getHardware(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
