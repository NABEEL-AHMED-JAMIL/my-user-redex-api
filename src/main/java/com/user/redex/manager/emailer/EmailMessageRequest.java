package com.user.redex.manager.emailer;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.redex.manager.velocity.TemplateType;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailMessageRequest implements Serializable {

    private TemplateType templateType;
    private String fromEmail;
    private String recipients;
    private List<String> recipientsMulti = new ArrayList<>();
    private String subject;
    private Map<String, Object> bodyMap = new HashMap<>();

    public EmailMessageRequest() {}

    public TemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public List<String> getRecipientsMulti() {
        return recipientsMulti;
    }

    public void setRecipientsMulti(List<String> recipientsMulti) {
        this.recipientsMulti = recipientsMulti;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getBodyMap() {
        return bodyMap;
    }

    public void setBodyMap(Map<String, Object> bodyMap) {
        this.bodyMap = bodyMap;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}