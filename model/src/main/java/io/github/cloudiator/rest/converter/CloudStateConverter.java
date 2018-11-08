package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.Cloud.StateEnum;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.IaasEntities.CloudState;

public class CloudStateConverter implements
    TwoWayConverter<IaasEntities.CloudState, Cloud.StateEnum> {

  @Override
  public CloudState applyBack(StateEnum stateEnum) {
    switch (stateEnum) {
      case ERROR:
        return CloudState.CLOUD_STATE_ERROR;
      case OK:
        return CloudState.CLOUD_STATE_OK;
      default:
        throw new AssertionError("Unexpected cloud state " + stateEnum);
    }
  }

  @Override
  public StateEnum apply(CloudState cloudState) {
    switch (cloudState) {

      case CLOUD_STATE_ERROR:
        return StateEnum.ERROR;
      case CLOUD_STATE_OK:
        return StateEnum.OK;
      case CLOUD_STATE_DELETED:
      case UNRECOGNIZED:
      case CLOUD_STATE_NEW:
      default:
        throw new AssertionError("Unexpected or illegal cloud state " + cloudState);
    }
  }
}
