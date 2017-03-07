#!/bin/bash

# download swagger codegen
wget https://oss.sonatype.org/content/repositories/releases/io/swagger/swagger-codegen-cli/2.2.2/swagger-codegen-cli-2.2.2.jar -O swagger-codegen-cli.jar

# generate
java -jar bin/swagger-codegen-cli.jar generate -i swagger/swagger.yaml -l jaxrs
