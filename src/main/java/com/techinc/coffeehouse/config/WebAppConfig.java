package com.techinc.coffeehouse.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@Configuration
public class WebAppConfig implements WebApplicationInitializer {
    // Registers and loads on startup MessageDispatcherServlet for the SOAP messages
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException  {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CoffeeHouseConfig.class.getName());

        // use MessageDispatcherServlet instead of standard DispatcherServlet for SOAP messages
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        // register MessageDispatcherServlet as Web Service entry point
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("MessageDispatcherServlet",
                                                                           messageDispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/ws/*");
    }
}
