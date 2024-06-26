package com.user.redex.manager.emailer;

import com.user.redex.manager.velocity.TemplateFactory;
import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

/**
 * @author Nabeel Ahmed
 */
@Component
public class EmailMessagesFactory {

    private Logger logger = LoggerFactory.getLogger(EmailMessagesFactory.class);

    private final String UTF8 = "utf-8";

    @Value("${admin.email}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateFactory templateFactory;

    /**
     * sendSimpleMail method use to send email.
     * @param emailContent
     * @throws Exception
     * */
    public String sendSimpleMail(EmailMessageRequest emailContent) throws Exception {
        try {
            MimeMessage mailMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, UTF8);
            if (ReduxUtil.isNull(emailContent.getFromEmail())) {
                helper.setFrom(fromEmail);
            }
            // if email not set by the user then default email set
            if (!emailContent.getRecipients().isEmpty()) {
                helper.setTo(emailContent.getRecipients().split(","));
                // * * * * * * * * *Send cc's* * * * * * * * *
                if (!ReduxUtil.isNull(emailContent.getRecipientsMulti()) && emailContent.getRecipientsMulti().size() > 0) {
                    String ccSendTo = emailContent.getRecipientsMulti().toString();
                    // removing the [] from the array
                    ccSendTo = ccSendTo.substring(1, ccSendTo.length()-1);
                    helper.setCc(ccSendTo);
                }
                // subject and content
                helper.setSubject(emailContent.getSubject());
                StringWriter content = this.templateFactory.getWriterResponse(
                    emailContent.getTemplateType(), emailContent.getBodyMap());
                helper.setText(content.toString(), true);
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

}
