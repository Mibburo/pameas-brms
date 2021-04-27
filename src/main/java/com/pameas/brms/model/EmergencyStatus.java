package com.pameas.brms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EmergencyStatus {

    private Boolean isEmergency;
    private String emergencyType;
    private String emergencyLocation;
    private String destination;
}
