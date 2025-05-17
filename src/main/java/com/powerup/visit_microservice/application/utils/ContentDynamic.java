package com.powerup.visit_microservice.application.utils;

import com.powerup.visit_microservice.application.dto.response.VisitScheduleResponseDto;

import java.util.HashMap;
import java.util.Map;

public class ContentDynamic {

    private ContentDynamic() {
        // Private constructor to prevent instantiation
    }

    private static final Map<Class<?>, String> typeToPropertyNameMap = new HashMap<>();

    static {
        typeToPropertyNameMap.put(VisitScheduleResponseDto.class, ApplicationConstants.VISIT_SCHEDULES);
    }

    public static String getPropertyName(Class<?> clazz) {
        return typeToPropertyNameMap.getOrDefault(clazz, ApplicationConstants.DEFAULT_CONTENT);
    }
}

