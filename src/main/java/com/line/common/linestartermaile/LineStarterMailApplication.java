package com.line.common.linestartermaile;

import com.line.common.linestartermaile.dto.MailContent;
import com.line.common.linestartermaile.properties.MailProperties;
import com.line.common.linestartermaile.service.IMailSendService;
import com.line.common.linestartermaile.service.impl.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LineStarterMailApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LineStarterMailApplication.class, args);
        IMailSendService bean = applicationContext.getBean(IMailSendService.class);
        MailProperties mp = applicationContext.getBean(MailProperties.class);
        MailContent mailContent = new MailContent();
        mailContent.setTitle("你好我是标题");
        mailContent.setContent("我这个内容在附件中!");
//        bean.send(mailContent);
        bean.send(mailContent,mp.getFileCovtPath()+"\\1\\累计三包2.docx",true);
    }

}
