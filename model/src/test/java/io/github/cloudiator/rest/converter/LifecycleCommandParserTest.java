package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertEquals;

import io.github.cloudiator.rest.model.LanceInterface.ContainerTypeEnum;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.LanceInterface;
import org.cloudiator.messages.entities.TaskEntities.TaskInterface;
import org.junit.Test;

public class LifecycleCommandParserTest {

  private static String testString = "rm -rf ~/melodic && mkdir ~/melodic && cd ~/melodic && wget https://s3-eu-west-1.amazonaws.com/melodic.testing.data/FCR/vms/mel1/FCRAPP.sh && chmod x ~/melodic/FCRAPP.sh && sudo wget https://s3-eu-west-1.amazonaws.com/melodic.testing.data/FCR/vms/mel1/vms-server.credentials -O /opt/vms-discovery-client/conf/vms-server.credentials && sudo chmod 700 /opt/vms-discovery-client/conf/vms-server.credentials && sudo cp /opt/vms-discovery-client/conf/vms-server.credentials /dev/null";
  io.github.cloudiator.rest.model.LanceInterface lIface = init();
  TaskEntities.LanceInterface lTEIface  = TaskEntities.LanceInterface.newBuilder().setPreInstall(testString).build();
  private final LanceInterfaceConverter lanceInterfaceConverter = new LanceInterfaceConverter();

  private io.github.cloudiator.rest.model.LanceInterface init() {
    io.github.cloudiator.rest.model.LanceInterface lI  = new io.github.cloudiator.rest.model.LanceInterface();
    lI.setContainerType(ContainerTypeEnum.NATIVE);
    lI.setPreInstall(testString);
    lI.setStart("echo \"test\"");
    return lI;
  }

  @Test
  public void testApply() {
    io.github.cloudiator.rest.model.LanceInterface result;
    result = lanceInterfaceConverter.applyBack(lTEIface);
    String actualString = result.getPreInstall();
    assertEquals(actualString, testString);
  }

  @Test
  public void testApplyBack() {
    TaskEntities.LanceInterface result;
    result = lanceInterfaceConverter.apply(lIface);
    String actualString = result.getPreInstall();
    assertEquals(actualString, testString);
  }
}
