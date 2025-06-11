package com.svitrtmnt.msvisualbehaviormonitor.expose.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class CapturaDatosPeticion implements Serializable {

    @Serial
    private static final long serialVersionUID = 9043163891451793486L;

    @JsonProperty("timestamp")
    private String timeStamp;

    @JsonProperty("userId")
    private String usuarioId;

    @JsonProperty("deviceId")
    private String dispositivoId;

    @JsonProperty("blinkFrequency")
    private Long frecuenciaParpadeo;

    @JsonProperty("blinkDurationMs")
    private Double duracionParpadeoMs;

    @JsonProperty("screenExposureMinutes")
    private Double minutosExposicionPantalla;

    @JsonProperty("fatigueLevel")
    private String nivelFatiga;

    @JsonProperty("environment")
    private AmbientePeticion ambiente;

}
