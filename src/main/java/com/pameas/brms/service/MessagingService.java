package com.pameas.brms.service;

import com.pameas.brms.utils.SeleniumUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService {

    @Value("${webrtc.sylk.url}")
    private String url;

    @Value("${webrtc.sylk.user.name}")
    private String userName;

    @Value("${webrtc.sylk.user.password}")
    private String userPassword;

    //SeleniumExample seleniumExample;

    public void runSelInvite(String confName, List<String> invites, String message){
        SeleniumUtil seleniumUtil = new SeleniumUtil();
        seleniumUtil.conferenceInvite(url, userName, userPassword, confName, invites, message);
    }
}
