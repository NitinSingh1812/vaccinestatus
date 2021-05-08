package com.ns.apps.vaccinestatus.controller;

import com.ns.apps.vaccinestatus.dto.VaccineStatusContainer;
import com.ns.apps.vaccinestatus.service.VaccineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class VaccineStatusController {

    @Autowired
    VaccineStatusService vaccineStatusService;

    @CrossOrigin
    @GetMapping("/status")
    public ResponseEntity<VaccineStatusContainer> getVaccineStatus() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(vaccineStatusService.getStatus());
    }

}
