package com.ns.apps.vaccinestatus.config;

import com.ns.apps.vaccinestatus.component.VaccineFeignClient;
import com.ns.apps.vaccinestatus.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MetadataLoader {

    @Autowired
    @Qualifier("stateDistrictMap")
    ConcurrentHashMap stateDistrictMap;

    @Autowired
    VaccineFeignClient httpClient;

    public void init() {
        Map<String, List<State>> statesMap = httpClient.getStates();
        List<State> stateList = new ArrayList<State>(statesMap.get());
    }

}
