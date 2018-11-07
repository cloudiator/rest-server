#!/bin/sh

java -jar bin/swagger-codegen-cli.jar generate -i https://raw.githubusercontent.com/cloudiator/rest-swagger/master/swagger.yaml -l spring --output server --artifact-id rest-server --group-id io.github.cloudiator --artifact-version 0.3.0-SNAPSHOT --api-package io.github.cloudiator.rest.api --model-package io.github.cloudiator.rest.model -DhideGenerationTimestamp=true

# move the model
mv server/src/main/java/io/github/cloudiator/rest/model/* model/src/main/java/io/github/cloudiator/rest/model/

# Delete the model
rm -rf server/src/main/java/io/github/cloudiator/rest/model



