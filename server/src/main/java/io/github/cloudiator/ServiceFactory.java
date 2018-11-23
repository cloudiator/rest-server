package io.github.cloudiator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.uniulm.omi.cloudiator.util.configuration.Configuration;
import org.cloudiator.messaging.kafka.KafkaContext;
import org.cloudiator.messaging.kafka.KafkaMessagingModule;
import org.cloudiator.messaging.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by daniel on 21.06.17.
 */
public class ServiceFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFactory.class);
  private static final Injector INJECTOR = Guice
      .createInjector(
          new KafkaMessagingModule(new KafkaContext(Configuration.conf())),
          new MessageServiceModule());

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

  public FunctionService createFunctionService() {
    return INJECTOR.getInstance(FunctionService.class);
  }

  public JobService createJobService() {
    return INJECTOR.getInstance(JobService.class);
  }

  public TaskService createTaskService() {
    return INJECTOR.getInstance(TaskService.class);
  }

  public MatchmakingService createMatchmakingService() {
    return INJECTOR.getInstance(MatchmakingService.class);
  }

  public NodeService createNodeService() {
    return INJECTOR.getInstance(NodeService.class);
  }


  public InstallationRequestService createInstallationRequestService() {
    return INJECTOR.getInstance(InstallationRequestService.class);
  }

  public UserService createUserService() {
    return INJECTOR.getInstance(UserService.class);
  }

  public EncryptionService createEncryptionService() {
    return INJECTOR.getInstance(EncryptionService.class);
  }

  public ProcessService createProcessService() {
    return INJECTOR.getInstance(ProcessService.class);
  }

  public SecureStoreService createSecureStoreService() {
    return INJECTOR.getInstance(SecureStoreService.class);
  }

  public MonitorService createMonitorService() {
    return INJECTOR.getInstance(MonitorService.class);
  }

}
