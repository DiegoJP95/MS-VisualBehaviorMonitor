package com.svitrtmnt.msvisualbehaviormonitor.expose;

import com.svitrtmnt.msvisualbehaviormonitor.business.MonitorService;
import com.svitrtmnt.msvisualbehaviormonitor.expose.request.CapturaDatosPeticion;
import com.svitrtmnt.msvisualbehaviormonitor.expose.response.CapturaDatosErrorRespuesta;
import com.svitrtmnt.msvisualbehaviormonitor.expose.response.CapturaDatosRespuesta;
import com.svitrtmnt.msvisualbehaviormonitor.model.VisualBehavior;
import com.svitrtmnt.msvisualbehaviormonitor.util.Constants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequiredArgsConstructor
public class MonitorController {

    private final MonitorService monitorService;
    private static final Logger log = LoggerFactory.getLogger(MonitorController.class);

    @PostMapping(value = "/capture", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Captura de datos", description ="Captura de datos de conducta visual",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Captura de datos de conducta visual",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CapturaDatosRespuesta.class)
                )),
            @ApiResponse(
                responseCode = "400",
                description = "Error en la captura de datos",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CapturaDatosErrorRespuesta.class)
                )),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CapturaDatosErrorRespuesta.class)
                ))
            }
        )
    public Mono<CapturaDatosRespuesta> dataCapture(
        @NotEmpty(message = Constants.X_API_KEY_NOT_EMPTY_MESSAGE)
        @NotNull(message = Constants.X_API_KEY_NOT_NULL)
        @Pattern(
                regexp = Constants.REGEX_UUID,
                message = Constants.X_API_KEY_PATTERN_MESSAGE
        )
        @RequestHeader(value = "x-api-key") String xapikey,

        @NotEmpty(message = Constants.X_USER_ID_NOT_EMPTY_MESSAGE)
        @NotNull(message = Constants.X_USER_ID_NOT_NULL)
        @Pattern(
                regexp = Constants.REGEX_UUID,
                message = Constants.X_USER_ID_PATTERN_MESSAGE
        )
        @RequestHeader(value = "x-user-id") String xuserid,

        @NotEmpty(message = Constants.X_USER_ROLE_NOT_EMPTY_MESSAGE)
        @NotNull(message = Constants.X_USER_ROLE_NOT_NULL)
        @Pattern(
                regexp = Constants.REGEX_ROLE,
                message = Constants.X_USER_ROLE_PATTERN_MESSAGE)
        @RequestHeader(value = "x-user-role") String xuserrole,

        @NotEmpty(message = Constants.X_DEVICE_ID_NOT_EMPTY_MESSAGE)
        @NotNull(message = Constants.X_DEVICE_ID_NOT_NULL)
        @Pattern(
                regexp = Constants.REGEX_DEVICE,
                message = Constants.X_DEVICE_ID_PATTERN_MESSAGE)
        @RequestHeader(value = "x-device-id") String xdeviceid,
        @Valid @RequestBody(required = true) CapturaDatosPeticion request
    ){
        log.info("Captura de datos de conducta visual");
        return monitorService.capturaDatos(request);
    }

    @GetMapping(value = "/patient/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtención de datos", description ="Obtención de datos del paciente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Obtención de datos de conducta visual",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CapturaDatosRespuesta.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error en la obtención de datos",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CapturaDatosErrorRespuesta.class)
                            )),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CapturaDatosErrorRespuesta.class)
                            ))
            }
    )
    public Flux<VisualBehavior> getPatientData(
            @NotEmpty(message = Constants.X_API_KEY_NOT_EMPTY_MESSAGE)
            @NotNull(message = Constants.X_API_KEY_NOT_NULL)
            @Pattern(
                    regexp = Constants.REGEX_UUID,
                    message = Constants.X_API_KEY_PATTERN_MESSAGE
            )
            @RequestHeader(value = "x-api-key") String xapikey,

            @NotEmpty(message = Constants.X_USER_ID_NOT_EMPTY_MESSAGE)
            @NotNull(message = Constants.X_USER_ID_NOT_NULL)
            @Pattern(
                    regexp = Constants.REGEX_UUID,
                    message = Constants.X_USER_ID_PATTERN_MESSAGE
            )
            @RequestHeader(value = "x-user-id") String xuserid,

            @NotEmpty(message = Constants.X_USER_ROLE_NOT_EMPTY_MESSAGE)
            @NotNull(message = Constants.X_USER_ROLE_NOT_NULL)
            @Pattern(
                    regexp = Constants.REGEX_ROLE,
                    message = Constants.X_USER_ROLE_PATTERN_MESSAGE)
            @RequestHeader(value = "x-user-role") String xuserrole,

            @NotEmpty(message = Constants.X_DEVICE_ID_NOT_EMPTY_MESSAGE)
            @NotNull(message = Constants.X_DEVICE_ID_NOT_NULL)
            @Pattern(
                    regexp = Constants.REGEX_DEVICE,
                    message = Constants.X_DEVICE_ID_PATTERN_MESSAGE)
            @RequestHeader(value = "x-device-id") String xdeviceid,
            @PathVariable(value = Constants.URL_PATH_USER_ID) @Valid String userId
    ){
        log.info("Obtención de datos del paciente");
        return monitorService.obtencionDatos(userId);
    }

}
