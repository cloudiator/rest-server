package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Image;
import io.github.cloudiator.rest.model.OperatingSystem;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class ImageConverter implements TwoWayConverter<Image, IaasEntities.Image> {

  OperatingSystemConverter operatingSystemConverter = new OperatingSystemConverter();


  @Override
  public Image applyBack(IaasEntities.Image image) {
    Image result = new Image();

    result.setId(image.getId());
    result.setName(image.getName());
    result.setProviderId(image.getProviderId());
    result.setOperatingSystem(operatingSystemConverter.applyBack(image.getOperationSystem()));
    return result;
  }

  @Override
  public IaasEntities.Image apply(Image image) {
    IaasEntities.Image.Builder builder = IaasEntities.Image.newBuilder();

    builder.setId(image.getId());
    builder.setName(image.getName());
    builder.setProviderId(image.getProviderId());
    builder.setOperationSystem(operatingSystemConverter.apply(image.getOperatingSystem()));

    return builder.build();
  }

  private class OperatingSystemConverter implements
      TwoWayConverter<OperatingSystem, CommonEntities.OperatingSystem> {

    @Override
    public OperatingSystem applyBack(CommonEntities.OperatingSystem operatingSystem) {
      OperatingSystem op = new OperatingSystem();
      op.setOperatingSystemVersion(operatingSystem.getOperatingSystemVersion());
      op.setOperatingSystemFamily(operatingSystem.getOperatingSystemFamily());
      op.setOperatingSystemArchitecture(operatingSystem.getOperatingSystemArchitecture());
      return op;
    }

    @Override
    public CommonEntities.OperatingSystem apply(OperatingSystem operatingSystem) {
      CommonEntities.OperatingSystem.Builder builder = CommonEntities.OperatingSystem.newBuilder();
      if (operatingSystem.getOperatingSystemArchitecture() != null) {
        builder.setOperatingSystemArchitecture(operatingSystem.getOperatingSystemArchitecture());
      } else {
        builder.clearOperatingSystemArchitecture();
      }
      if (operatingSystem.getOperatingSystemFamily() != null) {
        builder.setOperatingSystemFamily(operatingSystem.getOperatingSystemFamily());
      } else {
        builder.clearOperatingSystemFamily();
      }
      if (operatingSystem.getOperatingSystemVersion() != null) {
        builder.setOperatingSystemVersion(operatingSystem.getOperatingSystemVersion());
      } else {
        builder.clearOperatingSystemVersion();
      }

      return builder.build();
    }

  }
}
