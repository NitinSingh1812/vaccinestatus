package com.ns.apps.vaccinestatus.component;

import com.ns.apps.vaccinestatus.dto.VaccineStatusContainer;
import com.ns.apps.vaccinestatus.service.VaccineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class VaccinePollingService {

    @Autowired
    @Qualifier("vaccineStatusSchedulars")
    ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    VaccineStatusService vaccineStatusService;

    @Autowired
    @Qualifier("blockingQueue")
    BlockingQueue<VaccineStatusContainer> blockingQueue;


    @PostConstruct
    public void poll() {
        taskScheduler.schedule(()-> {
            try {
                blockingQueue.add(vaccineStatusService.getStatus());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, new PeriodicTrigger(2, TimeUnit.MINUTES));
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("Shutting down scheduler...");
        taskScheduler.shutdown();
    }
}
