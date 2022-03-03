package com.line.common.linestartermaile.service.impl;

import com.line.common.linestartermaile.dto.MailContent;
import com.line.common.linestartermaile.properties.MailProperties;
import com.line.common.linestartermaile.service.IMailSendProvider;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: yangcs
 * @Date: 2022/3/3 10:39
 * @Description:
 */
public class MailSendProvider implements IMailSendProvider {
    private MailProperties mailProperties;

    @Override
    public Boolean send(MailContent mc, File file) {
        Session session = getSession();
        Transport transport  ;
        try {
            transport = session.getTransport();
            transport.connect(mailProperties.getSenderEmail(), mailProperties.getSenderCode());//连接发送人的邮箱账户

            MimeMessage message = createMimeMessage(session, mc, file);//获取邮件对象（封装了一个方法）
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {

            return false;
        }

        try {
            // 7. 关闭连接
            transport.close();
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    private Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", mailProperties.getTransportProtocol());// 使用的协议
        props.setProperty("mail.smtp.host", mailProperties.getEmailSMTPHost());// 发件人的邮箱的SMTP服务器地址
        props.setProperty("mail.smtp.auth", mailProperties.getSmtpAuth());// 需要请求认证
        Session session = Session.getInstance(props);//得到会话对象实例
        session.setDebug(true);//是否打印详细日志
        return session;
    }

    public MimeMessage createMimeMessage(Session session, MailContent mc, File file) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(mailProperties.getSenderEmail(), "发件人", "UTF-8"));
        // 3. 设置收件人、抄送人、密送人
        //MimeMessage.RecipientType.TO：收件类型；MimeMessage.RecipientType.CC：抄送类型；MimeMessage.RecipientType.BCC：密送类型
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailProperties.getReceiveMailAccount(), "收件人", "UTF-8"));
//        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(ccMailAccount, "抄送人", "UTF-8"));
//        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bccmailAccount, "密送人", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(mc.getTitle(), "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(mc.getContent(), "text/html;charset=UTF-8");

//*****************以下部分为携带附件代码，不需要携带附件的可删除星号圈起的部分*************************
        if(file != null &&  file.exists()){
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart(); // 用来设置正文的
            textBodyPart.setText(mc.getContent());
            multipart.addBodyPart(textBodyPart);

            MimeBodyPart file1 = new MimeBodyPart();
            DataHandler handler = new DataHandler(new FileDataSource(file));
            file1.setDataHandler(handler);
            //对文件名进行编码，防止出现乱码
            String fileName = MimeUtility.encodeWord(file.getName(), "utf-8", "B");
            file1.setFileName(fileName);
            multipart.addBodyPart(file1);
            message.setContent(multipart);
        }

//*******************************************************************************************
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }


    public void setMailProperties(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }


}
