package pl.bajtas.whoshouldring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;


/**
 * Created by Bajtas on 18.03.2017.
 */
@Configuration
public class UserDetailsConfig {
    @Autowired
    DataSource dataSource;

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource);
        jdbcImpl.setUsersByUsernameQuery("SELECT login, password, true FROM buser WHERE login=?");
        jdbcImpl.setAuthoritiesByUsernameQuery("SELECT u.login, r.name FROM brole r, buser u WHERE u.login=? AND u.b_role=r.id");

        return jdbcImpl;
    }
}
