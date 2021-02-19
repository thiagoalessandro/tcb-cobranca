package br.com.totvs.tcb.cobranca.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile({"dev", "test"})
    public DataSource getDataSource(@Value("${spring.datasource.driverClassName}") String driverClassName,
                                    @Value("${spring.datasource.username}") String username,
                                    @Value("${spring.datasource.password}") String password,
                                    @Value("${spring.datasource.url}") String url) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    @Bean
    @Profile(value = {"prod", "default"})
    public DataSource jndiDataSource(@Value("${spring.datasource.jndi-name}") String jndiName) throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(jndiName);

        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
    }
}
