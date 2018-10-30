package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.IpAddressType;
import io.github.cloudiator.rest.model.IpVersion;
import org.cloudiator.messages.entities.IaasEntities;
import org.hamcrest.Matchers;
import org.junit.Test;

public class IpAddressConverterTest {

  private final IpAddressConverter ipAddressConverter = new IpAddressConverter();
  //IP
  private final IpAddress restIpAddress;
  private final IaasEntities.IpAddress iaasIpAddress;

  public IpAddressConverterTest() {
    this.restIpAddress = new IpAddress()
        .ipAddressType(IpAddressType.PRIVATE_IP)
        .ipVersion(IpVersion.V4).value("192.168.178.2");
    this.iaasIpAddress = IaasEntities.IpAddress.newBuilder()
        .setType(IaasEntities.IpAddressType.PRIVATE_IP)
        .setVersion(IaasEntities.IpVersion.V4).setIp("192.168.178.2").build();
  }

  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    IpAddress result = ipAddressConverter.applyBack(iaasIpAddress);
    assertThat(result, Matchers.is(Matchers.equalTo(restIpAddress)));
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    IaasEntities.IpAddress actual = ipAddressConverter.apply(restIpAddress);
    assertThat(actual, Matchers.is(Matchers.equalTo(iaasIpAddress)));
  }

}
