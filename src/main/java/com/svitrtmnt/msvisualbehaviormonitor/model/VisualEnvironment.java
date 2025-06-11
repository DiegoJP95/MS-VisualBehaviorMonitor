package com.svitrtmnt.msvisualbehaviormonitor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisualEnvironment {

    private int ambientLightLux;

    private int screenBrightness;

    private String posture;
}
