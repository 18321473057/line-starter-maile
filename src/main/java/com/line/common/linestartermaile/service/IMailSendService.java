package com.line.common.linestartermaile.service;

import com.line.common.linestartermaile.dto.MailContent;

import java.io.File;

/**
 * @Author: yangcs
 * @Date: 2021/1/8 15:12
 * @Description: 邮件发送者接口
 */
public interface IMailSendService {

    void send(MailContent mc);

    void send(MailContent mc, String filePath, boolean compression);

    void send(MailContent mc, File file, boolean compression);
}
