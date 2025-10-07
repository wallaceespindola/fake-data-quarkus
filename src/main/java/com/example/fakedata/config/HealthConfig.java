package com.example.fakedata.config;

import java.time.Instant;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class HealthConfig implements HealthCheck
{
   @Override
   public HealthCheckResponse call()
   {
      return HealthCheckResponse.builder()
            .name("timestamp")
            .up()
            .withData("timestamp", Instant.now().toString())
            .build();
   }
}
