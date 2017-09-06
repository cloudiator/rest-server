package io.github.cloudiator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.uniulm.omi.cloudiator.util.PropertiesLoader;
import java.util.Properties;
import org.cloudiator.messaging.kafka.KafkaMessagingModule;
import org.cloudiator.messaging.services.CloudService;
import org.cloudiator.messaging.services.HardwareService;
import org.cloudiator.messaging.services.ImageService;
import org.cloudiator.messaging.services.JobService;
import org.cloudiator.messaging.services.LocationService;
import org.cloudiator.messaging.services.MessageServiceModule;
import org.cloudiator.messaging.services.NodeService;
import org.cloudiator.messaging.services.SolutionService;
import org.cloudiator.messaging.services.TaskService;
import org.cloudiator.messaging.services.VirtualMachineService;
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

  private static final Injector INJECTOR = Guice
      .createInjector(new KafkaMessagingModule(), new MessageServiceModule());

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

  public ServiceFactory() {
  }

  public CloudService createCloudService() {
    return INJECTOR.getInstance(CloudService.class);
  }

  public HardwareService createHardwareService() {
    return INJECTOR.getInstance(HardwareService.class);
  }

  public ImageService createImageService() {
    return INJECTOR.getInstance(ImageService.class);
  }

  public LocationService createLocationService() {
    return INJECTOR.getInstance(LocationService.class);
  }

  public VirtualMachineService createVirtualMachineService() {
    return INJECTOR.getInstance(VirtualMachineService.class);
  }

  public JobService createJobService() {
    return INJECTOR.getInstance(JobService.class);
  }

  public TaskService createTaskService() {
    return INJECTOR.getInstance(TaskService.class);
  }

  public SolutionService createSolutionService() {
    return INJECTOR.getInstance(SolutionService.class);
  }

  public NodeService createNodeService() {
    return INJECTOR.getInstance(NodeService.class);
  }

}
