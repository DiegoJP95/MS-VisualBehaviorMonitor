package com.svitrtmnt.msvisualbehaviormonitor.business;

import com.svitrtmnt.msvisualbehaviormonitor.expose.request.CapturaDatosPeticion;
import com.svitrtmnt.msvisualbehaviormonitor.expose.response.CapturaDatosRespuesta;
import com.svitrtmnt.msvisualbehaviormonitor.model.VisualBehavior;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonitorService {

    Mono<CapturaDatosRespuesta> capturaDatos(CapturaDatosPeticion capturaDatosPeticion);

    Flux<VisualBehavior> obtencionDatos(String userId);

}
