package com.line.common.linestartermaile.service.impl;

import com.line.common.linestartermaile.dto.MailContent;
import com.line.common.linestartermaile.properties.MailProperties;
import com.line.common.linestartermaile.service.IMailSendProvider;
import com.line.common.linestartermaile.service.IMailSendService;
import com.line.common.linestartermaile.utils.CompressDirUtil;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @Author: yangcs
 * @Date: 2021/1/8 15:13
 * @Description: 邮件发送者接口
 */
public class MailSendService implements IMailSendService {

    private IMailSendProvider mailSendProvider;

    @Override
    public void send(MailContent mc) {
        send(mc, "", true);
    }

    /**
     * filePath 是文件就路径就写到文件
     * 是文件夹就到那一层文件夹
     */
    @Override
    public void send(MailContent mc, String filePath, boolean compression) {
        send(mc, StringUtils.isEmpty(filePath) ? null : new File(filePath), true);
    }

    @Override
    public void send(MailContent mc, File file, boolean compression) {
        if ((file != null && file.exists()) && (file.isDirectory() || (file.isFile() && compression))) {
            //文件夹必压缩 或者文件设置了压缩
            String  toPath  =mailProperties.getMailWorkSpace()+file.getPath().substring(file.getPath().lastIndexOf('\\'));
            CompressDirUtil.compressFileToZip(file.getPath(),toPath);
            mailSendProvider.send(mc, new File(toPath + ".zip"));
            return;
        }
        //不压缩只能是文件夹
        mailSendProvider.send(mc, file.exists() && file.isFile() ? file : null);
    }

    public void setMailSendProvider(IMailSendProvider mailSendProvider) {
        this.mailSendProvider = mailSendProvider;
    }


    private MailProperties mailProperties;

    public void setMailProperties(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }
}
