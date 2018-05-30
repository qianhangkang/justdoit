package com.example.amqpdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author HANGKANG
 * @date 2018/5/21 下午4:51
 */

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendSimpleEmail(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("qianhangkang@163.com");
        mailMessage.setTo(to);
        mailMessage.setSubject("subject");
        mailMessage.setText("this is just a test");
        try {
            sender.send(mailMessage);
            log.info("send email success");
        } catch (MailException e) {
            log.error("send email fail",e);
        }
    }

    public void sendPicEmail(String to) {
        MimeMessage message = sender.createMimeMessage();
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("qianhangkang@163.com");
            helper.setTo(to);
            helper.setSubject("subject");
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File("/Users/qianhangkang/Downloads/pic.jpg"));
            String fileName = "pic.jpg";
            helper.addAttachment(fileName, file);

            sender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常！", e);
        }
    }

    public void sendHtmlEmail(String to) {
        MimeMessage mimeMessage = sender.createMimeMessage();
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("qianhangkang@163.com");
            helper.setTo(to);
            helper.setSubject("subject");
            helper.setText(content, true);
            sender.send(mimeMessage);

            log.info("send email success");

        } catch (MessagingException e) {
            log.error("send email fail",e);
        }
    }
}
