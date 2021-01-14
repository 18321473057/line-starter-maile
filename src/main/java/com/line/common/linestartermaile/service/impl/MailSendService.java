package com.line.common.linestartermaile.service.impl;

import com.line.common.linestartermaile.properties.MailProperties;
import com.line.common.linestartermaile.service.IMailSendService;
import org.springframework.stereotype.Service;

/**
 * @Author: yangcs
 * @Date: 2021/1/8 15:13
 * @Description: 邮件发送者接口
 */
public class MailSendService implements IMailSendService {

    private  MailProperties mailProperties;

    @Override
    public void send(String msg) {
        System.out.println(">>>>>>>>"+mailProperties.getMailNo());
        System.out.println(">>>>>>>>"+mailProperties.getNickName());
        System.out.println(msg);
    }

    public MailSendService(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }
}
