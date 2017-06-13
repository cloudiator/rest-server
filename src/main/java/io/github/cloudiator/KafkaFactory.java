package io.github.cloudiator;

import de.uniulm.omi.cloudiator.util.PropertiesLoader;
import java.io.IOException;
import java.util.Properties;
import org.cloudiator.messaging.kafka.Kafka;

/**
 * Created by daniel on 13.06.17.
 */
public class KafkaFactory {

  public Kafka createKafka() {
    try {
      final Properties properties = PropertiesLoader.loadPropertiesFrom("kafka.properties");
      return new Kafka(properties.getProperty("bootstrap.servers"),
          properties.getProperty("group.id"));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
