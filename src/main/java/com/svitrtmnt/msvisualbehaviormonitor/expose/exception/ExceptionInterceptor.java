package com.svitrtmnt.msvisualbehaviormonitor.expose.exception;

import com.svitrtmnt.msvisualbehaviormonitor.expose.response.CapturaDatosErrorRespuesta;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionInterceptor {

    @ExceptionHandler(value = {ServerWebInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CapturaDatosErrorRespuesta handleWebInputException(ServerWebInputException exception) {
        String timeStamp = LocalDateTime.now().toString();
        String errorCode = "Error de campo requerido";
        String errorMessage = "Bad Request: " + exception.getReason();
        logErrorMessage(errorMessage);
        return CapturaDatosErrorRespuesta.builder()
                .timeStamp(timeStamp)
                .error(errorCode)
                .mensaje(errorMessage)
                .build();
    }

    @ExceptionHandler(value = {WebExchangeBindException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public CapturaDatosErrorRespuesta handleWebExchangeBindException(WebExchangeBindException exception) {
        String timeStamp = LocalDateTime.now().toString();
        String errorCode = "Error de validación";
        String errorMessage = "Validation Error: " + exception.getFieldErrors().toString();
        logErrorMessage(errorMessage);
        return CapturaDatosErrorRespuesta.builder()
                .timeStamp(timeStamp)
                .error(errorCode)
                .mensaje(errorMessage)
                .build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CapturaDatosErrorRespuesta handleException(Exception exception) {
        String timeStamp = LocalDateTime.now().toString();
        String errorCode = "Error en el formato de los datos";
        String errorMessage = "Error de validación : " + exception.getMessage();
        logErrorMessage(errorMessage);
        return CapturaDatosErrorRespuesta.builder()
                .timeStamp(timeStamp)
                .error(errorCode)
                .mensaje(errorMessage)
                .build();
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CapturaDatosErrorRespuesta noElementException(Exception exception) {
        String timeStamp = LocalDateTime.now().toString();
        String errorCode = "Error de datos no encontrados";
        String errorMessage = exception.getMessage();
        logErrorMessage(errorMessage);
        return CapturaDatosErrorRespuesta.builder()
                .timeStamp(timeStamp)
                .error(errorCode)
                .mensaje(errorMessage)
                .build();
    }

    private void logErrorMessage(String errorMessage) {
        log.error("Error Message: {}", errorMessage);
    }
}
