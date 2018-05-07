package com.yszn.security.core.properties.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/2 9:44
 * @Description: 微信配置
 * @Version: 1.0
 **/
@Component
@ConfigurationProperties(prefix = "yszn.weixin")
public class WeiXinProperties {

    /**
     * 微信服务器的回调地址(当前服务器地址)
     */
    private String callBackServerAddress;
    /**
     * 微信服务号AppId
     */
    private String appId;
    /**
     * 微信服务号AppSecret
     */
    private String appSecret;

    /**
     * 服务通知完成确认通知
     */
    private String serverFinishNotice;
    /**
     * 系统报警提醒
     */
    private String systemReport2ThePoliceNotice;
    /**
     * 授权通过提醒
     */
    private String authorizePassedNotice;
    /**
     * 意见建议提醒
     */
    private String suggestionNotice;
    /**
     * 工作分配通知
     */
    private String wordAllocationNotice;
    /**
     * 新增故障申报提醒
     */
    private String newDeviceErrorNotice;
    /**
     * 新求助信息提醒
     */
    private String newHelpMessageNotice;
    /**
     * 作业提醒
     */
    private String taskNotice;
    /**
     * 建议反馈提醒
     */
    private String suggetionFeedbackNotice;
    /**
     * 授权请求通知
     */
    private String authorizeRequestNotice;

    public String getCallBackServerAddress() {
        return callBackServerAddress;
    }

    public void setCallBackServerAddress(String callBackServerAddress) {
        this.callBackServerAddress = callBackServerAddress;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getServerFinishNotice() {
        return serverFinishNotice;
    }

    public void setServerFinishNotice(String serverFinishNotice) {
        this.serverFinishNotice = serverFinishNotice;
    }

    public String getSystemReport2ThePoliceNotice() {
        return systemReport2ThePoliceNotice;
    }

    public void setSystemReport2ThePoliceNotice(String systemReport2ThePoliceNotice) {
        this.systemReport2ThePoliceNotice = systemReport2ThePoliceNotice;
    }

    public String getAuthorizePassedNotice() {
        return authorizePassedNotice;
    }

    public void setAuthorizePassedNotice(String authorizePassedNotice) {
        this.authorizePassedNotice = authorizePassedNotice;
    }

    public String getSuggestionNotice() {
        return suggestionNotice;
    }

    public void setSuggestionNotice(String suggestionNotice) {
        this.suggestionNotice = suggestionNotice;
    }

    public String getWordAllocationNotice() {
        return wordAllocationNotice;
    }

    public void setWordAllocationNotice(String wordAllocationNotice) {
        this.wordAllocationNotice = wordAllocationNotice;
    }

    public String getNewDeviceErrorNotice() {
        return newDeviceErrorNotice;
    }

    public void setNewDeviceErrorNotice(String newDeviceErrorNotice) {
        this.newDeviceErrorNotice = newDeviceErrorNotice;
    }

    public String getNewHelpMessageNotice() {
        return newHelpMessageNotice;
    }

    public void setNewHelpMessageNotice(String newHelpMessageNotice) {
        this.newHelpMessageNotice = newHelpMessageNotice;
    }

    public String getTaskNotice() {
        return taskNotice;
    }

    public void setTaskNotice(String taskNotice) {
        this.taskNotice = taskNotice;
    }

    public String getSuggetionFeedbackNotice() {
        return suggetionFeedbackNotice;
    }

    public void setSuggetionFeedbackNotice(String suggetionFeedbackNotice) {
        this.suggetionFeedbackNotice = suggetionFeedbackNotice;
    }

    public String getAuthorizeRequestNotice() {
        return authorizeRequestNotice;
    }

    public void setAuthorizeRequestNotice(String authorizeRequestNotice) {
        this.authorizeRequestNotice = authorizeRequestNotice;
    }
}
