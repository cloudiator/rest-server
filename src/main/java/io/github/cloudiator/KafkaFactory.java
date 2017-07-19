package io.github.cloudiator;

import de.uniulm.omi.cloudiator.util.PropertiesLoader;
import java.io.IOException;
import java.util.Properties;
import org.cloudiator.messaging.kafka.Kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by daniel on 13.06.17.
 */
public class KafkaFactory {

  private static String CONFIG_FILE_LOCATION = System.getProperty("kafkaConfigFile");
  private static final String DEFAULT_CONFIG_FILE_LOCATION = "kafka.properties";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(KafkaFactory.class);

  public Kafka createKafka() {

    if (CONFIG_FILE_LOCATION == null) {
      CONFIG_FILE_LOCATION = DEFAULT_CONFIG_FILE_LOCATION;
      LOGGER.warn("Could not read system property. Falling back to default.");
    }
    LOGGER.debug("Trying to open kafkaConfigFile from " + CONFIG_FILE_LOCATION);

    try {
      Properties properties = PropertiesLoader.loadPropertiesFrom(CONFIG_FILE_LOCATION);
      return new Kafka(properties.getProperty("bootstrap.servers"),
          properties.getProperty("group.id"));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

  }
}
