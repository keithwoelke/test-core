package com.github.keithwoelke.test.core.restassured.spec;

import com.github.keithwoelke.test.core.http.RequestMethod;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Method;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ResponseSpec {

    public static ResponseSpecification allowHeaderResponseSpec(List<String> supportedRequestMethods) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        List<String> unsupportedRequestMethods = Arrays.stream(RequestMethod.values()).
                map(Enum::name).
                filter(method -> !supportedRequestMethods.contains(method)).
                collect(Collectors.toList());

        for (String requestMethod : supportedRequestMethods) {
            builder.expectHeader(HttpHeaders.ALLOW, containsString(requestMethod));
        }

        for (String requestMethod : unsupportedRequestMethods) {
            builder.expectHeader(HttpHeaders.ALLOW, not(containsString(requestMethod)));
        }

        return builder.build();
    }

    public static ResponseSpecification getMethodAllowHeaderResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectHeader(HttpHeaders.ALLOW, containsString(Method.GET.name()));
        builder.expectHeader(HttpHeaders.ALLOW, containsString(Method.HEAD.name()));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.POST.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.PUT.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.PATCH.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.DELETE.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(RequestMethod.TRACE.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(RequestMethod.CONNECT.name())));
        builder.expectHeader(HttpHeaders.ALLOW, containsString(Method.OPTIONS.name()));

        return builder.build();
    }

    public static ResponseSpecification httpStatus1xxInformationalResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectStatusCode(both(greaterThanOrEqualTo(100)).and(lessThanOrEqualTo(199)));

        return builder.build();
    }

    public static ResponseSpecification httpStatus200ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_OK);

        return builder.build();
    }

    public static ResponseSpecification genericResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

//        TODO: Uncomment these once there is enough traction on these security bugs. Otherwise, almost every test will fail.
//        builder.expectHeader(HttpHeader.X_POWERED_BY.name(), nullValue(String.class));
//        builder.expectHeader(HttpHeaders.SERVER, nullValue(String.class));
//        builder.expectBody(allOf(not(containsString("Server")), not(containsString("Apache")), not(containsString("Ubuntu")), not(containsString("Jetty"))));

        return builder.build();
    }

    public static ResponseSpecification httpStatus201ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_CREATED);

        return builder.build();
    }

    public static ResponseSpecification httpStatus412ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_PRECONDITION_FAILED);

        return builder.build();
    }

    public static ResponseSpecification httpStatus2xxSuccessResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectStatusCode(both(greaterThanOrEqualTo(200)).and(lessThanOrEqualTo(299)));

        return builder.build();
    }

    public static ResponseSpecification httpStatus3xxRedirectionResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectStatusCode(both(greaterThanOrEqualTo(300)).and(lessThanOrEqualTo(399)));

        return builder.build();
    }

    public static ResponseSpecification httpStatus400ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_BAD_REQUEST);
        return builder.build();
    }

    public static ResponseSpecification httpStatus401ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_UNAUTHORIZED);
        builder.expectHeader(HttpHeaders.WWW_AUTHENTICATE, notNullValue(String.class));

        return builder.build();
    }

    public static ResponseSpecification httpStatus404ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_NOT_FOUND);

        return builder.build();
    }

    public static ResponseSpecification httpStatus405ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);

        return builder.build();
    }

    public static ResponseSpecification httpStatus415ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
        builder.expectHeader(HttpHeaders.ACCEPT_ENCODING, notNullValue(String.class));

        return builder.build();
    }

    public static ResponseSpecification httpStatus4xxClientErrorResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectStatusCode(both(greaterThanOrEqualTo(400)).and(lessThanOrEqualTo(499)));

        return builder.build();
    }

    public static ResponseSpecification httpStatus501ResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.addResponseSpecification(genericResponseSpec());
        builder.expectStatusCode(HttpStatus.SC_NOT_IMPLEMENTED);

        return builder.build();
    }

    public static ResponseSpecification httpStatus5xxServerErrorResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectStatusCode(both(greaterThanOrEqualTo(500)).and(lessThanOrEqualTo(599)));

        return builder.build();
    }

    public static ResponseSpecification postMethodAllowHeaderResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder.expectHeader(HttpHeaders.ALLOW, not(Method.GET.name()));
        builder.expectHeader(HttpHeaders.ALLOW, not(Method.HEAD.name()));
        builder.expectHeader(HttpHeaders.ALLOW, containsString(Method.POST.name()));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.PUT.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.PATCH.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(Method.DELETE.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(RequestMethod.TRACE.name())));
        builder.expectHeader(HttpHeaders.ALLOW, not(containsString(RequestMethod.CONNECT.name())));
        builder.expectHeader(HttpHeaders.ALLOW, containsString(Method.OPTIONS.name()));

        return builder.build();
    }

    public static String getCombinedRootAndPath(String root, String path) {
        String combined;

        if (StringUtils.isEmpty(root)) {
            combined = path;
        } else {
            combined = String.format("%s.%s", root, path);
        }

        return combined;
    }
}
