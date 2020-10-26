package com.hyh.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSendTest {

    @Autowired
    private JavaMailSender sender;

    @Test
    public void send(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hyhstyle@126.com");
        simpleMailMessage.setTo("940318269@qq.com");
        simpleMailMessage.setSubject("测试");
        simpleMailMessage.setText("测试成功");
        sender.send(simpleMailMessage);
    }


    @Test
    public void sendHTML() throws MessagingException {
        MimeMessage mimeMailMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setFrom("hyhstyle@126.com");
        mimeMessageHelper.setTo("2565344066@qq.com");
        String content = "<html>\n"+"<body>\n"
                + "<h3>hello world!测试发送html格式邮件</h3>\n"
                +"<a href='www.baidu.com' " + ">\n"
                +"456577 \n"
                +"</a> \n"
                +"</body>\n"+"</html>";
        mimeMessageHelper.setSubject("HTML");
        mimeMessageHelper.setText(content,true);
        sender.send(mimeMailMessage);

    }


}
