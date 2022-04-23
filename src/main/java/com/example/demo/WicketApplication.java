package com.example.demo;

import com.example.demo.pages.PageHome;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.crypt.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
@ComponentScan
public class WicketApplication extends WebApplication {

    static private Logger log = LoggerFactory.getLogger(WicketApplication.class.getName());

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Class<? extends Page> getHomePage() {
        return PageHome.class;
    }

    @Override
    protected void onDestroy() {
        log.info("Shutdown wicket application");
    }

    @Override
    public void init() {
        super.init();
        System.out.println("ASd");
        log.info("Runtime configuration: DEPLOYMENT");

        if (getConfigurationType().equals(RuntimeConfigurationType.DEPLOYMENT)) {
            log.info("Runtime configuration: DEPLOYMENT");
            //setRootRequestMapper(new FlowCryptoMapper(getRootRequestMapper(), this));
        }

        if (getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT)) {
            log.info("Runtime configuration: DEVELOPMENT");
            getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        }

        getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);

        getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);
        getMarkupSettings().setStripComments(true);
        getMarkupSettings().setStripWicketTags(true);

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

       /* getSecuritySettings().setAuthorizationStrategy(new SimplePageAuthorizationStrategy(PageBase.class, getSignInPageClass()) {
            @Override
            protected boolean isAuthorized()
            {
                return FlowSession.get().isSignedIn();
            }
        });
        */

        final IBootstrapSettings bootstrapSettings = new BootstrapSettings();
        bootstrapSettings.setDeferJavascript(true); //https://javascript.info/script-async-defer
        Bootstrap.install(this, bootstrapSettings);
        log.info("Bootstrap Version: "+bootstrapSettings.getVersion());

        //mountPage("/users",  PageUsers.class);

        /*
        getSharedResources().add("flowSecurityLogo",  new PackageResourceReference(PageTemplate.class,  "images/flow-security_150dpi.png").getResource());
        getSharedResources().add("flowLogo",  new PackageResourceReference(PageTemplate.class,  "images/flow_logo_t.png").getResource());
        getSharedResources().add("flowWheel", new PackageResourceReference(PageTemplate.class,  "images/flow_menu_wheel.png").getResource());
        */


       /*
       Protect your web apps with Content Security Policy (CSP)
       https://wicket.apache.org/news/2020/07/15/wicket-9-released.html
       https://cwiki.apache.org/confluence/display/WICKET/Migration+to+Wicket+9.0

       With version 9 Wicket introduced a content security policy (CSP) active by default which prevents inline JavaScript and CSS code from been executed.
       If you are not planning to make your web app CSP compliant you can disable this policy using a simple line of code during app initialization:

       getCspSettings().blocking().disabled();

        */
    }

}
