package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Image;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class ImageConverter implements TwoWayConverter<Image, IaasEntities.Image> {

  private final OperatingSystemConverter operatingSystemConverter = new OperatingSystemConverter();
  private final LocationConverter locationConverter = new LocationConverter();

  @Override

  public Image applyBack(IaasEntities.Image image) {
    Image result = new Image();

    result.setId(image.getId());
    result.setName(image.getName());
    result.setProviderId(image.getProviderId());
    result.setOperatingSystem(operatingSystemConverter.applyBack(image.getOperationSystem()));
    result.setLocation(locationConverter.applyBack(image.getLocation()));

    return result;
  }

  @Override
  public IaasEntities.Image apply(Image image) {
    IaasEntities.Image.Builder builder = IaasEntities.Image.newBuilder();

    builder.setId(image.getId());
    builder.setName(image.getName());
    builder.setProviderId(image.getProviderId());
    builder.setOperationSystem(operatingSystemConverter.apply(image.getOperatingSystem()));

    if (image.getLocation() != null) {
      builder.setLocation(locationConverter.apply(image.getLocation()));
    }

    return builder.build();
  }

}
