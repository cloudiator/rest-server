package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.OperatingSystem;
import io.github.cloudiator.rest.model.OperatingSystemArchitecture;
import io.github.cloudiator.rest.model.OperatingSystemFamily;
import io.github.cloudiator.rest.model.OperatingSystemType;
import org.cloudiator.messages.entities.CommonEntities;


/**
 * Created by volker on 20.07.17.
 */
public class OperatingSystemConverter implements
    TwoWayConverter<OperatingSystem, CommonEntities.OperatingSystem> {

  private final OperatingSystemArchitectureConverter osaConverter = new OperatingSystemArchitectureConverter();
  private final OperatingSystemFamilyConverter osfConverter = new OperatingSystemFamilyConverter();

  @Override
  public OperatingSystem applyBack(CommonEntities.OperatingSystem operatingSystem) {
    return new OperatingSystem()
        .operatingSystemArchitecture(
            osaConverter.applyBack(operatingSystem.getOperatingSystemArchitecture()))
        .operatingSystemFamily(osfConverter.applyBack(operatingSystem.getOperatingSystemFamily()))
        .operatingSystemVersion(operatingSystem.getOperatingSystemVersion());
  }

  @Override
  public CommonEntities.OperatingSystem apply(OperatingSystem operatingSystem) {
    return CommonEntities.OperatingSystem.newBuilder()
        .setOperatingSystemArchitecture(
            osaConverter.apply(operatingSystem.getOperatingSystemArchitecture()))
        .setOperatingSystemFamily(osfConverter.apply(operatingSystem.getOperatingSystemFamily()))
        .setOperatingSystemVersion(operatingSystem.getOperatingSystemVersion()).build();
  }


  private class OperatingSystemArchitectureConverter implements
      TwoWayConverter<OperatingSystemArchitecture, CommonEntities.OperatingSystemArchitecture> {

    @Override
    public OperatingSystemArchitecture applyBack(
        CommonEntities.OperatingSystemArchitecture operatingSystemArchitecture) {
      switch (operatingSystemArchitecture) {
        case AMD64:
          return OperatingSystemArchitecture.AMD64;
        case I386:
          return OperatingSystemArchitecture.I386;
        case UNKOWN_OS_ARCH:
          return OperatingSystemArchitecture.UNKOWN;
        default:
          throw new AssertionError("corrupt OS-Architecture " + operatingSystemArchitecture);
      }
    }

    @Override
    public CommonEntities.OperatingSystemArchitecture apply(
        OperatingSystemArchitecture operatingSystemArchitecture) {
      switch (operatingSystemArchitecture) {
        case AMD64:
          return CommonEntities.OperatingSystemArchitecture.AMD64;
        case I386:
          return CommonEntities.OperatingSystemArchitecture.I386;
        case UNKOWN:
          return CommonEntities.OperatingSystemArchitecture.UNKOWN_OS_ARCH;
        default:
          throw new AssertionError("corrupt OS-Architecture " + operatingSystemArchitecture);
      }
    }
  }

  private class OperatingSystemTypeConverter implements
      TwoWayConverter<OperatingSystemType, CommonEntities.OperatingSystemType> {

    @Override
    public OperatingSystemType applyBack(CommonEntities.OperatingSystemType operatingSystemType) {
      switch (operatingSystemType) {
        case BSD:
          return OperatingSystemType.BSD;
        case MAC:
          return OperatingSystemType.MAC;
        case UNIX:
          return OperatingSystemType.UNIX;
        case LINUX:
          return OperatingSystemType.LINUX;
        case WINDOWS_OS:
          return OperatingSystemType.WINDOWS_OS;
        case UNKOWN_OS_TYPE:
          return OperatingSystemType.UNKOWN;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("corrupt OS-Type " + operatingSystemType);
      }
    }

    @Override
    public CommonEntities.OperatingSystemType apply(OperatingSystemType operatingSystemType) {
      switch (operatingSystemType) {
        case MAC:
          return CommonEntities.OperatingSystemType.MAC;
        case BSD:
          return CommonEntities.OperatingSystemType.BSD;
        case WINDOWS_OS:
          return CommonEntities.OperatingSystemType.WINDOWS_OS;
        case LINUX:
          return CommonEntities.OperatingSystemType.LINUX;
        case UNIX:
          return CommonEntities.OperatingSystemType.UNIX;
        case UNKOWN:
          return CommonEntities.OperatingSystemType.UNKOWN_OS_TYPE;
        default:
          throw new AssertionError("corrupt OS-Type " + operatingSystemType);
      }
    }
  }

  private class OperatingSystemFamilyConverter implements
      TwoWayConverter<OperatingSystemFamily, CommonEntities.OperatingSystemFamily> {

    @Override
    public OperatingSystemFamily applyBack(
        CommonEntities.OperatingSystemFamily operatingSystemFamily) {
      switch (operatingSystemFamily) {
        case UNKOWN_OS_FAMILY:
          return OperatingSystemFamily.UNKOWN_OS_FAMILY;
        case AIX:
          return OperatingSystemFamily.AIX;
        case ARCH:
          return OperatingSystemFamily.ARCH;
        case CENTOS:
          return OperatingSystemFamily.CENTOS;
        case DARWIN:
          return OperatingSystemFamily.DARWIN;
        case DEBIAN:
          return OperatingSystemFamily.DEBIAN;
        case ESX:
          return OperatingSystemFamily.ESX;
        case FEDORA:
          return OperatingSystemFamily.FEDORA;
        case FREEBSD:
          return OperatingSystemFamily.FREEBSD;
        case GENTOO:
          return OperatingSystemFamily.GENTOO;
        case HPUX:
          return OperatingSystemFamily.HPUX;
        case COREOS:
          return OperatingSystemFamily.COREOS;
        case AMZN_LINUX:
          return OperatingSystemFamily.AMZN_LINUX;
        case MANDRIVA:
          return OperatingSystemFamily.MANDRIVA;
        case NETBSD:
          return OperatingSystemFamily.NETBSD;
        case OEL:
          return OperatingSystemFamily.OEL;
        case OPENBSD:
          return OperatingSystemFamily.OPENBSD;
        case RHEL:
          return OperatingSystemFamily.RHEL;
        case SCIENTIFIC:
          return OperatingSystemFamily.SCIENTIFIC;
        case CEL:
          return OperatingSystemFamily.CEL;
        case SLACKWARE:
          return OperatingSystemFamily.SLACKWARE;
        case SOLARIS:
          return OperatingSystemFamily.SOLARIS;
        case SUSE:
          return OperatingSystemFamily.SUSE;
        case TURBOLINUX:
          return OperatingSystemFamily.TURBOLINUX;
        case UBUNTU:
          return OperatingSystemFamily.UBUNTU;
        case CLOUD_LINUX:
          return OperatingSystemFamily.CLOUD_LINUX;
        case WINDOWS:
          return OperatingSystemFamily.WINDOWS;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("corrupt OS-Family " + operatingSystemFamily);
      }
    }

    @Override
    public CommonEntities.OperatingSystemFamily apply(OperatingSystemFamily operatingSystemFamily) {
      switch (operatingSystemFamily) {
        case UNKOWN_OS_FAMILY:
          return CommonEntities.OperatingSystemFamily.UNKOWN_OS_FAMILY;
        case AIX:
          return CommonEntities.OperatingSystemFamily.AIX;
        case ARCH:
          return CommonEntities.OperatingSystemFamily.ARCH;
        case CENTOS:
          return CommonEntities.OperatingSystemFamily.CENTOS;
        case DARWIN:
          return CommonEntities.OperatingSystemFamily.DARWIN;
        case DEBIAN:
          return CommonEntities.OperatingSystemFamily.DEBIAN;
        case ESX:
          return CommonEntities.OperatingSystemFamily.ESX;
        case FEDORA:
          return CommonEntities.OperatingSystemFamily.FEDORA;
        case FREEBSD:
          return CommonEntities.OperatingSystemFamily.FREEBSD;
        case GENTOO:
          return CommonEntities.OperatingSystemFamily.GENTOO;
        case HPUX:
          return CommonEntities.OperatingSystemFamily.GENTOO;
        case COREOS:
          return CommonEntities.OperatingSystemFamily.COREOS;
        case AMZN_LINUX:
          return CommonEntities.OperatingSystemFamily.AMZN_LINUX;
        case MANDRIVA:
          return CommonEntities.OperatingSystemFamily.MANDRIVA;
        case NETBSD:
          return CommonEntities.OperatingSystemFamily.NETBSD;
        case OEL:
          return CommonEntities.OperatingSystemFamily.OEL;
        case OPENBSD:
          return CommonEntities.OperatingSystemFamily.OPENBSD;
        case RHEL:
          return CommonEntities.OperatingSystemFamily.RHEL;
        case SCIENTIFIC:
          return CommonEntities.OperatingSystemFamily.SCIENTIFIC;
        case CEL:
          return CommonEntities.OperatingSystemFamily.CEL;
        case SLACKWARE:
          return CommonEntities.OperatingSystemFamily.SLACKWARE;
        case SOLARIS:
          return CommonEntities.OperatingSystemFamily.SOLARIS;
        case SUSE:
          return CommonEntities.OperatingSystemFamily.SUSE;
        case TURBOLINUX:
          return CommonEntities.OperatingSystemFamily.TURBOLINUX;
        case CLOUD_LINUX:
          return CommonEntities.OperatingSystemFamily.CLOUD_LINUX;
        case UBUNTU:
          return CommonEntities.OperatingSystemFamily.UBUNTU;
        case WINDOWS:
          return CommonEntities.OperatingSystemFamily.WINDOWS;
        default:
          throw new AssertionError("corrupt OS-Family " + operatingSystemFamily);
      }
    }
  }

}
