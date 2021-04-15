package com.pameas.brms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import com.pameas.brms.model.AccessPoint;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class GeofenceNtf {

    @Id
    private String id;

    private String untransformedAddress;
    private String gfEvent;
    private String gfId;
    private String gfName;
    private String macAddress;
    private String isAssociated;
    private String dwellTime;
    private List<AccessPoint> apInfo;
    private String hashedMacAddress;
    private String timestamp;

}
