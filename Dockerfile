FROM openjdk:8-jre-alpine

WORKDIR /data

EXPOSE 9000

ADD server/target/rest-server-0.3.0-SNAPSHOT.jar .
ADD entry.sh .

ENTRYPOINT ["./entry.sh"]
