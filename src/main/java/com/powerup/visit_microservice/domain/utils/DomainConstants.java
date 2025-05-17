package com.powerup.visit_microservice.domain.utils;

public class DomainConstants {

    private DomainConstants() {
        // Prevent instantiation
    }

    // Constants for Visit
    public static final String VISIT_ALREADY_SCHEDULED_FOR_HOUSE = "A seller already has this house assigned for a visit.";
    public static final String START_DATE_AFTER_END_DATE = "The start date cannot be after the end date.";
    public static final String START_DATE_EQUALS_END_DATE = "The start date cannot be equal to the end date.";
    public static final String SCHEDULE_OUT_OF_RANGE = "The start and end dates must be within the next 3 weeks.";
    public static final String VISIT_ALREADY_SCHEDULED_FOR_DATE_RANGE = "There is already a scheduled visit in this time range.";
    public static final String HOUSE_NOT_FOUND = "House not found.";
    public static final String USER_NOT_FOUND = "User not found.";

    //Pagination
    public static final int MIN_PAGE_INDEX = 0;
    public static final int MIN_PAGE_SIZE = 1;
    public static final String PAGE_INDEX_NEGATIVE_ERROR = "Page index must not be negative";
    public static final String PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR = "Page size must be greater than zero";


}
