package com.pameas.brms.controller;

import com.pameas.brms.model.EmergencyStatus;
import com.pameas.brms.model.Message;
import com.pameas.brms.model.entity.ClientLocation;
import com.pameas.brms.model.entity.GeofenceNtf;
import com.pameas.brms.service.EmergencyMessageService;
import com.pameas.brms.service.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value ="rules")
public class RulesController {

    private final EmergencyMessageService msgService;
    private final MessagingService selenium;

    //private static SeleniumExample seleniumExample;

    @Autowired
    public RulesController(EmergencyMessageService msgService, MessagingService selenium){
        this.msgService = msgService;
        this.selenium = selenium;
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

    //for testing purposes
    @GetMapping("/testSel")
    public void executeSelenium(String message) throws ScriptException, NoSuchMethodException {
        /*SeleniumExample seleniumExample = new SeleniumExample();
        seleniumExample.conferenceInvite();*/
        List<String> invites = Arrays.asList("mibu2@sylk.link", "a4a4@sip2sip.info");
        selenium.runSelInvite("brmsConf", invites, "new Automated message");
    }
}
