package com.line.common.linestartermaile.configuration;

import com.line.common.linestartermaile.properties.MailProperties;
import com.line.common.linestartermaile.service.IMailSendProvider;
import com.line.common.linestartermaile.service.impl.MailSendProvider;
import com.line.common.linestartermaile.service.impl.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Author: yangcs
 * @Date: 2021/1/8 16:12
 * @Description:
 */
@Configuration
@EnableConfigurationProperties({MailProperties.class}) //开启自定义配置,@Component好像也可以生效 @configurationProperties
@ConditionalOnClass(MailSendService.class)
@ConditionalOnProperty(prefix = "line.mail", value = "enable", matchIfMissing = true)
public class MailAutoConfiguration {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    @ConditionalOnMissingBean(MailSendService.class)
    @DependsOn("mailSendProvider")
    public MailSendService mailSendService(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> MailSendService  INIT ");
        MailSendService mailSendService = new MailSendService();
        mailSendService.setMailProperties(mailProperties);
        mailSendService.setMailSendProvider(mailSendProvider());
        return mailSendService;
    }

    @Bean
    public IMailSendProvider mailSendProvider(){
        MailSendProvider mailSendService = new MailSendProvider();
        mailSendService.setMailProperties(mailProperties);
        System.out.println("mailProperties 配置信息 如下");
        System.out.println(mailProperties);
        return mailSendService;
    }



}
