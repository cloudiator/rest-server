package io.github.cloudiator;

import de.uniulm.omi.cloudiator.util.PropertiesLoader;
import org.cloudiator.messaging.kafka.Kafka;

import java.io.IOException;
import java.util.Properties;

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
