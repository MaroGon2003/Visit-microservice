package com.powerup.visit_microservice.infrastructure.utils;

public class InfrastructureConstants {

    private InfrastructureConstants() {
    }

    //Statues http
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_500 = "500";
    public static final String RESPONSE_CODE_200 = "200";

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
    public static final String RESPONSE_CODE_200_DESCRIPTION = "List of visit schedules retrieved successfully";
    public static final String RESPONSE_CODE_500_DESCRIPTION = "Internal server error";
    public static final String GET_VISIT_SCHEDULES_SUMMARY = "Get visit schedules";
    public static final String GET_VISIT_SCHEDULES_DESCRIPTION = "Retrieve a paginated list of visit schedules";
    public static final String RESPONSE_CODE_400_DESCRIPTION = "Invalid request parameters";
    public static final String VISIT_SCHEDULES_REQUEST = "/request";
    public static final String CREATE_VISIT_REQUEST_SUMMARY = "Create a visit request";
    public static final String CREATE_VISIT_REQUEST_DESCRIPTION = "Create a visit request for a specific visit schedule";
    public static final String RESPONSE_201_VISIT_REQUEST_CREATED = "Visit request created successfully";
    public static final String RESPONSE_400_INVALID_INPUT = "Invalid input data";
    public static final String RESPONSE_500_INTERNAL_SERVER_ERROR = "Internal server error";




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


    // Default values
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    //Pagination
    public static final String SORT_DIRECTION_ASC = "ASC";

    //JPA constants
    public static final String SORT_BY_START_DATE_TIME = "startDateTime";
    public static final String SORT_DIRECTION_DESC = "DESC";

    // Visit schedule request
    public static final String MAX_VISIT_SCHEDULE_CAPACITY = "2";


}
