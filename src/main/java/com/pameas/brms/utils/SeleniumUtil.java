package com.pameas.brms.utils;

import com.pameas.brms.configuration.SeleniumConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Slf4j
public class SeleniumUtil {

    private final SeleniumConfig config;

    public SeleniumUtil( ) {
        config = new SeleniumConfig();
    }

    public void testSel(String url, String userName, String userPassword, String confName, String message){
        log.info("inside testSel");
        config.getDriver().get(url);
        WebDriverWait wait = new WebDriverWait(config.getDriver(), 20);
        logIn(wait, userName, userPassword);
        startConference(wait, confName);
        openChat(wait);
        sendMessaage(wait, message);
        config.getDriver().close();
    }

    public void conferenceInvite(String url, String userName, String userPassword, String confName, List<String> invites, String message) {
        log.info("inside conferenceInvite");
        config.getDriver().get(url);
        WebDriverWait wait = new WebDriverWait(config.getDriver(), 20);
        logIn(wait, userName, userPassword);
        startConference(wait, confName);
        inviteToConference(wait, invites);

        /*int i = 0;
        String participantsNo = config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[4]/div[1]/div/div[1]/div/p[2]/span/text()[3]")).getText();
        while (participantsNo.equals(config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[4]/div[1]/div/div[1]/div/p[2]/span/text()[3]")).getText()) && i < 10){
            try {
                i++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }*/

        openChat(wait);
        sendMessaage(wait, message);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        config.getDriver().close();

    }

    private void logIn(WebDriverWait wait, String userName, String userPassword){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inputUser")));
        config.getDriver().findElement(By.id("inputUser")).clear();
        config.getDriver().findElement(By.id("inputUser")).sendKeys(userName);
        config.getDriver().findElement(By.id("inputPassword")).clear();
        config.getDriver().findElement(By.id("inputPassword")).sendKeys(userPassword);
        config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[7]/div/div/div/div[2]/form/div[4]/div[1]/button")).click();
    }

    private void startConference(WebDriverWait wait, String confName){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("uri-input")));
        config.getDriver().findElement(By.id("uri-input")).clear();
        config.getDriver().findElement(By.id("uri-input")).sendKeys(confName);
        //*[@id="app"]/div/div[6]/div/div/div/div/div[1]/div/div[2]/button[3]/i
        config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div/div/div[1]/div/div[2]/button[3]/i")).click();
    }

    private void inviteToConference(WebDriverWait wait, List<String> invites){
        //click invite button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[4]/div[1]/div/div[2]/button[1]")));
        config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[4]/div[1]/div/div[2]/button[1]")).click();
        //click invite by address
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shareOverlay\"]/div[2]/div/div/button[1]")));
        config.getDriver().findElement(By.xpath("//*[@id=\"shareOverlay\"]/div[2]/div/div/button[1]")).click();

        //add addresses to invite
        String addresses = String.join(",", invites);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inputTarget")));
        config.getDriver().findElement(By.id("inputTarget")).sendKeys(addresses);
        config.getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/form/div[2]/button")).click();
    }

    private void openChat(WebDriverWait wait){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[4]/div[1]/div/div[1]/div/div[1]/button[1]/i")));
        config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[4]/div[1]/div/div[1]/div/div[1]/button[1]/i")).click();
    }

    private void sendMessaage(WebDriverWait wait, String message){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[6]/div/div/div[2]/div[2]/div/div[3]/div/div[2]")));
        config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[6]/div/div/div[2]/div[2]/div/div[3]/div/div[2]")).sendKeys(message);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[6]/div/div/div[2]/div[2]/div/button/span/i")));
        config.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div/div[6]/div/div/div[6]/div/div/div[2]/div[2]/div/button/span/i")).click();
    }
}

