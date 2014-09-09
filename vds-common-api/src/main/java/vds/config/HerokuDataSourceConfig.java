package vds.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ConditionalOnProperty("database.url")
public class HerokuDataSourceConfig {
    @Value("${database.url}")
    private String uri;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(uri);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

        DataSourceBuilder factory = DataSourceBuilder
                .create()
                .url(dbUrl)
                .username(username)
                .password(password);
        return factory.build();
    }
}
