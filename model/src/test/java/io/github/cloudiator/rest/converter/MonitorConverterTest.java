package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertEquals;

import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.DataSink.TypeEnum;
import io.github.cloudiator.rest.model.Monitor;
import io.github.cloudiator.rest.model.MonitoringTag;
import io.github.cloudiator.rest.model.MonitoringTarget;
import io.github.cloudiator.rest.model.PushSensor;
import io.github.cloudiator.rest.model.Sensor;
import java.util.Collections;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.SinkType;
import org.cloudiator.messages.entities.MonitorEntities.TargetType;
import org.junit.Test;

public class MonitorConverterTest {

  private final MonitorConverter monitorConverter = new MonitorConverter();
  private final Monitor restMonitor;
  private final MonitorEntities.Monitor kafkaMonitor;
  //Sensors
  private final Sensor restPushSensor;
  private final MonitorEntities.Sensor kafkaPushSensor;
  //Sinks
  private final DataSink restSink1;
  private final DataSink restSink2;
  private final MonitorEntities.Sink kafkaSink1;
  private final MonitorEntities.Sink kafkaSink2;
  //Tags
  private final MonitoringTag restTag1;
  private final MonitoringTag restTag2;
  private final MonitorEntities.MonitoringTag kafkaTag1;
  private final MonitorEntities.MonitoringTag kafkaTag2;
  //Targets
  private final MonitoringTarget restTarget1;
  private final MonitoringTarget restTarget2;
  private final MonitorEntities.MonitoringTarget kafkaTarget1;
  private final MonitorEntities.MonitoringTarget kafkaTarget2;

  public MonitorConverterTest() {

    //Sensor
    this.restPushSensor = new PushSensor().port(12345);
    this.restPushSensor.setType(restPushSensor.getClass().getSimpleName());
    this.kafkaPushSensor = MonitorEntities.Sensor.newBuilder()
        .setPushsensor(MonitorEntities.PushSensor.newBuilder().setPort(12345).build())
        .build();

    //Sinks
    this.restSink1 = new DataSink()
        .type(TypeEnum.INFLUX)
        ._configuration(Collections.singletonMap("DataSink1Key", "DataSink1Value"));
    this.restSink2 = new DataSink()
        .type(TypeEnum.KAIROS_DB)
        ._configuration(Collections.singletonMap("DataSink2Key", "DataSink2Value"));
    this.kafkaSink1 = MonitorEntities.Sink.newBuilder()
        .setType(SinkType.INFLUX)
        .putAllConfiguration(Collections.singletonMap("DataSink1Key", "DataSink1Value"))
        .build();
    this.kafkaSink2 = MonitorEntities.Sink.newBuilder()
        .setType(SinkType.KAIROS_DB)
        .putAllConfiguration(Collections.singletonMap("DataSink2Key", "DataSink2Value"))
        .build();
    //Tags
    this.restTag1 = new MonitoringTag().key("Tag1Key").value("Tag1Value");
    this.restTag2 = new MonitoringTag().key("Tag2Key").value("Tag2Value");
    this.kafkaTag1 = MonitorEntities.MonitoringTag.newBuilder()
        .setKey("Tag1Key").setValue("Tag1Value").build();
    this.kafkaTag2 = MonitorEntities.MonitoringTag.newBuilder()
        .setKey("Tag2Key").setValue("Tag2Value").build();

    //Targets
    this.restTarget1 = new MonitoringTarget()
        .type(MonitoringTarget.TypeEnum.CLOUD)
        .identifier("Target1Identifier");
    this.restTarget2 = new MonitoringTarget()
        .type(MonitoringTarget.TypeEnum.JOB)
        .identifier("Target2Identifier");
    this.kafkaTarget1 = MonitorEntities.MonitoringTarget.newBuilder()
        .setType(TargetType.CLOUD)
        .setIdentifier("Target1Identifier").build();
    this.kafkaTarget2 = MonitorEntities.MonitoringTarget.newBuilder()
        .setType(TargetType.JOB)
        .setIdentifier("Target2Identifier").build();

    //Monitor
    this.restMonitor = new Monitor()
        .metric("TestMetric")
        .sensor(restPushSensor)
        .addSinksItem(restSink1).addSinksItem(restSink2)
        .addTagsItem(restTag1).addTagsItem(restTag2)
        .addTargetsItem(restTarget1).addTargetsItem(restTarget2);
    this.kafkaMonitor = MonitorEntities.Monitor.newBuilder()
        .setMetric("TestMetric")
        .setSensor(kafkaPushSensor)
        .addDatasink(kafkaSink1).addDatasink(kafkaSink2)
        .addTags(kafkaTag1).addTags(kafkaTag2)
        .addTarget(kafkaTarget1).addTarget(kafkaTarget2).build();

  }


  @Test
  public void applyBack() {
    //form iaas to rest
    Monitor actual = monitorConverter.applyBack(kafkaMonitor);

    assertEquals(restMonitor, actual);
  }

  @Test
  public void apply() {
    MonitorEntities.Monitor actual = monitorConverter.apply(restMonitor);

    assertEquals(kafkaMonitor, actual);
  }
}
