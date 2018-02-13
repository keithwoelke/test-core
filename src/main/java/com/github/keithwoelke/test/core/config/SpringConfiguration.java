package com.github.keithwoelke.test.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
@SuppressWarnings("unused")
@ComponentScan(basePackages = "com.github.keithwoelke.test.core")
@Configuration
public class SpringConfiguration {

    private static final String DEFAULT_ENVIRONMENT = "default";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(ApplicationContext applicationContext) {
        org.springframework.core.env.Environment environment = applicationContext.getBean("environment", org.springframework.core.env.Environment
                .class);

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        String[] activeProfiles = environment.getActiveProfiles();
        List<Resource> resources = Lists.newArrayList();

        resources.add(new ClassPathResource(String.format("environments/%s.properties", DEFAULT_ENVIRONMENT)));

        for (String activeProfile : activeProfiles) {
            resources.add(new ClassPathResource(String.format("environments/%s.properties", activeProfile)));
        }

        propertySourcesPlaceholderConfigurer.setLocations(resources.toArray(new Resource[0]));

        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
