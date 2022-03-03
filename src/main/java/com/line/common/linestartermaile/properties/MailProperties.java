package com.line.common.linestartermaile.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yangcs
 * @Date: 2021/1/8 16:14
 * @Description:
 */
@ConfigurationProperties(prefix = "line.mail")
public class MailProperties {
    //    发送人
    private String senderEmail;
    //    授权码
    private String senderCode;
    //    服务器地址
    private String emailSMTPHost;
    //    收件人邮箱
    private String receiveMailAccount;
    //    抄送
    private String ccMailAccount;
    //    密送人邮箱
    private String bccmailAccount;
    //   使用的协议
    private String transportProtocol;
    //  需要请求认证
    private String smtpAuth;

    private String fileCovtPath;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getEmailSMTPHost() {
        return emailSMTPHost;
    }

    public void setEmailSMTPHost(String emailSMTPHost) {
        this.emailSMTPHost = emailSMTPHost;
    }

    public String getReceiveMailAccount() {
        return receiveMailAccount;
    }

    public void setReceiveMailAccount(String receiveMailAccount) {
        this.receiveMailAccount = receiveMailAccount;
    }

    public String getCcMailAccount() {
        return ccMailAccount;
    }

    public void setCcMailAccount(String ccMailAccount) {
        this.ccMailAccount = ccMailAccount;
    }

    public String getBccmailAccount() {
        return bccmailAccount;
    }

    public void setBccmailAccount(String bccmailAccount) {
        this.bccmailAccount = bccmailAccount;
    }


    public String getTransportProtocol() {
        return transportProtocol;
    }

    public void setTransportProtocol(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getFileCovtPath() {
        return fileCovtPath;
    }

    public void setFileCovtPath(String fileCovtPath) {
        this.fileCovtPath = fileCovtPath;
    }
}
