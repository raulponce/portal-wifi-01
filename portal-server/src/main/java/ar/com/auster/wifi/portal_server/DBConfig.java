package ar.com.auster.wifi.portal_server;

import ar.com.auster.wifi.portal_server.mapper.Tests;
import jakarta.annotation.Resource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@EnableTransactionManagement
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
//        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        return dataSource;
    }

    @Bean(name = "AuditJdbc")
    public JdbcTemplate jdbcAuditTemplate(@Qualifier("AuditDB") DataSource dataSource) {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        return jdbc;
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

    @Bean(name = "AuditTx")
    public PlatformTransactionManager txAuditManager(@Qualifier("AuditDB") DataSource dataSource) {
        DataSourceTransactionManager tx = new DataSourceTransactionManager(dataSource);
        tx.setRollbackOnCommitFailure(true);
        return tx;
    }

    @Bean(name = "DataDB")
    public DataSource dataSource(@Value("${db.data.url}") String url,
                                    @Value("${db.data.username}") String username,
                                    @Value("${db.data.password}") String password,
                                    @Value("${db.data.driver}") String driver) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);
//        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        return dataSource;
    }

    @Bean(name = "DataJdbc")
    public JdbcTemplate jdbcDataTemplate(@Qualifier("DataDB") DataSource dataSource) {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        return jdbc;
    }

    @Bean(name = "DataFlyway")
    public Flyway dataFlyway(@Qualifier("DataDB") DataSource dataSource) {
        FluentConfiguration flywayCfg = Flyway.configure();
        flywayCfg.dataSource(dataSource);
        flywayCfg.locations("classpath:db/migration/data");
        flywayCfg.baselineOnMigrate(true);
        Flyway flyway = flywayCfg.load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }

    @Bean(name = "DataTx")
    public PlatformTransactionManager txDataManager(@Qualifier("DataDB") DataSource dataSource) {
        DataSourceTransactionManager tx = new DataSourceTransactionManager(dataSource);
        tx.setRollbackOnCommitFailure(true);
        return tx;
    }

}
