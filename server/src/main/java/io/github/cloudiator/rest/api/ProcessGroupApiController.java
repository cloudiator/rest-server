package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.ProcessGroupConverter;
import io.github.cloudiator.rest.model.ProcessGroup;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.Process.ProcessGroupQueryMessage;
import org.cloudiator.messages.Process.ProcessGroupQueryResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProcessGroupApiController implements ProcessGroupApi {

    private static final Logger log = LoggerFactory.getLogger(ProcessGroupApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ProcessService processService;


    private final ProcessGroupConverter processGroupConverter = new ProcessGroupConverter();


    @org.springframework.beans.factory.annotation.Autowired
    public ProcessGroupApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ProcessGroup>> findProcessGroups() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            final String tenant = UserInfo.of(request).tenant();

            try {
                final ProcessGroupQueryResponse processQueryResponse = processService
                    .queryProcessGroups(ProcessGroupQueryMessage.newBuilder().setUserId(tenant).build());

                return new ResponseEntity<>(
                    processQueryResponse.getProcessGroupsList().stream().map(processGroupConverter::applyBack)
                        .collect(
                            Collectors.toList()), HttpStatus.OK);


            } catch (ResponseException e) {
                throw new ApiException(e.code(), e.getMessage());
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ProcessGroup> getProcessGroup(@ApiParam(value = "Unique identifier of the resource",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            final String tenant = UserInfo.of(request).tenant();

            try {
                final ProcessGroupQueryResponse processGroupQueryResponse = processService.queryProcessGroups(
                    ProcessGroupQueryMessage.newBuilder().setUserId(tenant).setProcessGroupId(id).build());

                if (processGroupQueryResponse.getProcessGroupsCount() == 0) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else if (processGroupQueryResponse.getProcessGroupsCount() > 1) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                return new ResponseEntity<>(
                    processGroupConverter.applyBack(processGroupQueryResponse.getProcessGroups(0)), HttpStatus.OK);


            } catch (ResponseException e) {
                throw new ApiException(e.code(), e.getMessage());
            }


        }
        return new ResponseEntity<ProcessGroup>(HttpStatus.NOT_IMPLEMENTED);
    }

}
