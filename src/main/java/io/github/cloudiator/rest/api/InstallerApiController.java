package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.InstallationRequestConverter;
import io.github.cloudiator.rest.model.InstallationRequest;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.cloudiator.messages.Installation;
import org.cloudiator.messaging.services.InstallationRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InstallerApiController implements InstallerApi {

  @Autowired
  private InstallationRequestService installationRequestService;

  @Autowired
  private UserService userService;

  private InstallationRequestConverter installationRequestConverter = new InstallationRequestConverter();

  private static final Logger LOGGER = LoggerFactory.getLogger(InstallerApiController.class);

  public ResponseEntity<Void> installTools(
      @ApiParam(value = "a request to install the cloudiator tools on a provided node", required = true) @Valid @RequestBody InstallationRequest installationRequest) {


    System.out.println("Starting tools installation!");
    //TODO: get installationRequest service

    LOGGER.debug("installing something maybe...");

    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    installationRequestService.createInstallationRequestAsync(Installation.InstallationRequest.newBuilder()
        .setUserId(userService.getUserId())
        .setInstallation(installationRequestConverter.apply(installationRequest)).build(),
        (content, error) -> {
          System.out.println("Error " + error);
          System.out.println("Content " + content);
        });




    return new ResponseEntity<>(HttpStatus.OK);
  }

}
