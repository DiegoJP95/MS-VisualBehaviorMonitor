package com.svitrtmnt.msvisualbehaviormonitor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisualBehavior {

    private String timeStamp;

    private String userId;

    private String deviceId;

    private Long blinkFrecuency;

    private Double blinkDurationMs;

    private Double screenExposureMinutes;

    private String fatigueLevel;

    private VisualEnvironment environment;

}
