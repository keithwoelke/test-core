package com.github.keithwoelke.test.core.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("defaultEnvironment")
@ToString
@EqualsAndHashCode
@Getter
public class Environment {

    private final String environmentName;
    private final String baseUrl;

    public Environment(@Value("${environment.name}") String environmentName,
                       @Value("${environment.baseUrl}") String baseUrl
    ) {
        this.environmentName = environmentName;
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl(String urlPath) {
        return baseUrl + urlPath;
    }
}
