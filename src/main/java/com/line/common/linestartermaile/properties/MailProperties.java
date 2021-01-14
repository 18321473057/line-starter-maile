package com.line.common.linestartermaile.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yangcs
 * @Date: 2021/1/8 16:14
 * @Description:
 */
@ConfigurationProperties(prefix = "line.mail")
public class MailProperties {

    private String mailNo;
    private String nickName;

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
