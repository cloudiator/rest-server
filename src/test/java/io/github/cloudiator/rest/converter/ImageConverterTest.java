package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ImageConverterTest {

    private final ImageConverter imageConverter = new ImageConverter();
    private final Image restImage;
    private final IaasEntities.Image iaasImage;
    //Operatingsystem
    private final OperatingSystem restOperatingSystem;
    private final CommonEntities.OperatingSystem iaasOperatingSystem;
    //Location
    private final IaasEntities.Location iaasLocation;
    private final Location restLocation;

    public ImageConverterTest() {

        //OperatingSystem
        this.iaasOperatingSystem = CommonEntities.OperatingSystem.newBuilder()
                .setOperatingSystemArchitecture(CommonEntities.OperatingSystemArchitecture.AMD64)
                .setOperatingSystemFamily(CommonEntities.OperatingSystemFamily.UBUNTU)
                .setOperatingSystemType(CommonEntities.OperatingSystemType.UNIX)
                .setOperatingSystemVersion("Test.0").build();
        this.restOperatingSystem = new OperatingSystem()
                .operatingSystemArchitecture(OperatingSystemArchitecture.AMD64)
                .operatingSystemFamily(OperatingSystemFamily.UBUNTU)
                .operatingSystemType(OperatingSystemType.UNIX)
                .operatingSystemVersion("Test.0");
        //Location
        this.iaasLocation = IaasEntities.Location.newBuilder()
                .setId("32chars-long_testID_for_UnitTest")
                .setName("TestName")
                .setProviderId("TestProvider")
                .setLocationScope(CommonEntities.LocationScope.PROVIDER)
                .setIsAssignable(true).clearParent().build();
        this.restLocation = new Location()
                .id("32chars-long_testID_for_UnitTest")
                .name("TestName")
                .providerId("TestProvider")
                .locationScope(Location.LocationScopeEnum.PROVIDER)
                .isAssignable(true);
        //Image
        this.iaasImage = IaasEntities.Image.newBuilder()
                .setId("32chars-long_testID_for_UnitTest")
                .setName("TestName")
                .setOperationSystem(iaasOperatingSystem)
                .setProviderId("TestProvider")
                .setLocation(iaasLocation)
                .build();
        this.restImage = new Image()
                .id("32chars-long_testID_for_UnitTest")
                .name("TestName")
                .operatingSystem(restOperatingSystem)
                .providerId("TestProvider")
                .location(restLocation);
    }


    @Test
    public void applyBack() throws Exception {
        //from iaas to rest
        Image actual = imageConverter.applyBack(iaasImage);
        assertThat(actual, is(equalTo(restImage)));
    }

    @Test
    public void apply() throws Exception {
        IaasEntities.Image actual = imageConverter.apply(restImage);
        assertThat(actual, is(equalTo(iaasImage)));
    }

}