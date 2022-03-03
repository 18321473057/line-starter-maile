package com.line.common.linestartermaile.service;

import com.line.common.linestartermaile.dto.MailContent;

import java.io.File;

/**
 * @Author: yangcs
 * @Date: 2022/3/3 10:27
 * @Description:  邮件发送功能提供者
 */
public interface IMailSendProvider {

    Boolean send(MailContent mc, File file ) ;



}
