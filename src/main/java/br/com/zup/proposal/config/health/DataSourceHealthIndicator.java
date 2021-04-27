package br.com.zup.proposal.config.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component("database")
public class DataSourceHealthIndicator implements HealthIndicator {

    @Autowired
    private DataSource dataSource;

    @Override public Health health () {
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            statement.execute("select 1 from proposal");
        } catch (SQLException e) {
            return Health.outOfService().withException(e).build();
        }
        return Health.up().withDetail("description", "database service").build();
    }
}
