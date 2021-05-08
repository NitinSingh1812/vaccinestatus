package com.ns.apps.vaccinestatus.config;

import com.ns.apps.vaccinestatus.dto.District;
import com.ns.apps.vaccinestatus.dto.State;
import com.ns.apps.vaccinestatus.dto.VaccineStatusContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class BeanConfigs {

    @Bean("blockingQueue")
    public BlockingQueue<VaccineStatusContainer> getLinkedBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean("stateDistrictMap")
    public ConcurrentHashMap<State, List<District>> getStateDistrictMap() {
        return new ConcurrentHashMap<>();
    }

}
