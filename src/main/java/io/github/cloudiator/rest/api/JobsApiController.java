package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.Job;

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
public class JobsApiController implements JobsApi {



    public ResponseEntity<Job> addJob(@ApiParam(value = "Job to be created. " ,required=true )  @Valid @RequestBody Job job) {
        // do some magic!
        return new ResponseEntity<Job>(HttpStatus.OK);
    }

    public ResponseEntity<List<Job>> findJobs() {
        // do some magic!
        return new ResponseEntity<List<Job>>(HttpStatus.OK);
    }

}
