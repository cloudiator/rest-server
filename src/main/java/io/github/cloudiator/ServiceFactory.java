package io.github.cloudiator;

import de.uniulm.omi.cloudiator.util.PropertiesLoader;
import java.io.IOException;
import java.util.Properties;
import org.cloudiator.messaging.MessageInterface;
import org.cloudiator.messaging.services.ApplicationServiceImpl;
import org.cloudiator.messaging.services.CloudServiceImpl;
import org.cloudiator.messaging.services.ComponentServiceImpl;
import org.cloudiator.messaging.services.HardwareServiceImpl;
import org.cloudiator.messaging.services.ImageServiceImpl;
import org.cloudiator.messaging.services.LocationServiceImpl;
import org.cloudiator.messaging.services.VirtualMachineServiceImpl;

/**
 * Created by daniel on 21.06.17.
 */
public class ServiceFactory {

  private final long timeout;
  private final MessageInterface messageInterface;


  public ServiceFactory(MessageInterface messageInterface) {
    this.messageInterface = messageInterface;
    try {
      final Properties properties = PropertiesLoader.loadPropertiesFrom("kafka.properties");
      timeout = Long.parseLong(properties.getProperty("responseTimeout"));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public CloudServiceImpl createCloudService() {

    final CloudServiceImpl cloudService = new CloudServiceImpl(messageInterface);
    cloudService.setResponseTimeout(timeout);
    return cloudService;
  }

  public HardwareServiceImpl createHardwareService() {
    final HardwareServiceImpl hardwareService = new HardwareServiceImpl(messageInterface);
    hardwareService.setResponseTimeout(timeout);
    return hardwareService;
  }

  public ImageServiceImpl createImageService() {

    final ImageServiceImpl imageService = new ImageServiceImpl(messageInterface);
    imageService.setResponseTimeout(timeout);
    return imageService;
  }

  public LocationServiceImpl createLocationService() {
    final LocationServiceImpl locationService = new LocationServiceImpl(messageInterface);
    locationService.setResponseTimeout(timeout);
    return locationService;
  }

  public VirtualMachineServiceImpl createVirtualMachineService() {
    final VirtualMachineServiceImpl virtualMachineService = new VirtualMachineServiceImpl(
        messageInterface);
    virtualMachineService.setResponseTimeout(0);
    return virtualMachineService;
  }

  public ApplicationServiceImpl createApplicationService() {
    final ApplicationServiceImpl applicationService = new ApplicationServiceImpl(messageInterface);
    applicationService.setResponseTimeout(timeout);
    return applicationService;
  }

  public ComponentServiceImpl createComponentService() {
    final ComponentServiceImpl componentService = new ComponentServiceImpl(messageInterface);
    componentService.setResponseTimeout(timeout);
    return componentService;
  }

}
