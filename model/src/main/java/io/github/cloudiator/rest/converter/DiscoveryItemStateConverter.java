package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DiscoveryItemState;
import org.cloudiator.messages.entities.IaasEntities;

public class DiscoveryItemStateConverter implements
    TwoWayConverter<IaasEntities.DiscoveryItemState, DiscoveryItemState> {

  public static final DiscoveryItemStateConverter INSTANCE = new DiscoveryItemStateConverter();

  private DiscoveryItemStateConverter() {
  }

  @Override
  public IaasEntities.DiscoveryItemState applyBack(DiscoveryItemState discoveryItemState) {

    switch (discoveryItemState) {
      case OK:
        return IaasEntities.DiscoveryItemState.DISCOVERY_OK;
      case NEW:
        return IaasEntities.DiscoveryItemState.DISCOVERY_NEW;
      case UNKNOWN:
        return IaasEntities.DiscoveryItemState.DISCOVERY_UNKNOWN;
      case DISABLED:
        return IaasEntities.DiscoveryItemState.DISCOVERY_DISABLED;
      case LOCALLY_DELETED:
        return IaasEntities.DiscoveryItemState.DISCOVERY_LOCALLY_DELETED;
      case REMOTELY_DELETED:
        return IaasEntities.DiscoveryItemState.DISCOVERY_REMOTELY_DELETED;
      default:
        throw new AssertionError("Unknown state " + discoveryItemState);
    }
  }

  @Override
  public DiscoveryItemState apply(IaasEntities.DiscoveryItemState discoveryItemState) {
    switch (discoveryItemState) {
      case DISCOVERY_OK:
        return DiscoveryItemState.OK;
      case DISCOVERY_NEW:
        return DiscoveryItemState.NEW;
      case DISCOVERY_UNKNOWN:
        return DiscoveryItemState.UNKNOWN;
      case DISCOVERY_DISABLED:
        return DiscoveryItemState.DISABLED;
      case DISCOVERY_LOCALLY_DELETED:
        return DiscoveryItemState.LOCALLY_DELETED;
      case DISCOVERY_REMOTELY_DELETED:
        return DiscoveryItemState.REMOTELY_DELETED;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("Unknown state " + discoveryItemState);
    }
  }
}
