package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.OperatingSystem;
import io.github.cloudiator.rest.model.OperatingSystemArchitecture;
import io.github.cloudiator.rest.model.OperatingSystemFamily;
import org.cloudiator.messages.entities.CommonEntities;
import org.hamcrest.Matchers;
import org.junit.Test;

public class OperatingSystemConverterTest {

  private final OperatingSystemConverter operatingSystemConverter = new OperatingSystemConverter();
  //OperatingSystem
  private final OperatingSystem restOperatingSystem;
  private final CommonEntities.OperatingSystem iaasOperatingSystem;

  public OperatingSystemConverterTest() {
    this.restOperatingSystem = new OperatingSystem()
        .operatingSystemVersion("Test.0")
        .operatingSystemFamily(OperatingSystemFamily.UBUNTU)
        .operatingSystemArchitecture(OperatingSystemArchitecture.AMD64);
    this.iaasOperatingSystem = CommonEntities.OperatingSystem.newBuilder()
        .setOperatingSystemVersion("Test.0")
        .setOperatingSystemFamily(CommonEntities.OperatingSystemFamily.UBUNTU)
        .setOperatingSystemArchitecture(CommonEntities.OperatingSystemArchitecture.AMD64).build();
  }

  @Test
  public void applyBack() {
    //from iaas to rest
    OperatingSystem actual = operatingSystemConverter.applyBack(iaasOperatingSystem);
    assertThat(actual, Matchers.is(Matchers.equalTo(restOperatingSystem)));
  }

  @Test
  public void apply() {
    //from rest to iaas
    CommonEntities.OperatingSystem actual = operatingSystemConverter.apply(restOperatingSystem);
    assertThat(actual, Matchers.is(Matchers.equalTo(iaasOperatingSystem)));
  }

}
