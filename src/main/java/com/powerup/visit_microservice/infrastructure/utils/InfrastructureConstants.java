package com.powerup.visit_microservice.infrastructure.utils;

public class InfrastructureConstants {

    private InfrastructureConstants() {
    }

    //Statues http
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";

    //JWT
    public static final String TOKEN_INVALID =  "Token is invalid";
    public static final String ROLE_CLAIM = "role";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String SUBJECT_CLAIM = "sub";
    public static final String JWT_MISSING_OR_INVALID = "JWT token is missing or invalid";


    //Visit schedule controller
    public static final String TAG_NAME = "Visit Schedule";
    public static final String TAG_DESCRIPTION = "Operations related to visit schedules";
    public static final String CREATE_VISIT_SCHEDULE_DESCRIPTION = "Create a new visit schedule";
    public static final String VISIT_SCHEDULES_BASE_PATH = "/visit-schedules";
    public static final String CREATE_VISIT_SCHEDULE_SUMMARY = "Create a new visit schedule";
    public static final String RESPONSE_201_DESCRIPTION = "Visit schedule created successfully";
    public static final String RESPONSE_400_DESCRIPTION = "Invalid input data";


    //Feign client House
    public static final String HOUSE_RESPONSE_BODY_NULL = "House existence response body is null.";
    public static final String HOUSE_FETCH_FAILED = "Failed to fetch house existence by ID.";
    public static final String HOUSE_MICROSERVICE_NAME = "house-microservice";
    public static final String HOUSE_MICROSERVICE_URL = "localhost:8020";
    public static final String HOUSE_EXISTS_ENDPOINT = "/real-estates/exists";
    public static final String HOUSE_ID_PARAM = "houseId";



    // User Feign Client
    public static final String USER_RESPONSE_BODY_NULL = "User ID response body is null.";
    public static final String USER_FETCH_FAILED = "Failed to fetch user ID by email.";
    public static final String USER_MICROSERVICE_NAME = "user-microservice";
    public static final String USER_MICROSERVICE_URL = "localhost:8080";
    public static final String USER_GET_ID_BY_EMAIL_ENDPOINT = "/user/get-id-by-email";
    public static final String USER_EMAIL_PARAM = "email";




}
