package com.example.fakedata.config;

import jakarta.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
      info = @Info(
            title = "Fake Data API",
            version = "1.0.0",
            description = "Demo API using Datafaker + Easy Random",
            license = @License(
                  name = "Apache 2.0",
                  url = "https://www.apache.org/licenses/LICENSE-2.0.html"
            )
      )
)
public class OpenApiConfig extends Application
{
}
