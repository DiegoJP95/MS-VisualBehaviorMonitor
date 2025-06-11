package com.svitrtmnt.msvisualbehaviormonitor.repository;

import com.svitrtmnt.msvisualbehaviormonitor.model.VisualBehavior;
import com.svitrtmnt.msvisualbehaviormonitor.model.VisualEnvironment;
import com.svitrtmnt.msvisualbehaviormonitor.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VisualBehaviorRepositoryImpl implements VisualBehaviorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveVisualBehaviorData(VisualBehavior visualBehavior) {
        VisualEnvironment env = visualBehavior.getEnvironment();

        jdbcTemplate.update(Constants.INSERT_QUERY,
                visualBehavior.getTimeStamp(),
                visualBehavior.getUserId(),
                visualBehavior.getDeviceId(),
                visualBehavior.getBlinkFrecuency(),
                visualBehavior.getBlinkDurationMs(),
                visualBehavior.getScreenExposureMinutes(),
                visualBehavior.getFatigueLevel(),
                env.getAmbientLightLux(),
                env.getScreenBrightness(),
                env.getPosture());
    }

    @Override
    public Flux<VisualBehavior> getVisualBehaviorData(String userId) {
        return Flux.defer(() -> {
            List<VisualBehavior> datos = jdbcTemplate.query(Constants.SELECT_QUERY,
                    ps -> ps.setString(1, userId),
                    (rs, rowNum) -> {
                        return VisualBehavior.builder()
                                .timeStamp(rs.getString("timestamp"))
                                .userId(rs.getString("user_id"))
                                .deviceId(rs.getString("device_id"))
                                .blinkFrecuency(rs.getLong("blink_frecuency"))
                                .blinkDurationMs(rs.getDouble("blink_duration_ms"))
                                .screenExposureMinutes(rs.getDouble("screen_exposure_minutes"))
                                .fatigueLevel(rs.getString("fatigue_level"))
                                .environment(VisualEnvironment.builder()
                                        .ambientLightLux(rs.getInt("ambient_light_lux"))
                                        .screenBrightness(rs.getInt("screen_brightness"))
                                        .posture(rs.getString("posture"))
                                        .build())
                                .build();
                    });
            if (datos.isEmpty()) {
                log.warn("No se encontraron datos para el usuario con ID: {}", userId);
                return Flux.error(new NoSuchElementException("No existen datos para el usuario con ID: " + userId));
            }
            log.info("Total de registros obtenidos: {}", datos.size());
            return Flux.fromIterable(datos);
        });
    }
}
