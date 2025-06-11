package com.svitrtmnt.msvisualbehaviormonitor.util;

public class Constants {

    public static final String X_API_KEY = "X-Api-Key";
    public static final String X_USER_ID = "X-User-Id";
    public static final String X_USER_ROLE = "X-User-Role";
    public static final String X_DEVICE_ID = "X-Device-Id";
    public static final String URL_PATH_USER_ID = "userId";

    public static final String X_API_KEY_NOT_EMPTY_MESSAGE = "X-Api-Key no puede estar vacío";
    public static final String X_USER_ID_NOT_EMPTY_MESSAGE = "X-User-Id no puede estar vacío";
    public static final String X_USER_ROLE_NOT_EMPTY_MESSAGE = "X-User-Role no puede estar vacío";
    public static final String X_DEVICE_ID_NOT_EMPTY_MESSAGE = "X-Device-Id no puede estar vacío";

    public static final String X_API_KEY_NOT_NULL = "X-Api-Key no puede ser nulo";
    public static final String X_USER_ID_NOT_NULL = "X-User-Id no puede ser nulo";
    public static final String X_USER_ROLE_NOT_NULL = "X-User-Role no puede ser nulo";
    public static final String X_DEVICE_ID_NOT_NULL = "X-Device-Id no puede ser nulo";

    public static final String REGEX_UUID = "^[a-zA-Z0-9-]{36}$";
    public static final String REGEX_ROLE = "^(USER|ADMIN)$";
    public static final String REGEX_DEVICE = "^[A-Z]{4}-[A-Z0-9]{3}$";
    public static final String PATTERN_UUID_MESSAGE = "El formato debe ser de tipo UUID. Por ejemplo: ba209999-0c6c-11d2-97cf-00c04f8eea45";
    public static final String PATTERN_ROLE_MESSAGE = "Debe ser 'ADMIN' o 'USER'.";
    public static final String INVALID_FORMAT_MESSAGE = " no cumple con el formato esperado: ";
    public static final String PATTERN_DEVICE_MESSAGE = "Debe ser de la forma XXXX-YYY, donde Y es un carácter alfanumérico.";

    public static final String X_API_KEY_PATTERN_MESSAGE = X_API_KEY + INVALID_FORMAT_MESSAGE + PATTERN_UUID_MESSAGE;
    public static final String X_USER_ID_PATTERN_MESSAGE = X_USER_ID + INVALID_FORMAT_MESSAGE + PATTERN_UUID_MESSAGE;
    public static final String X_USER_ROLE_PATTERN_MESSAGE = X_USER_ROLE + INVALID_FORMAT_MESSAGE + PATTERN_ROLE_MESSAGE;
    public static final String X_DEVICE_ID_PATTERN_MESSAGE = X_DEVICE_ID + INVALID_FORMAT_MESSAGE + PATTERN_DEVICE_MESSAGE;

    public static final String INSERT_QUERY = "INSERT INTO visual_behavior ("
            + " timestamp, user_id, device_id, blink_frecuency,"
            + " blink_duration_ms, screen_exposure_minutes,"
            + " fatigue_level, ambient_light_lux, screen_brightness, posture"
            + " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_QUERY = "SELECT "
            + " timestamp, user_id, device_id, blink_frecuency,"
            + " blink_duration_ms, screen_exposure_minutes,"
            + " fatigue_level, ambient_light_lux, screen_brightness, posture"
            + " FROM visual_behavior WHERE user_id = ?";
}
