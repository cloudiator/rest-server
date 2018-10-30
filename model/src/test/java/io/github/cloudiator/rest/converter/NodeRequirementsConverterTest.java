package io.github.cloudiator.rest.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.model.AttributeRequirement;
import io.github.cloudiator.rest.model.IdentifierRequirement;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.github.cloudiator.rest.model.OclRequirement;
import io.github.cloudiator.rest.model.RequirementOperator;
import java.io.IOException;
import org.cloudiator.messages.NodeEntities;
import org.cloudiator.messages.entities.CommonEntities;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class NodeRequirementsConverterTest {

  private final NodeRequirementsConverter nodeRequirementsConverter = new NodeRequirementsConverter();
  public final NodeRequirements restNodeReq;
  public final NodeEntities.NodeRequirements iaasNodeReq;

  //rest
  public final OclRequirement restOclReq;
  public final IdentifierRequirement restIdReq;
  public final AttributeRequirement restAttrReq;

  //iaas
  public final CommonEntities.Requirement iaasOclReq;
  public final CommonEntities.Requirement iaasIdReq;
  public final CommonEntities.Requirement iaasAttrReq;

  public NodeRequirementsConverterTest() {
    this.restOclReq = new OclRequirement()
        .constraint("TestConstraint");
    this.restOclReq.setType(restOclReq.getClass().getSimpleName());
    this.restIdReq = new IdentifierRequirement()
        .hardwareId("TestHardwareID")
        .locationId("TestLocationID")
        .imageId("TestImageID");
    this.restIdReq.setType(restIdReq.getClass().getSimpleName());
    this.restAttrReq = new AttributeRequirement()
        .requirementClass("TestRequirementClass")
        .requirementAttribute("TestRequirementAttribute")
        .requirementOperator(RequirementOperator.EQ)
        .value("TestValue");
    this.restAttrReq.setType(restAttrReq.getClass().getSimpleName());

    this.iaasOclReq = CommonEntities.Requirement.newBuilder()
        .setOclRequirement(CommonEntities.OclRequirement.newBuilder()
            .setConstraint("TestConstraint").build())
        .build();
    this.iaasIdReq = CommonEntities.Requirement.newBuilder()
        .setIdRequirement(CommonEntities.IdRequirement.newBuilder()
            .setHardwareId("TestHardwareID")
            .setLocationId("TestLocationID")
            .setImageId("TestImageID")
            .build())
        .build();
    this.iaasAttrReq = CommonEntities.Requirement.newBuilder()
        .setAttributeRequirement(CommonEntities.AttributeRequirement.newBuilder()
            .setRequirementClass("TestRequirementClass")
            .setRequirementAttribute("TestRequirementAttribute")
            .setRequirementOperator(CommonEntities.RequirementOperator.EQ)
            .setValue("TestValue").build())
        .build();

    this.restNodeReq = new NodeRequirements()
        .addRequirementsItem(restOclReq)
        .addRequirementsItem(restIdReq)
        .addRequirementsItem(restAttrReq);
    this.iaasNodeReq = NodeEntities.NodeRequirements.newBuilder()
        .addRequirements(iaasOclReq)
        .addRequirements(iaasIdReq)
        .addRequirements(iaasAttrReq).build();
  }

  @Test
  public void apply() {
    //from rest to iaas
    NodeEntities.NodeRequirements actual = nodeRequirementsConverter.apply(restNodeReq);

    ObjectMapper mapper = new ObjectMapper();
    try {
      String jsoninString = mapper.writeValueAsString(restNodeReq);
      System.out.println(jsoninString);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(actual);

    assertThat(actual, Matchers.is(Matchers.equalTo(iaasNodeReq)));
  }
}
