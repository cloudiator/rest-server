package io.github.cloudiator.rest.converter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.Hardware;
import io.github.cloudiator.rest.model.Image;
import io.github.cloudiator.rest.model.Location;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.MatchmakingEntities;
import org.junit.Test;

public class NodeCandidateConverterTest {

  private final NodeCandidateConverter nodeCandidateConverter = new NodeCandidateConverter();
  private final CloudToCloudConverterTest c2cConverterTest = new CloudToCloudConverterTest();
  private final ImageConverterTest imageConverterTest = new ImageConverterTest();
  private final HardwareConverterTest hwConverterTest = new HardwareConverterTest();
  private final LocationConverterTest locationConverterTest = new LocationConverterTest();

  //rest NodeCandidate
  private final NodeCandidate restNodeCandidate;
  private final Cloud restCloud;
  private final Image restImage;
  private final Hardware restHardware;
  private final Location restLocationNoParent;

  //iaas NodeCandidate

  private final MatchmakingEntities.NodeCandidate iaasNodeCandidate;
  private final IaasEntities.Cloud iaasCloud;
  private final IaasEntities.Image iaasImage;
  private final IaasEntities.HardwareFlavor iaasHardware;
  private final IaasEntities.Location iaasLocationNoParent;

  private final String id = "testId";


  public NodeCandidateConverterTest() {
    //rest
    this.restCloud = c2cConverterTest.restCloud;
    this.restImage = imageConverterTest.restImage;
    this.restHardware = hwConverterTest.restHardware;
    this.restLocationNoParent = locationConverterTest.restParentLocation;
    this.restNodeCandidate = new NodeCandidate()
        .nodeCandidateType(NodeCandidate.NodeCandidateTypeEnum.IAAS)
        .id(id)
        .cloud(restCloud)
        .image(restImage)
        .hardware(restHardware)
        .location(restLocationNoParent)
        .price(123.4);
    //iaas
    this.iaasCloud = c2cConverterTest.iaasCloud;
    this.iaasImage = imageConverterTest.iaasImage;
    this.iaasHardware = hwConverterTest.iaasHardware;
    this.iaasLocationNoParent = locationConverterTest.iaasParentLocation;
    this.iaasNodeCandidate = MatchmakingEntities.NodeCandidate.newBuilder()
        .setType(MatchmakingEntities.NodeCandidateType.NC_IAAS)
        .setId(id)
        .setCloud(iaasCloud)
        .setImage(iaasImage)
        .setHardwareFlavor(iaasHardware)
        .setLocation(iaasLocationNoParent)
        .setPrice(123.4)
        .build();
  }

  @Test
  public void applyBack() throws Exception {
    //iaas to rest
    NodeCandidate actual = nodeCandidateConverter.applyBack(iaasNodeCandidate);

    assertThat(actual, is(equalTo(restNodeCandidate)));
  }

  @Test
  public void apply() throws Exception {
    //rest to iaas
    MatchmakingEntities.NodeCandidate actual = nodeCandidateConverter.apply(restNodeCandidate);

    assertThat(actual, is(equalTo(iaasNodeCandidate)));
  }

}