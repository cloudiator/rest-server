package io.github.cloudiator.rest.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of OS Family
 */
public enum OperatingSystemFamily {
  
  UBUNTU("UBUNTU"),
  
  UNKOWN_OS_FAMILY("UNKOWN_OS_FAMILY"),
  
  AIX("AIX"),
  
  ARCH("ARCH"),
  
  CENTOS("CENTOS"),
  
  DARWIN("DARWIN"),
  
  DEBIAN("DEBIAN"),
  
  ESX("ESX"),
  
  FEDORA("FEDORA"),
  
  FREEBSD("FREEBSD"),
  
  GENTOO("GENTOO"),
  
  HPUX("HPUX"),
  
  COREOS("COREOS"),
  
  AMZN_LINUX("AMZN_LINUX"),
  
  MANDRIVA("MANDRIVA"),
  
  NETBSD("NETBSD"),
  
  OEL("OEL"),
  
  OPENBSD("OPENBSD"),
  
  RHEL("RHEL"),
  
  SCIENTIFIC("SCIENTIFIC"),
  
  CEL("CEL"),
  
  SLACKWARE("SLACKWARE"),
  
  SOLARIS("SOLARIS"),
  
  SUSE("SUSE"),
  
  TURBOLINUX("TURBOLINUX"),
  
  CLOUD_LINUX("CLOUD_LINUX"),
  
  WINDOWS("WINDOWS");

  private String value;

  OperatingSystemFamily(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperatingSystemFamily fromValue(String text) {
    for (OperatingSystemFamily b : OperatingSystemFamily.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

