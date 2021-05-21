package link.jack1024.service.impl;

import link.jack1024.service.MailSenderSrvService;
import org.apache.ibatis.io.Resources;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class MailSenderSrvServiceImpl implements MailSenderSrvService {

    private JavaMailSenderImpl mailSender;

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String mail,String subject,String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Properties prop = new Properties();
        String from;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(Resources.getResourceAsStream("/mail.properties"));
            from = prop.get("mail.smtp.username")+"";
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(from);//发件人
            messageHelper.setTo(mail);
            messageHelper.setSubject(subject);
            messageHelper.setText(text,true);//true代表支持html格式
            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }


    }
}
