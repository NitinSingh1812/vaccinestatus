package com.ns.apps.vaccinestatus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {

    @JsonProperty("session_id")
    public String sessionId;

    @JsonProperty("date")
    public String date;

    @JsonProperty("available_capacity")
    public long availableCapacity;

    @JsonProperty("min_age_limit")
    public int maxAgeLimit;

    @JsonProperty("vaccine")
    public String vaccine;

    @JsonProperty("slots")
    public List<String> slots;

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", date='" + date + '\'' +
                ", availableCapacity=" + availableCapacity +
                ", maxAgeLimit=" + maxAgeLimit +
                ", vaccine='" + vaccine + '\'' +
                ", slots=" + slots +
                '}';
    }
}
