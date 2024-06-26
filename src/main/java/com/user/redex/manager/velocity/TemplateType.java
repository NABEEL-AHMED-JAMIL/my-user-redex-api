package com.user.redex.manager.velocity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

/**
 * @author Nabeel Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum TemplateType {

    ADD_BOOK_PATH("templates/addbook.vm"),
    FORGOT_PASS_PATH("templates/forgot-pass.vm"),
    PASSWORD_RESET_PATH("templates/password-reset.vm"),
    REGISTER_PATH("templates/register.vm");

    private String templatePath;

    TemplateType(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}