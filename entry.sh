#!/bin/sh

set -x

echo ${KAFKA_BOOTSTRAP_SERVERS_IP:?"KAFKA_BOOTSTRAP_SERVERS_IP has to be set"}
echo ${KAFKA_GROUP_ID:?"KAFKA_GROUP_ID has to be set"}
echo ${MESSAGING_TIMEOUT:?"MESSAGING_TIMEOUT has to be set"}
echo ${APPLICATION_DOCS_PATH:?"APPLICATION_DOCS_PATH has to be set"}
echo ${APPLICATION_PORT:?"APPLICATION_PORT has to be set"}

# Extract kafta config
jar xf rest-server-0.3.0-SNAPSHOT.jar BOOT-INF/classes/kafka.properties.template

# Substitute
cat BOOT-INF/classes/kafka.properties.template | envsubst > BOOT-INF/classes/kafka.properties
cat BOOT-INF/classes/messaging.properties.template | envsubst > BOOT-INF/classes/messaging.properties
cat BOOT-INF/classes/application.properties.template | envsubst > BOOT-INF/classes/application.properties
cat BOOT-INF/classes/kafka.properties
cat BOOT-INF/classes/application.properties
cat BOOT-INF/classes/messaging.properties

# Write config back
#jar uf rest-server-0.3.0-SNAPSHOT.jar BOOT-INF/classes/kafka.properties

# Cleanup
#rm -rf BOOT-INF

# Run the service
java -Dspring.config.location=file://BOOT-INF/classes/application.properties -Dkafka.config.file=BOOT-INF/classes/kafka.properties -Dmessaging.config.file=BOOT-INF/classes/messaging.properties -jar rest-server-0.3.0-SNAPSHOT.jar 

