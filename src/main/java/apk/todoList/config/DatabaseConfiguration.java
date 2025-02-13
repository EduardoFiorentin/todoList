package apk.todoList.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
public class DatabaseConfiguration {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;


    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        // opcionais
        config.setMaximumPoolSize(10);  // maximo de conexões liberadas
        config.setMinimumIdle(1);        // minimo de conexões liberadas
        config.setPoolName("library-db-pool"); //
        config.setMaxLifetime(600000); // tempo max de conex. em ms
        config.setConnectionTimeout(100000);
        //config.setConnectionTestQuery("select 1"); // testa resposta do banco

        return new HikariDataSource(config);
    }
}
