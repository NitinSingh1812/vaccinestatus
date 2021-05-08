package com.ns.apps.vaccinestatus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalInfo {

    @JsonProperty("center_id")
    public long centerId;

    public String name;

    public String address;

    @JsonProperty("state_name")
    public String stateName;

    @JsonProperty("district_name")
    public String districtName;

    @JsonProperty("block_name")
    public String blockName;

    public int pincode;

    public double lat;

    @JsonProperty("long")
    public double longitude;

    public String from;

    public String to;

    @JsonProperty("fee_type")
    public String feeType;

    public List<Session> sessions;

    @Override
    public String toString() {
        return "HospitalInfo{" +
                "centerId=" + centerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", stateName='" + stateName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", blockName='" + blockName + '\'' +
                ", pincode=" + pincode +
                ", lat=" + lat +
                ", longitude=" + longitude +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", feeType='" + feeType + '\'' +
                ", sessions=" + sessions +
                '}';
    }
}
