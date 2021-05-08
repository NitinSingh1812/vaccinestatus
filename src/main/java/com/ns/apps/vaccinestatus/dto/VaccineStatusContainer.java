package com.ns.apps.vaccinestatus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VaccineStatusContainer {

    public List<HospitalInfo> centers;

    @Override
    public String toString() {
        return "VaccineStatusContainer{" +
                "centers=" + centers +
                '}';
    }
}
