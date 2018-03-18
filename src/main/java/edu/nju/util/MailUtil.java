package edu.nju.util;

import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 发送邮件的工具类
 */
public class MailUtil {

    private static JavaMailSender mailSender;

    private static SimpleMailMessage simpleMailMessage;

    public void setMailSender(JavaMailSender mailSender) {
        MailUtil.mailSender = mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        MailUtil.simpleMailMessage = simpleMailMessage;
    }

    public static boolean sendMail(String to, String link) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(to);
            helper.setSubject(simpleMailMessage.getSubject());

            helper.setText("<html>" +
                    "<head>" +
                    "<meta charset=\"UTF-8\">" +
                    "</head>" +
                    "<body>" +
                    "<h3>验证邮箱</h3>" +
                    "<a href=\"" + link + "\">" + link + "</a>" +
                    "</body>" +
                    "</html>", true);

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
        return true;
    }
}
