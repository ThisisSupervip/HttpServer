package com.thisisvip.enums;

public enum Status {

    /**1xx: Information Response**/
    CONTINUE(100,"Continue"),
    SWITCHING_PROTOCOLS(101,"Switching Protocols"),
    PROCESSING(102,"Processing"),

    /**2xx: Success**/
    OK(200,"OK"),
    CREATED(201,"Created"),
    ACCEPTED(202,"Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203,"Non-Authoritative Information"),
    NO_CONTENT(204,"No Content"),
    RESET_CONTENT(205,"Reset Content"),
    PARTIAL_CONTENT(206,"Partial Content"),
    MULTI_STATUS(207,"Multi Status"),

    /**3xx: Redirection**/
    MULTIPLE_CHOICES(300,"Multiple Choices"),
    MOVED_PERMANENTLY(301,"Moved Permanently"),
    MOVED_TEMPORARILY(302,"Moved Temporarily"),
    SEE_OTHER(303,"See Other"),
    NOT_MODIFIED(304,"Not Modified"),
    USE_PROXY(305,"Use Proxy"),
    TEMPORARY_REDIRECT(307,"Temporary Redirect"),

    /**4xx: Client Error**/
    BAD_REQUEST(400,"Bad Request"),
    UNAUTHORIZED(401,"Unauthorized"),
    PAYMENT_REQUIRED(402,"Payment Required"),
    FORBIDDEN(403,"Forbidden"),
    NOT_FOUND(404,"Not Found"),
    METHOD_NOT_ALLOWED(405,"Method Not Allowed"),
    NOT_ACCEPTABLE(406,"Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407,"Proxy Authentication Required"),
    REQUEST_TIMEOUT(408,"Request Time-out"),
    CONFLICT(409,"Conflict"),
    GONE(410,"Gone"),
    LENGTH_REQUIRED(411,"Length Required"),
    PRECONDITION_FAILED(412,"Precondition Failed"),
    REQUEST_TOO_LONG(413,"Request Entity Too Large"),
    REQUEST_URI_TOO_LONG(414,"Request-URI Too Large"),
    UNSUPPORTED_MEDIA_TYPE(415,"Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416,"Requested range not satisfiable"),
    EXPECTATION_FAILED(417,"Expectation Failed"),
    INSUFFICIENT_SPACE_ON_RESOURCE(419,"Insufficient space on resource"),
    METHOD_FAILURE(420,"Method Failure"),
    UNPROCESSABLE_ENTITY(422,"Unprocessable Entity"),
    LOCKED(423,"Locked"),
    FAILED_DEPENDENCY(424,"Failed Dependency"),

    /**5xx: Server Error**/
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    NOT_IMPLEMENTED(501,"Not Implemented"),
    BAD_GATEWAY(502,"Bad Gateway"),
    SERVICE_UNAVAILABLE(503,"Service Unavailable"),
    GATEWAY_TIMEOUT(504,"Gateway Time-out"),
    HTTP_VERSION_NOT_SUPPORTED(505,"HTTP Version not supported"),
    INSUFFICIENT_STORAGE(507,"Insufficient Storage");

    private int statusCode;
    private String description;

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    Status(int statusCode, String description){
        this.statusCode = statusCode;
        this.description = description;
    }
}
