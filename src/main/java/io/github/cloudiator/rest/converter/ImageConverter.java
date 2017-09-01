package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Image;
import io.github.cloudiator.rest.model.OperatingSystem;
import org.cloudiator.messages.entities.CommonEntities;
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

        if ((image.getId() != null) || (!image.getId().isEmpty())) {
            builder.setId(image.getId());
        } else {
            throw new AssertionError("ImageId is null or empty");
        }
        if ((image.getName() != null) || (!image.getName().isEmpty())) {
            builder.setName(image.getName());
        } else {
            throw new AssertionError("ImageName is null or empty");
        }
        if ((image.getProviderId() != null) || (!image.getProviderId().isEmpty())) {
            builder.setProviderId(image.getProviderId());
        } else {
            throw new AssertionError("ImageProviderId is null or empty");
        }
        if (image.getOperatingSystem() != null) {
            builder.setOperationSystem(operatingSystemConverter.apply(image.getOperatingSystem()));
        } else {
            throw new AssertionError("ImageOperatingSystem is null");
        }
        if (image.getLocation() != null) {
            builder.setLocation(locationConverter.apply(image.getLocation()));
        } else {
            builder.clearLocation();
        }

        return builder.build();
    }

}
