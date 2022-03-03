package com.line.common.linestartermaile.service.impl;

import com.line.common.linestartermaile.dto.MailContent;
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
            CompressDirUtil.compressFileToZip(file.getPath());
            String path = file.getPath();
            path = file.isFile() ? path.substring(0, path.lastIndexOf('.')) : path;
            mailSendProvider.send(mc, new File(path + ".zip"));
            return;
        }
        mailSendProvider.send(mc, file);
    }

    public void setMailSendProvider(IMailSendProvider mailSendProvider) {
        this.mailSendProvider = mailSendProvider;
    }
}
