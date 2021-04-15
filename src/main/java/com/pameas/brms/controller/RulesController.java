package com.pameas.brms.controller;

import com.pameas.brms.model.EmergencyStatus;
import com.pameas.brms.model.Message;
import com.pameas.brms.model.entity.ClientLocation;
import com.pameas.brms.model.entity.GeofenceNtf;
import com.pameas.brms.service.EmergencyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value ="rules")
public class RulesController {

    private final EmergencyMessageService msgService;

    @Autowired
    public RulesController(EmergencyMessageService msgService){
        this.msgService = msgService;
    }

    @PostMapping("/scenario1")
    public void executeRuleTest(@RequestBody GeofenceNtf gfNtf){
        EmergencyStatus emergencyStatus = new EmergencyStatus();
        emergencyStatus.setIsEmergency(true);

        log.info("aaaaaaaaaaaaaaaaaaaaa gfntf :{}", gfNtf);

        ClientLocation clientLocation = new ClientLocation();
        msgService.showMessage(gfNtf, emergencyStatus, clientLocation, new Message());
    }

    @GetMapping("/scenario2")
    public void executeRuleTest2(GeofenceNtf gfNtf){
        EmergencyStatus emergencyStatus = new EmergencyStatus();
        emergencyStatus.setIsEmergency(false);

        ClientLocation clientLocation = new ClientLocation();
        msgService.showMessage(gfNtf, emergencyStatus, clientLocation, new Message());
    }
}
