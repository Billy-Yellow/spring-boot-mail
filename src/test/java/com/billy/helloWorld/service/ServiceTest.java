package com.billy.helloWorld.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    private String emailAddress = "562790152@qq.com";

    @Test
    public void sayHelloTest(){
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail(emailAddress,"try","hello, it is a great try");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<html>\n"+
                "<body>\n"+
                "<h3>hello world, this is an html email</h3>\n"+
                "</body>\n"+
                "</html>";
        mailService.sendHtmlMail(emailAddress,"this is an html email",content);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath = "/Users/xuhuang/Documents/mail/spring-boot-mail/demo.zip";
        mailService.sendAttachmentsMail(emailAddress, "this is an email with attachment","demo", filePath);
    }

    @Test
    public void  sendInlineResourceMailTest(){
        String imgPath = "/Users/xuhuang/Documents/mail/spring-boot-mail/Laura.png";
        String rscId = "billy001";
        String content="<html><body>this is an email with pic:<img src=\'cid:"+rscId+"\'></img></body></html>";
        mailService.sendInlineResourceMail(emailAddress,"this is an email with pic", content, imgPath, rscId);
    }

    @Test
    public void TemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("section","react_resume");

        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail(emailAddress,"this is an email with template",emailContent);
    }

}
