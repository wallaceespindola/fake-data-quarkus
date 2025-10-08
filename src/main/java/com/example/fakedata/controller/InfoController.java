package com.example.fakedata.controller;

import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/actuator/info")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Info", description = "Application information endpoint (actuator-compatible)")
public class InfoController
{
   @ConfigProperty(name = "quarkus.application.name", defaultValue = "fake-data-quarkus")
   String appName;

   @ConfigProperty(name = "quarkus.application.version", defaultValue = "unknown")
   String appVersion;

   @GET
   @Operation(summary = "Get application info (version, name, etc.)")
   public Map<String, Object> info()
   {
      return Map.of(
            "app", Map.of(
                  "name", appName,
                  "version", appVersion,
                  "description", "Fake Data Generation Quarkus API using DataFaker and EasyRandom"
            ),
            "build", Map.of(
                  "version", appVersion,
                  "name", appName
            )
      );
   }
}

