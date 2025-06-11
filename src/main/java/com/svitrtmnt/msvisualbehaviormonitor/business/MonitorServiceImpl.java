package com.svitrtmnt.msvisualbehaviormonitor.business;

import com.svitrtmnt.msvisualbehaviormonitor.expose.request.CapturaDatosPeticion;
import com.svitrtmnt.msvisualbehaviormonitor.expose.response.CapturaDatosRespuesta;
import com.svitrtmnt.msvisualbehaviormonitor.model.VisualBehavior;
import com.svitrtmnt.msvisualbehaviormonitor.model.VisualEnvironment;
import com.svitrtmnt.msvisualbehaviormonitor.repository.VisualBehaviorRepository;
import java.time.format.DateTimeParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitorServiceImpl implements MonitorService {

    private final VisualBehaviorRepository repository;

    @Override
    public Mono<CapturaDatosRespuesta> capturaDatos(CapturaDatosPeticion peticion) {
        try {
            log.info("Registrando datos con petición: {}", peticion.toString());
            VisualBehavior behavior = mapToBehavior(peticion);
            repository.saveVisualBehaviorData(behavior);

            return Mono.just(CapturaDatosRespuesta.builder()
                    .estado("OK")
                    .mensaje("Datos registrados correctamente")
                    .build()
            );
        } catch (DateTimeParseException e) {
            log.error("Error al convertir timestamp: {}", peticion.getTimeStamp(), e);
            return Mono.error(new RuntimeException("Formato de fecha inválido."));
        } catch (Exception e) {
            log.error("Error al guardar datos de comportamiento visual", e);
            return Mono.error(new RuntimeException("Error interno al registrar los datos."));
        }
    }

    @Override
    public Flux<VisualBehavior> obtencionDatos(String userId) {
        try {
            log.info("Obteniendo datos del usuario: {}", userId);
            return repository.getVisualBehaviorData(userId);

        } catch (Exception e) {
            log.error("Error al consultar los datos de comportamiento visual", e);
            return Flux.error(new RuntimeException("Error interno al consultar los datos."));
        }
    }

    private VisualBehavior mapToBehavior(CapturaDatosPeticion req) {
        return VisualBehavior.builder()
                .timeStamp(req.getTimeStamp())
                .userId(req.getUsuarioId())
                .deviceId(req.getDispositivoId())
                .blinkFrecuency(req.getFrecuenciaParpadeo())
                .blinkDurationMs(req.getDuracionParpadeoMs())
                .screenExposureMinutes(req.getMinutosExposicionPantalla())
                .fatigueLevel(req.getNivelFatiga())
                .environment(
                    VisualEnvironment.builder()
                        .ambientLightLux(req.getAmbiente().getIluminacionAmbiental())
                        .screenBrightness(req.getAmbiente().getBrilloPantalla())
                        .posture(req.getAmbiente().getPostura())
                        .build()
                )
                .build();
    }


}
