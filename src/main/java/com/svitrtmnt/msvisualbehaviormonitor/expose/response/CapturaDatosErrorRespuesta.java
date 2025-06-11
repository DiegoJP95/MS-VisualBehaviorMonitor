package com.svitrtmnt.msvisualbehaviormonitor.expose.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class CapturaDatosErrorRespuesta implements Serializable {

    @Serial
    private static final long serialVersionUID = -7933623160339116545L;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String mensaje;
}
