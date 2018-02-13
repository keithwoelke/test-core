package com.github.keithwoelke.test.core.http;

@SuppressWarnings("unused")
public enum HttpHeader {
    X_POWERED_BY("x-powered-by");

    private final String headerKey;

    HttpHeader(String headerKey) {
        this.headerKey = headerKey;
    }

    public String headerKey() {
        return headerKey;
    }
}
