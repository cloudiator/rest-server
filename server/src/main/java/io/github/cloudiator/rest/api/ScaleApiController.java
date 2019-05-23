package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.NodeConverter;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.model.Scale;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.NodeEntities.Node;
import org.cloudiator.messages.Process.ScaleRequest;
import org.cloudiator.messages.Process.ScaleResponse;
import org.cloudiator.messages.entities.ProcessEntities.Nodes;
import org.cloudiator.messaging.services.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScaleApiController implements ScaleApi {

    private static final Logger log = LoggerFactory.getLogger(ScaleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private static final NodeConverter NODE_CONVERTER = new NodeConverter();

    private final ProcessService processService;
    private final QueueService queueService;

    @org.springframework.beans.factory.annotation.Autowired
    public ScaleApiController(ObjectMapper objectMapper, HttpServletRequest request,ProcessService processService, QueueService queueService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.processService = processService;
        this.queueService = queueService;
    }

    public ResponseEntity<Queue> triggerScale(@ApiParam(value = "Scaling action to be executed " ,required=true )  @Valid @RequestBody Scale scale) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {


                final String tenant = UserInfo.of(request).tenant();

                List<Node> nodes = new ArrayList<>();
                for (io.github.cloudiator.rest.model.Node node: scale.getNodes()) {
                    nodes.add(NODE_CONVERTER.apply(node));
                }

                Nodes messageNodes = Nodes.newBuilder().addAllNodes(nodes).build();

                final ScaleRequest scaleRequest = ScaleRequest.newBuilder()
                    .setUserId(tenant)
                    .setScheduleId(scale.getSchedule())
                    .setTaskId(scale.getTask())
                    .setNodes(messageNodes)
                    .build();

                final QueueItem<ScaleResponse> queueItem = queueService
                    .queueCallback(tenant,
                        processCreatedResponse -> "schedule/" + scale.getSchedule());

                processService.createScaleRequestAsync(scaleRequest,queueItem.getCallback());

                final HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

                return new ResponseEntity<>(queueItem.getQueue(), httpHeaders, HttpStatus.ACCEPTED);

        }

        return new ResponseEntity<Queue>(HttpStatus.NOT_IMPLEMENTED);
    }

}
