package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.IpAddressType;
import io.github.cloudiator.rest.model.IpVersion;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 20.07.17.
 */
public class IpAddressConverter implements TwoWayConverter<IpAddress, IaasEntities.IpAddress> {

  private final IpAddressTypeConverter ipAddressTypeConverter = new IpAddressTypeConverter();
  private final IpAddressVersionConverter ipAddressVersionConverter = new IpAddressVersionConverter();

  @Override
  public IpAddress applyBack(IaasEntities.IpAddress ipAddress) {

    IpAddress ip = new IpAddress();
    ip.setIpAddressType(ipAddressTypeConverter.applyBack(ipAddress.getType()));
    ip.setIpVersion(ipAddressVersionConverter.applyBack(ipAddress.getVersion()));
    ip.setValue(ipAddress.getIp());
    return ip;
  }

  @Override
  public IaasEntities.IpAddress apply(IpAddress ipAddress) {
    IaasEntities.IpAddress.Builder builder = IaasEntities.IpAddress.newBuilder();
    if (ipAddress.getIpAddressType() != null) {
      builder.setType(ipAddressTypeConverter.apply(ipAddress.getIpAddressType()));
    } else {
      builder.clearType();
    }
    if (ipAddress.getIpVersion() != null) {
      builder.setVersion(ipAddressVersionConverter.apply(ipAddress.getIpVersion()));
    } else {
      builder.clearVersion();
    }

    builder.setIp(ipAddress.getValue());
    return builder.build();
  }

  private class IpAddressTypeConverter implements
      TwoWayConverter<IpAddressType, IaasEntities.IpAddressType> {

    @Override
    public IpAddressType applyBack(IaasEntities.IpAddressType ipAddressType) {
      switch (ipAddressType) {
        case PUBLIC_IP:
          return IpAddressType.PUBLIC_IP;
        case PRIVATE_IP:
          return IpAddressType.PRIVATE_IP;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Illegal IPAddressType " + ipAddressType);
      }
    }

    @Override
    public IaasEntities.IpAddressType apply(IpAddressType ipAddressType) {
      switch (ipAddressType) {
        case PRIVATE_IP:
          return IaasEntities.IpAddressType.PRIVATE_IP;
        case PUBLIC_IP:
          return IaasEntities.IpAddressType.PUBLIC_IP;
        default:
          throw new AssertionError("Illegal IPAddressType " + ipAddressType);
      }
    }
  }

  private class IpAddressVersionConverter implements
      TwoWayConverter<IpVersion, IaasEntities.IpVersion> {

    @Override
    public IpVersion applyBack(IaasEntities.IpVersion ipVersion) {
      switch (ipVersion) {
        case V4:
          return IpVersion.V4;
        case V6:
          return IpVersion.V6;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Illegal IPVersion " + ipVersion);
      }
    }

    @Override
    public IaasEntities.IpVersion apply(IpVersion ipVersion) {
      switch (ipVersion) {
        case V4:
          return IaasEntities.IpVersion.V4;
        case V6:
          return IaasEntities.IpVersion.V6;
        default:
          throw new AssertionError("Illegal IPVersion " + ipVersion);
      }
    }
  }

}
