package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.ImageConverter;
import io.github.cloudiator.rest.model.Image;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Image.ImageQueryRequest;
import org.cloudiator.messages.Image.ImageQueryResponse;
import org.cloudiator.messages.Image.ImageUpdatedResponse;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class ImagesApiController implements ImagesApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public ImagesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private UserServiceOld userService;

  @Autowired
  private ImageService imageService;

  @Override
  public ResponseEntity<Image> editImage(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Image to update ", required = true) @Valid @RequestBody Image image) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      //Preparation
      if (id.length() != 32) {
        throw new ApiException(406, "ID not valid. Length must be 32");
      }
      Image input = image;
      ImageConverter imageConverter = new ImageConverter();
      org.cloudiator.messages.Image.ImageUpdateRequest.Builder request = org.cloudiator.messages.Image.ImageUpdateRequest
          .newBuilder();
      request.setUserId(userService.getUserId());
      request.setImage(imageConverter.apply(image));
      org.cloudiator.messages.Image.ImageUpdatedResponse updatedResponse = null;
      //to Kafka
      updatedResponse = null; //not yet implemented

      return new ResponseEntity<Image>(HttpStatus.OK);

    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


  public ResponseEntity<List<Image>> findImages() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

        //preparation
        System.out.println("----------------- find Images -------------");
        ImageQueryRequest imageQueryRequest = ImageQueryRequest.newBuilder()
            .setUserId(userService.getUserId()).build();
        List<Image> imageList = new ArrayList<Image>();
        ImageQueryResponse imageQueryResponse = null;
        //to kafka
        try {
          imageQueryResponse = imageService.getImages(imageQueryRequest);
        } catch (ResponseException re) {
          throw new ApiException(re.code(), re.getMessage());
        }
        //converting response
        final ImageConverter imageConverter = new ImageConverter();
        for (IaasEntities.Image image : imageQueryResponse.getImagesList()) {
          imageList.add(imageConverter.applyBack(image));
        }

        System.out
            .println("imageList: \n " + imageList + "\n " + imageList.size() + " items listed.");

        return new ResponseEntity<List<Image>>(imageList, HttpStatus.OK);

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
