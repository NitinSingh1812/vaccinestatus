package com.ns.apps.vaccinestatus.component;

import com.ns.apps.vaccinestatus.dto.District;
import com.ns.apps.vaccinestatus.dto.State;
import com.ns.apps.vaccinestatus.dto.VaccineStatusContainer;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Headers("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36")
@FeignClient(value = "vaccinetracker",url = "https://cdn-api.co-vin.in/api/")
public interface VaccineFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/appointment/sessions/public/calendarByDistrict?district_id={districtId}&date={currentDate}")
    VaccineStatusContainer getVaccineStatus(@RequestParam("districtId") String districtId, @RequestParam("currentDate") String currentDate);

    @RequestMapping(method = RequestMethod.GET, value = "/v2/admin/location/states")
    Map<String, List<State>> getStates();

    @RequestMapping(method = RequestMethod.GET, value = "/v2/admin/location/districts/{stateId}")
    Map<String, List<District>> getDistricts(@RequestParam("stateId") String stateId);

}
