package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Hardware;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T09:24:26.089+02:00")

@Api(value = "hardware", description = "the hardware API")
public interface HardwareApi {

    @ApiOperation(value = "", notes = "Returns all hardware visible to the user ", response = Hardware.class, responseContainer = "List", tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Hardware.class) })
    
    @RequestMapping(value = "/hardware",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Hardware>> findHardware();

}
