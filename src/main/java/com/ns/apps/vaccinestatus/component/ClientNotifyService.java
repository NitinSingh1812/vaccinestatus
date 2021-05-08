package com.ns.apps.vaccinestatus.component;

import com.ns.apps.vaccinestatus.dto.VaccineStatusContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Component
public class ClientNotifyService {

    @Autowired
    @Qualifier("blockingQueue")
    BlockingQueue<VaccineStatusContainer> blockingQueue;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    @Qualifier("threadExecutorPool")
    ExecutorService executor;

    public void notifyClient() {
        try {
            VaccineStatusContainer container = blockingQueue.take();
            messagingTemplate.convertAndSend("/vaccine/status",container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void execute() {
        executor.execute(()->{
            while(true) {
                notifyClient();
            }
        });
    }

    @PreDestroy
    public void cleanUp() {
        executor.shutdown();
    }

}
