package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.IpAddressType;
import io.github.cloudiator.rest.model.IpVersion;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class IpAddressConverterTest {

    private final IpAddressConverter ipAddressConverter = new IpAddressConverter();
    //IP
    private final IpAddress restIpAddress;
    private final IaasEntities.IpAddress iaasIpAddress;

    public IpAddressConverterTest() {
        this.restIpAddress = new IpAddress()
                .ipAddressType(IpAddressType.PRIVATE_IP)
                .ipVersion(IpVersion.V4);
        this.iaasIpAddress = IaasEntities.IpAddress.newBuilder()
                .setType(IaasEntities.IpAddressType.PRIVATE_IP)
                .setVersion(IaasEntities.IpVersion.V4).build();
    }

    @Test
    public void applyBack() throws Exception {
        //from iaas to rest
        IpAddress result = ipAddressConverter.applyBack(iaasIpAddress);
        assertThat(result, is(equalTo(restIpAddress)));
    }

    @Test
    public void apply() throws Exception {
        //from rest to iaas
        IaasEntities.IpAddress actual = ipAddressConverter.apply(restIpAddress);
        assertThat(actual, is(equalTo(iaasIpAddress)));
    }

}