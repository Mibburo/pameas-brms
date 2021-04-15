package com.pameas.brms.service;

import com.pameas.brms.model.EmergencyStatus;
import com.pameas.brms.model.Message;
import com.pameas.brms.model.entity.ClientLocation;
import com.pameas.brms.model.entity.GeofenceNtf;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmergencyMessageService {

    @Autowired
    private KieContainer kieContainer;

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
}
