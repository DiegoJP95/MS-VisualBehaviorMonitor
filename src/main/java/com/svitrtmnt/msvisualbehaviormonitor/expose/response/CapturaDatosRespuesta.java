package com.svitrtmnt.msvisualbehaviormonitor.expose.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class CapturaDatosRespuesta implements Serializable {

    @Serial
    private static final long serialVersionUID = -7163800962368314349L;

    @JsonProperty("status")
    private String estado;

    @JsonProperty("message")
    private String mensaje;

}
