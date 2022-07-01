package org.example.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Создание класса для реализации Dispatcher Servlet
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Не используется
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

//public class MyWebInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(final ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext context =
//                new AnnotationConfigWebApplicationContext();
//
//        context.scan("org.example");
//        servletContext.addListener(new ContextLoaderListener(context));
//
//        ServletRegistration.Dynamic appServlet =
//                servletContext.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
//        appServlet.setLoadOnStartup(1);
//        appServlet.addMapping("/");
//    }
//
//
//}
