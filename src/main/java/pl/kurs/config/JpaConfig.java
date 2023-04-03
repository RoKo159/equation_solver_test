package pl.kurs.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Profile({"dev", "prod", "!dev & !prod"})
    @Bean
    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter adapter, DataSource ds) {  // ten EMF nie wykorzystuje już persistence.xml
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.kurs");
        emf.setDataSource(ds);
        return emf;
    }

    @Profile({"prod", "!dev & !prod"})
    @Bean
    public JpaVendorAdapter createJpaVendorAdapterProd() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true); // to umożliwa tworzenie tabel na podstawie encji
        return adapter;
    }

    @Profile({"prod", "!dev & !prod"})
    @Bean
    public DataSource createDataSourceProd() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/calculator_app?useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setInitialSize(5);
        return ds;
    }


    @Profile("dev")
    @Bean
    public JpaVendorAdapter createJpaVendorAdapterDev() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true); // to umożliwa tworzenie tabel na podstawie encji
        return adapter;
    }

    @Profile("dev")
    @Bean
    public DataSource createDataSourceDev() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:h2:mem:testdb");
        ds.setUsername("SA");
        ds.setPassword("");
        ds.setDriverClassName("org.h2.Driver");
        ds.setInitialSize(5);
        return ds;
    }
}
