package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class WebInitializer implements ServletContextInitializer {

    private static final Log log = LogFactory.getLog(WebInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration wicketFilter = servletContext.addFilter("wicket-filter", WicketFilter.class);
        wicketFilter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        wicketFilter.setInitParameter("applicationBean", "wicketApplication");
        wicketFilter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        wicketFilter.addMappingForUrlPatterns(null, false, "/*");
        //icketFilter.setInitParameter("configuration", RuntimeConfigurationType.DEVELOPMENT.toString());
        wicketFilter.setInitParameter("configuration", RuntimeConfigurationType.DEPLOYMENT.toString());
    }
}
