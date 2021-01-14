package com.line.common.linestartermaile;

import com.line.common.linestartermaile.service.impl.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LineStarterMailApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LineStarterMailApplication.class, args);
        MailSendService bean = applicationContext.getBean(MailSendService.class);
        bean.send("XXA");
    }

}
