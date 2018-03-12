package edu.nju.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 发送邮件的工具类
 */
public class MailUtil {

    private static MailSender mailSender;

    private static SimpleMailMessage message;

    public void setMailSender(MailSender mailSender) {
        MailUtil.mailSender = mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        MailUtil.message = simpleMailMessage;
    }

    public static boolean sendMail(String to, String msg) {

        message.setTo(to);

        message.setText(String.format(message.getText(), msg));

        mailSender.send(message);
        return true;
    }
}
