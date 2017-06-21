#!/bin/sh

java -jar swagger-codegen-cli.jar generate -i https://raw.githubusercontent.com/cloudiator/rest-swagger/master/swagger.yaml -l spring --artifact-id rest-server --group-id io.github.cloudiator --artifact-version 0.3.0-SNAPSHOT --api-package io.github.cloudiator.rest.api --model-package io.github.cloudiator.rest.model -DhideGenerationTimestamp=true
