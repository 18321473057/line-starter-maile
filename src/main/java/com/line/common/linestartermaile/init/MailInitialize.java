package com.line.common.linestartermaile.init;

import com.line.common.linestartermaile.properties.MailProperties;
import com.line.common.linestartermaile.utils.FileUtilsDelete;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @Author: yangcs
 * @Date: 2022/3/3 14:51
 * @Description:
 */
@Component
public class MailInitialize implements InitializingBean, ApplicationContextAware {

    private ApplicationContext appContext;

    @Override
    public void afterPropertiesSet() {
        MailProperties mp = appContext.getBean(MailProperties.class);
        if (StringUtils.isEmpty(mp.getMailWorkSpace())) {
            //TODO   没有配置工作空间地址
            System.exit(0);
        }
        // 清空这个工作空间
        FileUtilsDelete.delete(mp.getMailWorkSpace());
        new File(mp.getMailWorkSpace()).mkdirs();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
