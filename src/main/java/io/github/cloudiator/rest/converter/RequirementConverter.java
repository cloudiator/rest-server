package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.AttributeRequirement;
import io.github.cloudiator.rest.model.IdentifierRequirement;
import io.github.cloudiator.rest.model.OclRequirement;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.RequirementOperator;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.CommonEntities.IdRequirement;

public class RequirementConverter implements
    TwoWayConverter<Requirement, CommonEntities.Requirement> {

  @Override
  public CommonEntities.Requirement apply(Requirement requirement) {

    if (requirement instanceof IdentifierRequirement) {
      return CommonEntities.Requirement.newBuilder()
          .setIdRequirement(
              IdRequirement.newBuilder()
                  .setImageId(((IdentifierRequirement) requirement).getImageId())
                  .setLocationId(((IdentifierRequirement) requirement).getLocationId())
                  .setHardwareId(((IdentifierRequirement) requirement).getHardwareId())
                  .build()
          ).build();
    } else if (requirement instanceof OclRequirement) {
      return CommonEntities.Requirement.newBuilder().setOclRequirement(
          CommonEntities.OclRequirement.newBuilder()
              .setConstraint(((OclRequirement) requirement).getConstraint()).build()).build();
    } else if (requirement instanceof AttributeRequirement) {
      return CommonEntities.Requirement.newBuilder().setAttributeRequirement(
          CommonEntities.AttributeRequirement.newBuilder().setRequirementAttribute(
              ((AttributeRequirement) requirement).getRequirementAttribute())
              .setRequirementClass(((AttributeRequirement) requirement).getRequirementClass())
              .setRequirementOperator(CommonEntities.RequirementOperator
                  .valueOf(((AttributeRequirement) requirement).getRequirementOperator().name()))
              .setValue(((AttributeRequirement) requirement).getValue()).build()).build();
    } else {
      throw new AssertionError(
          String.format("Unknown requirement type %s.", requirement.getClass()));
    }
  }

  @Override
  public Requirement applyBack(CommonEntities.Requirement requirement) {
    switch (requirement.getRequirementCase()) {
      case REQUIREMENT_NOT_SET:
        throw new IllegalStateException("Requirement not set.");
      case OCLREQUIREMENT:
        OclRequirement oclRequirement = new OclRequirement();
        oclRequirement.setConstraint(requirement.getOclRequirement().getConstraint());
        oclRequirement.setType(oclRequirement.getClass().getSimpleName());
        return oclRequirement;
      case IDREQUIREMENT:
        IdentifierRequirement identifierRequirement = new IdentifierRequirement();
        identifierRequirement.setHardwareId(requirement.getIdRequirement().getHardwareId());
        identifierRequirement.setImageId(requirement.getIdRequirement().getImageId());
        identifierRequirement.setLocationId(requirement.getIdRequirement().getLocationId());
        identifierRequirement.setType(identifierRequirement.getClass().getSimpleName());
        return identifierRequirement;
      case ATTRIBUTEREQUIREMENT:
        AttributeRequirement attributeRequirement = new AttributeRequirement();
        attributeRequirement.setRequirementAttribute(
            requirement.getAttributeRequirement().getRequirementAttribute());
        attributeRequirement
            .setRequirementClass(requirement.getAttributeRequirement().getRequirementClass());
        attributeRequirement.setRequirementOperator(RequirementOperator
            .valueOf(requirement.getAttributeRequirement().getRequirementOperator().name()));
        attributeRequirement.setValue(requirement.getAttributeRequirement().getValue());
        return attributeRequirement;
      default:
        throw new AssertionError(
            String.format("RequirementCase %s is unknown", requirement.getRequirementCase()));
    }
  }
}
