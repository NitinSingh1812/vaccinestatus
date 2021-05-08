package com.ns.apps.vaccinestatus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class District {

    @JsonProperty("state_id")
    public String stateId;

    @JsonProperty("district_id")
    public String districtId;

    @JsonProperty("district_name")
    public String districtName;

}
