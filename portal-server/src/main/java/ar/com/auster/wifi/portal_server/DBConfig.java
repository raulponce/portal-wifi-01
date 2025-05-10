package ar.com.auster.wifi.portal_server;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean(name = "AuditDB")
    public DataSource auditDataSource(@Value("${db.audit.url}") String url,
                                      @Value("${db.audit.username}") String username,
                                      @Value("${db.audit.password}") String password,
                                      @Value("${db.audit.driver}") String driver) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

    @Bean(name = "AuditFlyway")
    public Flyway auditFlyway(@Qualifier("AuditDB") DataSource dataSource) {
        FluentConfiguration flywayCfg = Flyway.configure();
        flywayCfg.dataSource(dataSource);
        flywayCfg.locations("classpath:db/migration/audit");
        flywayCfg.baselineOnMigrate(true);
        Flyway flyway = flywayCfg.load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }

    @Bean(name = "DataDB")
    public DataSource bioDataSource(@Value("${db.data.url}") String url,
                                    @Value("${db.data.username}") String username,
                                    @Value("${db.data.password}") String password,
                                    @Value("${db.data.driver}") String driver) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

    @Bean(name = "DataFlyway")
    public Flyway biodataFlyway(@Qualifier("DataDB") DataSource dataSource) {
        FluentConfiguration flywayCfg = Flyway.configure();
        flywayCfg.dataSource(dataSource);
        flywayCfg.locations("classpath:db/migration/data");
        flywayCfg.baselineOnMigrate(true);
        Flyway flyway = flywayCfg.load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }

}
