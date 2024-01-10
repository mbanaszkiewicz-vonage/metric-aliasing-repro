package server;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@SpringBootApplication
public class ServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceApplication.class, args);
  }
}

@RestController
class SampleController {
  @GetMapping("/test")
  Mono<Map<String, Object>> testEndpoint() {
    return Mono.just(Map.of());
  }
}

@Configuration
class MetricsConfig {
  @Bean
  LoggingMeterRegistry loggingMeterRegistry() {
    return new LoggingMeterRegistry(new LoggingRegistryConfig() {
      @Override
      public Duration step() {
        return Duration.ofSeconds(10); // log every 10 seconds
      }

      @Override
      public String get(String key) {
        return null;
      }
    }, Clock.SYSTEM);
  }
}