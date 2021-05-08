package com.ns.apps.vaccinestatus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    @JsonProperty("state_id")
    public String stateId;

    @JsonProperty("state_name")
    public String stateName;

}
