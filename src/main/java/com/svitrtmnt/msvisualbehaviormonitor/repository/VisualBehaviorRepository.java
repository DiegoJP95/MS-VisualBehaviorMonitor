package com.svitrtmnt.msvisualbehaviormonitor.repository;

import com.svitrtmnt.msvisualbehaviormonitor.model.VisualBehavior;
import reactor.core.publisher.Flux;

import java.util.List;

public interface VisualBehaviorRepository {

    void saveVisualBehaviorData(VisualBehavior visualBehavior);

    Flux<VisualBehavior> getVisualBehaviorData(String userId);

}
