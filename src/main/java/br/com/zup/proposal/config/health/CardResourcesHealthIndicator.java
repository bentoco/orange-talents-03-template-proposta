package br.com.zup.proposal.config.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component("cardResource")
public class CardResourcesHealthIndicator implements HealthIndicator {

    @Value ( value = "${host.card-resources}" )
    private String URI;
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
