package br.com.zup.proposal.config.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Component("cardResource")
public class CardResourcesHealthIndicator implements HealthIndicator {

    private static final String URI = "http://localhost:8888/api/cartoes";
    private final Logger logger = LoggerFactory.getLogger(CardResourcesHealthIndicator.class);

    @Override public Health health () {
        Map<String, Object> details = new HashMap<>();
        details.put("url" , URI);
        details.put("description" , "external card service");

        try (Socket socket = new Socket(new URL(URI).getHost() , 8888)) {
        } catch (Exception e) {
            logger.warn("Failed to connect to: {}" , URI);
            return Health.down()
                    .withDetail("error" , e.getMessage())
                    .build();
        }
        return Health.up().withDetails(details).build();
    }
}
