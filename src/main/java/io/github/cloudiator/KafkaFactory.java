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

  private static final String CONFIG_FILE_LOCATION_KEY = "kafka.config.file";
  private static final String DEFAULT_CONFIG_FILE_NAME = "kafka.properties";

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaFactory.class);

  public Kafka createKafka() {
    
    try {
      LOGGER.info("creating kafka: loading properties");
      Properties properties = PropertiesLoader.loadPropertiesWithException(CONFIG_FILE_LOCATION_KEY, DEFAULT_CONFIG_FILE_NAME);
      return new Kafka(properties.getProperty("bootstrap.servers"),
          properties.getProperty("group.id"));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

  }
}
