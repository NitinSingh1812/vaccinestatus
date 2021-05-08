package com.ns.apps.vaccinestatus.service;

import com.ns.apps.vaccinestatus.component.VaccineFeignClient;
import com.ns.apps.vaccinestatus.dto.HospitalInfo;
import com.ns.apps.vaccinestatus.dto.VaccineStatusContainer;
import com.ns.apps.vaccinestatus.util.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class VaccineStatusService {

    @Autowired
    VaccineFeignClient httpClient;

    @Autowired
    @Qualifier("threadExecutorPool")
    ExecutorService threadExecutorPool;

    public VaccineStatusContainer getStatus() throws ExecutionException, InterruptedException {
        Callable<VaccineStatusContainer> callableMumbaiToday = ()->httpClient.getVaccineStatus("395", DateUtility.getCurrentDate(),"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        Callable<VaccineStatusContainer> callableMumbaiTomorrow = ()->httpClient.getVaccineStatus("395", DateUtility.getTomorrowsDate(),"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        Callable<VaccineStatusContainer> callableThaneToday = ()->httpClient.getVaccineStatus("392", DateUtility.getCurrentDate(),"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        Callable<VaccineStatusContainer> callableThaneTomorrow = ()->httpClient.getVaccineStatus("392", DateUtility.getTomorrowsDate(),"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        Future<VaccineStatusContainer> futureMumbaiToday = threadExecutorPool.submit(callableMumbaiToday);
        Future<VaccineStatusContainer> futureThaneToday = threadExecutorPool.submit(callableThaneToday);
        Future<VaccineStatusContainer> futureMumbaiTomorrow = threadExecutorPool.submit(callableMumbaiTomorrow);
        Future<VaccineStatusContainer> futureThaneTomorrow = threadExecutorPool.submit(callableThaneTomorrow);
        List<HospitalInfo> hospitalInfosMumbaiTomorrow = getHospitalsWithVaccines(futureMumbaiTomorrow.get().centers);
        List<HospitalInfo> hospitalInfosThaneTomorrow = getHospitalsWithVaccines(futureThaneTomorrow.get().centers);
        List<HospitalInfo> hospitalInfosThaneToday = getHospitalsWithVaccines(futureThaneToday.get().centers);
        VaccineStatusContainer vaccineStatusContainerMumbaiToday = futureMumbaiToday.get();
        vaccineStatusContainerMumbaiToday.centers = getHospitalsWithVaccines(vaccineStatusContainerMumbaiToday.centers);
        vaccineStatusContainerMumbaiToday.centers.addAll(hospitalInfosThaneToday);
        vaccineStatusContainerMumbaiToday.centers.addAll(hospitalInfosMumbaiTomorrow);
        vaccineStatusContainerMumbaiToday.centers.addAll(hospitalInfosThaneTomorrow);
        return vaccineStatusContainerMumbaiToday;
    }

    private List<HospitalInfo> getHospitalsWithVaccines(List<HospitalInfo> allHospitals) {
        return allHospitals.stream().filter(hospitalInfo -> hospitalInfo.sessions.stream().anyMatch(session->session.availableCapacity>0)).collect(Collectors.toList());
    }

    @PreDestroy
    public void cleanUp() {
        threadExecutorPool.shutdown();
    }

}
