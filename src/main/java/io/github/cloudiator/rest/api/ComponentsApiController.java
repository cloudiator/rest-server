package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.Component;

import io.swagger.annotations.*;

import org.cloudiator.messaging.services.ApplicationService;
import org.cloudiator.messaging.services.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class ComponentsApiController implements ComponentsApi {

    @Autowired
    private UserService userService;

    @Autowired
    private ComponentService componentService;

    public ResponseEntity<Component> addComponent(@ApiParam(value = "Component to be created " ,required=true )  @Valid @RequestBody Component component) {
        // do some magic!
        return new ResponseEntity<Component>(HttpStatus.OK);
    }

    public ResponseEntity<List<Component>> findComponents() {
        // do some magic!
        return new ResponseEntity<List<Component>>(HttpStatus.OK);
    }

}
