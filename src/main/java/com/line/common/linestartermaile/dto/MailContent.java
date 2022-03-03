package com.line.common.linestartermaile.dto;

/**
 * @Author: yangcs
 * @Date: 2022/3/3 10:48
 * @Description:  邮件内容类, 不包含附件
 */
public class MailContent {
    //    发送人
    private String senderEmail;
    //    收件人邮箱
    private String receiveMailAccount;
    //    抄送
    private String ccMailAccount;
    //    密送人邮箱
    private String bccmailAccount;
    //头
    private String  title;
    //内容
    private String  content;
    //类型
    private String  miniType;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMiniType() {
        return miniType;
    }

    public void setMiniType(String miniType) {
        this.miniType = miniType;
    }
}
