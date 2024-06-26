package com.user.redex.manager.velocity;

import com.user.redex.util.ExceptionUtil;
import com.user.redex.util.ReduxUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringWriter;
import static com.user.redex.manager.velocity.TemplateType.*;

/**
 * @author Nabeel Ahmed
 */
@Component
public class TemplateFactory {

    private Logger logger = LoggerFactory.getLogger(TemplateFactory.class);

    private VelocityEngine engine;
    private VelocityContext context;
    private StringWriter writer;

    public TemplateFactory() { }

    /**
     * pre method call to inti the velocity engine engine
     * */
    @PostConstruct
    public void init() throws IOException {
        try {
            this.engine = new VelocityEngine();
            this.engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            this.engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            this.engine.init();
        } catch (ExceptionInInitializerError ex) {
            logger.error("Error :- " + ExceptionUtil.getRootCause(ex));
        }
    }

    /**
     * Method use to get the template
     * @param templateType
     * @return Template
     * */
    private Template getTemplate(TemplateType templateType) {
        logger.debug("Template path :- " + templateType.getTemplatePath());
        switch (templateType) {
            case REGISTER_PATH:
                return this.getEngine().getTemplate(REGISTER_PATH.getTemplatePath());
            case PASSWORD_RESET_PATH:
                return this.getEngine().getTemplate(PASSWORD_RESET_PATH.getTemplatePath());
            case FORGOT_PASS_PATH:
                return this.getEngine().getTemplate(FORGOT_PASS_PATH.getTemplatePath());
            case ADD_BOOK_PATH:
                return this.getEngine().getTemplate(ADD_BOOK_PATH.getTemplatePath());
        }
        return null;
    }

    /**
     * Method use to add the business content into velocity context
     * @param payload
     * @return void
     * */
    private void getVelocityContextWithMessage(Object payload) {
        this.setContext(new VelocityContext());
        logger.info("Request Content :- " + payload);
        this.getContext().put("object", payload);
    }

    /**
     * Method use to write the response into template
     * @param templateType
     * @param payload
     * @return StringWriter
     * */
    public StringWriter getWriterResponse(TemplateType templateType, Object payload) throws Exception {
        Template template = getTemplate(templateType);
        if(!ReduxUtil.isNull(template)) {
            // adding the business content into velocity context
            this.getVelocityContextWithMessage(payload);
            this.setWriter(new StringWriter());
            template.merge(context, this.getWriter());
            return this.getWriter();
        }
        throw new Exception("Template Not Found");
    }

    public VelocityEngine getEngine() {
        return engine;
    }

    public void setEngine(VelocityEngine engine) {
        this.engine = engine;
    }

    public VelocityContext getContext() {
        return context;
    }

    public void setContext(VelocityContext context) {
        this.context = context;
    }

    public StringWriter getWriter() {
        return writer;
    }

    public void setWriter(StringWriter writer) {
        this.writer = writer;
    }
}