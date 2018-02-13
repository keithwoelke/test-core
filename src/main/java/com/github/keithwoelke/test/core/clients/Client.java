package com.github.keithwoelke.test.core.clients;

import com.google.common.collect.Maps;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Client {

    private final String baseUrl;

    public Client(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl(String urlPath) {
        return baseUrl + urlPath;
    }

    protected Response request(String requestUrl, RequestSpecification requestSpecification, String requestMethod) {
        return request(requestUrl, null, requestSpecification, requestMethod);
    }

    protected Response request(String requestUrl, @SuppressWarnings("SameParameterValue") Map<String, String> queryParams, RequestSpecification requestSpecification, String requestMethod) {
        if (queryParams == null) {
            queryParams = Maps.newHashMap();
        }

        ByteArrayOutputStream requestBaos = new ByteArrayOutputStream();
        ByteArrayOutputStream responseBaos = new ByteArrayOutputStream();

        Response response;
        try {
            response = given(requestSpecification).
                    filter(new RequestLoggingFilter(new PrintStream(requestBaos))).
                    filter(new ResponseLoggingFilter(new PrintStream(responseBaos))).
                    queryParams(queryParams).
                    request(requestMethod, getUrl(requestUrl));
        } finally {
            log.debug(String.format("Request:%n%s", requestBaos));
            log.debug(String.format("Response:%n%s", responseBaos));
        }

        return response;
    }
}
