package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.InstallRequest;
import io.github.cloudiator.rest.model.OclSolution;

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
public class InstallerApiController implements InstallerApi {



    public ResponseEntity<OclSolution> installTools(@ApiParam(value = "a request to install the cloudiator tools on a provided node" ,required=true )  @Valid @RequestBody InstallRequest installRequest) {
        // do some magic!
        return new ResponseEntity<OclSolution>(HttpStatus.OK);
    }

}
