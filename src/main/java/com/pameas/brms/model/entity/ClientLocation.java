package com.pameas.brms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import com.pameas.brms.model.Location;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ClientLocation {

    @Id
    private String id;

    private String macAddress;
    private String hashedMacAddress;
    private List<Location> locationHistory;
//    private String xLocation;
//    private String yLocation;
//    private String errorLevel;
//    private String isAssociated;
//    private String campusId;
//    private String buildingId;
//    private String floorId;
//    private String hashedMacAddress;
//    private String geofenceId;
//    private String locAlgorithm;
//    private String longitude;
//    private String latitude;
//    private String altitude;
//    private String mUnit;
//    private String targetType;
//    private String errorCode;
//    private List<LocationRecord> records;

}
