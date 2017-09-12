package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.InstallRequest;
import io.github.cloudiator.rest.model.OclSolution;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.cloudiator.messages.Installation.InstallationRequest;
import org.cloudiator.messaging.services.InstallationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InstallerApiController implements InstallerApi {


  @Autowired
  private UserService userService;

  @Autowired
  private InstallationRequestService installationRequestService;

  public ResponseEntity<OclSolution> installTools(
      @ApiParam(value = "a request to install the cloudiator tools on a provided node", required = true)
      @Valid @RequestBody InstallRequest installRequest) {

    //TODO: create an Installation Request

    InstallationRequest.newBuilder().setUserId(userService.getUserId()).setInstallation(null).build();
    //TODO: publish an installation request message
    this.installationRequestService.createInstallationRequestAsync(null);

    return new ResponseEntity<OclSolution>(HttpStatus.OK);
  }

}
