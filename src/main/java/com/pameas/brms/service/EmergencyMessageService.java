package com.pameas.brms.service;

import com.pameas.brms.model.EmergencyStatus;
import com.pameas.brms.model.Location;
import com.pameas.brms.model.Message;
import com.pameas.brms.model.entity.ClientLocation;
import com.pameas.brms.model.entity.GeofenceNtf;
import com.pameas.brms.model.entity.PersonalInfo;
import com.pameas.brms.model.repository.ClientLocationRepository;
import com.pameas.brms.model.repository.PersonalInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class EmergencyMessageService {

    private final KieContainer kieContainer;
    private final ClientLocationRepository locationRepo;
    private final MessagingService msgService;
    private final PersonalInfoRepository infoRepo;

    @Autowired
    public EmergencyMessageService(KieContainer kieContainer, ClientLocationRepository locationRepo, MessagingService msgService, PersonalInfoRepository infoRepo) {
        this.kieContainer = kieContainer;
        this.locationRepo = locationRepo;
        this.msgService = msgService;
        this.infoRepo = infoRepo;
    }

    public void emergencyLocation(EmergencyStatus status, Message message) {

        //should replace with find all
        ClientLocation testClientLocation = locationRepo.findByMacAddress("38:37:8B:DE:42:F8");
        Location testLocation = testClientLocation.getLocationHistory().get(testClientLocation.getLocationHistory().size()-1);
        testLocation.setGfName(testLocation.getGeofenceNames().get(0));
        Set<String> geofences = new HashSet<>();

        //get the geofence other than the one that has an emergency , for testing purposes
        for(Location loc: testClientLocation.getLocationHistory()){
            if(!status.getEmergencyLocation().equals(loc.getGeofenceNames().get(0))){
                geofences.add(loc.getGeofenceNames().get(0));
            }
        }
        List<String> destList = new ArrayList<>(geofences);
        status.setDestination(destList.get(0));

        //should be in a loop
        PersonalInfo personalInfo = infoRepo.findClientAddressByMacAddress(testClientLocation.getMacAddress());
        log.info("personal info, client address :{}", personalInfo);

        List<String> invites = new ArrayList<>();

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("alertMsg", message);
        kieSession.insert(status);
        kieSession.insert(testLocation);
        kieSession.insert(personalInfo);
        //kieSession.insert(gfNtf);
        kieSession.fireAllRules();
        kieSession.dispose();

        if(message.getMessage() != null){
            invites.add(message.getAddress());
            log.info("address :{}, message :{}", message.getAddress(), message.getMessage());
            msgService.runSelInvite("confBrms", invites, message.getMessage());
        }
    }

}
