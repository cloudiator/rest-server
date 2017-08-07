package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.Applicationconverter;
import io.github.cloudiator.rest.model.Application;

import io.swagger.annotations.*;

import org.cloudiator.messages.entities.ApplicationEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ApplicationService;
import org.cloudiator.messaging.services.CloudService;
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

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class ApplicationsApiController implements ApplicationsApi {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;


    public ResponseEntity<Application> addApplication(@ApiParam(value = "Application to be created. ", required = true) @Valid @RequestBody Application application) {
        // do some magic!
        return new ResponseEntity<Application>(HttpStatus.OK);
    }

    public ResponseEntity<List<Application>> findApplications() {
        org.cloudiator.messages.Application.ApplicationQueryRequest request = org.cloudiator.messages.Application.ApplicationQueryRequest.newBuilder()
                .setUserId(userService.getUserId()).build();
        List<Application> result = new ArrayList<>();

        try {
            final Applicationconverter applicationconverter = new Applicationconverter();
            org.cloudiator.messages.Application.ApplicationQueryResponse response = applicationService.getApplications(request);
            for (ApplicationEntities.Application app : response.getApplicationsList()) {
                result.add(applicationconverter.applyBack(app));
            }
        } catch (ResponseException re) {
            throw new ApiException(re.code(), re.getMessage());
        }
        System.out.println("result:" + result.size() + "item(s) ");
        return new ResponseEntity<List<Application>>(result, HttpStatus.OK);
    }

}
