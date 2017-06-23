package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Application;

import io.swagger.annotations.*;

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

@Controller
public class ApplicationsApiController implements ApplicationsApi {



    public ResponseEntity<Application> addApplication(@ApiParam(value = "Application to be created. " ,required=true )  @Valid @RequestBody Application application) {
        // do some magic!
        return new ResponseEntity<Application>(HttpStatus.OK);
    }

    public ResponseEntity<List<Application>> findApplications() {
        // do some magic!
        return new ResponseEntity<List<Application>>(HttpStatus.OK);
    }

}
