package io.github.cloudiator;

import de.uniulm.omi.cloudiator.util.PropertiesLoader;
import java.util.Properties;
import org.cloudiator.messaging.MessageInterface;
import org.cloudiator.messaging.services.CloudServiceImpl;
import org.cloudiator.messaging.services.HardwareServiceImpl;
import org.cloudiator.messaging.services.ImageServiceImpl;
import org.cloudiator.messaging.services.JobServiceImpl;
import org.cloudiator.messaging.services.LocationServiceImpl;
import org.cloudiator.messaging.services.SolutionServiceImpl;
import org.cloudiator.messaging.services.TaskServiceImpl;
import org.cloudiator.messaging.services.VirtualMachineServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by daniel on 21.06.17.
 */
public class ServiceFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFactory.class);
  private static final String MESSAGING_TIMEOUT_KEY = "timeout";

  private static final long MESSAGING_TIMEOUT_DEFAULT_VALUE = 10000;
  private static final String MESSAGING_SYSTEM_PROPERTIES_KEY = "messaging.config.file";
  private static final String MESSAGING_DEFAULT_PROPERTIES_FILE = "messaging.properties";

  private static final long messagingTimeout;

  static {
    final Properties properties = PropertiesLoader
        .loadPropertiesFrom(MESSAGING_SYSTEM_PROPERTIES_KEY, MESSAGING_DEFAULT_PROPERTIES_FILE);
    Long l = null;
    try {
      l = Long.valueOf(properties.getProperty(MESSAGING_TIMEOUT_KEY));
      LOGGER.info("using " + l + " as value for messaging timeout");
    } catch (NumberFormatException ex) {
      LOGGER.warn("no timeout value found. using default value");
      l = MESSAGING_TIMEOUT_DEFAULT_VALUE;
    }
    messagingTimeout = l;
  }

  private final MessageInterface messageInterface;

  public ServiceFactory(MessageInterface messageInterface) {
    this.messageInterface = messageInterface;
  }

  public CloudServiceImpl createCloudService() {

    final CloudServiceImpl cloudService = new CloudServiceImpl(messageInterface);
    cloudService.setResponseTimeout(messagingTimeout);
    return cloudService;
  }

  public HardwareServiceImpl createHardwareService() {
    final HardwareServiceImpl hardwareService = new HardwareServiceImpl(messageInterface);
    hardwareService.setResponseTimeout(messagingTimeout);
    return hardwareService;
  }

  public ImageServiceImpl createImageService() {

    final ImageServiceImpl imageService = new ImageServiceImpl(messageInterface);
    imageService.setResponseTimeout(messagingTimeout);
    return imageService;
  }

  public LocationServiceImpl createLocationService() {
    final LocationServiceImpl locationService = new LocationServiceImpl(messageInterface);
    locationService.setResponseTimeout(messagingTimeout);
    return locationService;
  }

  public VirtualMachineServiceImpl createVirtualMachineService() {
    final VirtualMachineServiceImpl virtualMachineService = new VirtualMachineServiceImpl(
        messageInterface);
    virtualMachineService.setResponseTimeout(0);
    return virtualMachineService;
  }

  public JobServiceImpl createJobService() {
    final JobServiceImpl jobService = new JobServiceImpl(messageInterface);
    jobService.setResponseTimeout(messagingTimeout);
    return jobService;
  }

  public TaskServiceImpl createTaskService() {
    final TaskServiceImpl taskService = new TaskServiceImpl(messageInterface);
    taskService.setResponseTimeout(messagingTimeout);
    return taskService;
  }

  public SolutionServiceImpl createSolutionService() {
    final SolutionServiceImpl solutionService = new SolutionServiceImpl(messageInterface);
    solutionService.setResponseTimeout(0);
    return solutionService;
  }

}
