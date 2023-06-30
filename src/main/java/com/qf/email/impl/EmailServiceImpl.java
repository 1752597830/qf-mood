package com.qf.email.impl;

import com.qf.email.EmailService;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Random;

/**
 * @Auther: sin
 * @Date: 2023/1/22 - 01 - 22 - 14:19
 * @Description: com.qf.email.impl
 * @version: 1.0
 */

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 邮件发件人
     */
    @Value("${spring.mail.from}")
    private String fromAddress;

    @Resource
    TemplateEngine templateEngine;

    @Override
    public String getRegisterEmailVerificationCode(String toAddress, String template) {
        //随机数 生产6位验证码
        Random random = new Random();
        String verifyCode = String.valueOf(random.nextInt(100000)+100001);
//        redisTemplate.opsForValue().set(toAddress, verifyCode,3600, TimeUnit.SECONDS);
        System.out.println(verifyCode);
        //创建邮件正文
        Context context = new Context();
        context.setVariable("verifyCode", verifyCode.toCharArray());

        //将模块引擎内容解析成html字符串
        String emailContent = templateEngine.process(template, context);
        MimeMessage message=mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject("绿松心情小程序");
            helper.setText(emailContent,true);
            mailSender.send(message);
            return verifyCode;
        }catch (MessagingException e) {
            return null;
        }
    }


}
