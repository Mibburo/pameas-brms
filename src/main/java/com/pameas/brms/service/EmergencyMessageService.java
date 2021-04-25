package com.pameas.brms.service;

import com.pameas.brms.model.EmergencyStatus;
import com.pameas.brms.model.Location;
import com.pameas.brms.model.Message;
import com.pameas.brms.model.entity.ClientLocation;
import com.pameas.brms.model.entity.GeofenceNtf;
import com.pameas.brms.model.repository.ClientLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmergencyMessageService {

    private final KieContainer kieContainer;
    private final ClientLocationRepository locationRepo;
    private final MessagingService msgService;

    @Autowired
    public EmergencyMessageService(KieContainer kieContainer, ClientLocationRepository locationRepo, MessagingService msgService) {
        this.kieContainer = kieContainer;
        this.locationRepo = locationRepo;
        this.msgService = msgService;
    }

    public String showMessage(GeofenceNtf gfNtf, EmergencyStatus status, ClientLocation clLocation, Message message) {

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("alertMsg", message);
        kieSession.insert(status);
        kieSession.insert(clLocation);
        kieSession.insert(gfNtf);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.info("xxxxxxxxxxxxxxxx message :{}", message.getMessage());
        return message.getMessage();
    }

    public void emergencyLocation(EmergencyStatus status, Message message) {

        ClientLocation testClientLocation = locationRepo.findByMacAddress("38:37:8B:DE:42:F8");
        Location testLocation = testClientLocation.getLocationHistory().get(testClientLocation.getLocationHistory().size()-1);
        testLocation.setGfName(testLocation.getGeofenceNames().get(0));
        List<String> invites = new ArrayList<>();

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("alertMsg", message);
        kieSession.insert(status);
        kieSession.insert(testLocation);
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
