package com.github.keithwoelke.test.core.restassured.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings({"unused", "WeakerAccess"})
public class RequestSpec {

    public static RequestSpecification emptyRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        return builder.build();
    }

    public static RequestSpecification genericJsonRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setAccept(ContentType.JSON);
        builder.setContentType(ContentType.JSON);

        return builder.build();
    }

    public static RequestSpecification genericXmlRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setAccept(ContentType.JSON);
        builder.setContentType(ContentType.XML);

        return builder.build();
    }
}
