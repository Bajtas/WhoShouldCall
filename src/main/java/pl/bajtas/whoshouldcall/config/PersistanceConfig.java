package pl.bajtas.whoshouldcall.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Configuration
@EnableTransactionManagement
public class PersistanceConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test3;DATABASE_TO_UPPER=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("pl.bajtas.whoshouldcall.model");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        //jpaProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        //jpaProperties.put("hibernate.physical_naming_strategy", "pl.bajtas.whoshouldring.util.PhysicalNamingStrategyImpl");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }
}
