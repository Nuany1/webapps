package com.lucky.nacos.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendEmail {
    /*@Autowired
    private JavaMailSender javaMailSender;
    public void SendEmails() throws Exception{
        //创建一个MimeMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //发送邮件的工具类
        MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage);
        helper.setFrom("dyc87112@qq.com"); //发件人
        helper.setTo("dyc87112@qq.com"); //收件人
        helper.setSubject("主题：");//主题
        helper.setText("有附件的邮件");//主体
        javaMailSender.send(mimeMessage);
    }*/
}
