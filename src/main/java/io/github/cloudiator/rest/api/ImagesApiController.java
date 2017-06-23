package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.ImageConverter;
import io.github.cloudiator.rest.model.Image;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.cloudiator.messages.Image.ImageQueryRequest;
import org.cloudiator.messages.Image.ImageQueryResponse;
import org.cloudiator.messages.Image.ImageUpdatedResponse;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class ImagesApiController implements ImagesApi {

  @Autowired
  private UserService userService;

  @Autowired
  private ImageService imageService;

  public ResponseEntity<Image> editImage(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Image to update ", required = true) @Valid @RequestBody Image image)
      throws Exception {

    //Preparation
    if (id.length() != 32) {
      throw new ResponseException(406, "ID not valid. Length must be 32");
    }
    Image input = image;
    ImageConverter imageConverter = new ImageConverter();
    org.cloudiator.messages.Image.ImageUpdateRequest.Builder request = org.cloudiator.messages.Image.ImageUpdateRequest
        .newBuilder();
    request.setUserId(userService.getUserId());
    request.setImage(imageConverter.apply(image));
    //to Kafka
    org.cloudiator.messages.Image.ImageUpdatedResponse updatedResponse; //not yet implemented
    return new ResponseEntity<Image>(HttpStatus.OK);
  }

  public ResponseEntity<List<Image>> findImages() throws ResponseException {
    //preparation
    System.out.println("----------------- find Images -------------");
    ImageQueryRequest imageQueryRequest = ImageQueryRequest.newBuilder()
        .setUserId(userService.getUserId()).build();
    List<Image> imageList = new ArrayList<Image>();
    //to kafka
    ImageQueryResponse imageQueryResponse = imageService.getImages(imageQueryRequest);
    //converting response
    ImageConverter imageConverter = new ImageConverter();
    for (IaasEntities.Image image : imageQueryResponse.getImagesList()) {
      imageList.add(imageConverter.applyBack(image));
    }

    System.out.println("imageList: \n " + imageList + "\n " + imageList.size() + " items listed.");

    return new ResponseEntity<List<Image>>(imageList, HttpStatus.OK);
  }

}
