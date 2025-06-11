package com.svitrtmnt.msvisualbehaviormonitor.expose.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class AmbientePeticion {

    @JsonProperty("ambientLightLux")
    private int iluminacionAmbiental;

    @JsonProperty("screenBrightness")
    private int brilloPantalla;

    @JsonProperty("posture")
    private String postura;
}
