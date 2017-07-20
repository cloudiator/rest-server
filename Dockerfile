FROM java:openjdk-8-jdk-alpine

WORKDIR /data
RUN apk --update add gettext
ADD target/rest-server-0.3.0-SNAPSHOT.jar .

ADD entry.sh .
ENTRYPOINT ["./entry.sh"]

