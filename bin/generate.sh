<<<<<<< HEAD
#!/bin/bash

# download swagger codegen
wget https://oss.sonatype.org/content/repositories/releases/io/swagger/swagger-codegen-cli/2.2.2/swagger-codegen-cli-2.2.2.jar -O swagger-codegen-cli.jar

# generate
java -jar bin/swagger-codegen-cli.jar generate -i swagger/swagger.yaml -l jaxrs
=======
#!/bin/sh

java -jar bin/swagger-codegen-cli.jar generate -i https://raw.githubusercontent.com/cloudiator/rest-swagger/master/swagger.yaml -l spring --artifact-id rest-server --group-id io.github.cloudiator --artifact-version 0.3.0-SNAPSHOT --api-package io.github.cloudiator.rest.api --model-package io.github.cloudiator.rest.model
>>>>>>> a698c736d9cb31c33bea8c5a62a8b2792cbc58ea
