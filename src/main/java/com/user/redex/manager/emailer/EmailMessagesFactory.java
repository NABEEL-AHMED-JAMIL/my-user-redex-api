package com.user.redex.manager.emailer;

import com.user.redex.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @author Nabeel Ahmed
 */
@Component
public class EmailMessagesFactory {

    private Logger logger = LoggerFactory.getLogger(EmailMessagesFactory.class);

    private final String UTF8 = "utf-8";

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * sendSimpleMail method use to send email.
     * @param emailContent
     * @throws Exception
     * */
    public String sendSimpleMail(EmailMessageRequest emailContent) throws Exception {
        try {
            MimeMessage mailMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, UTF8);
            helper.setFrom(emailContent.getFromEmail());
            if (!emailContent.getRecipients().isEmpty()) {
                helper.setTo(emailContent.getRecipients().split(","));
                // * * * * * * * * *Send cc's* * * * * * * * *
                if (emailContent.getRecipientsMulti() != null && emailContent.getRecipientsMulti().size() > 0) {
                    String ccSendTo = emailContent.getRecipientsMulti().toString();
                    // removing the [] from the array
                    ccSendTo = ccSendTo.substring(1, ccSendTo.length()-1);
                    helper.setCc(ccSendTo);
                }
                helper.setSubject(emailContent.getSubject());
                String content = this.getResponseMessage(emailContent.getBodyPayload(), emailContent.getBodyMap());
                helper.setText(content, true);
                logger.info(String.format("Email Send Successfully Content %s.", content));
                this.javaMailSender.send(mailMessage);
                return "Mail sent successfully...";
            }
            throw new Exception("Recipient Not Found");
        } catch (Exception ex) {
            logger.error("Exception :- " + ExceptionUtil.getRootCauseMessage(ex));
            throw new Exception("Error while sending mail");
        }
    }

    /**
     * Method use to fill the input bodyMap object with tag
     * @param bodyPayload
     * @param bodyMap
     * */
    public String getResponseMessage(String bodyPayload, Map<String, Object> bodyMap) {
        if (!bodyMap.isEmpty()) {
            for (Map.Entry<String, Object> objectEntry: bodyMap.entrySet()) {
                bodyPayload = bodyPayload.replace(String.format("${%s}", objectEntry.getKey()),
                    String.valueOf(objectEntry.getValue() != null ? objectEntry.getValue(): ""));
            }
        }
        return bodyPayload;
    }

}
