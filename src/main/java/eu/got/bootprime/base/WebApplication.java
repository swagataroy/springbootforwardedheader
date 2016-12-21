package eu.got.bootprime.base;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;

//import eu.filter.ForwardedHeaderFilter;

import eu.got.bootprime.base.filter.Test404Filter;
import eu.got.bootprime.base.filter.Test5xxFilter;
import eu.got.bootprime.base.filter.TestExceptionFilter;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
     
    @Bean
    public ServletRegistrationBean facesServletRegistration() {
      ServletRegistrationBean registration = new   ServletRegistrationBean(new FacesServlet(), "*.xhtml");
      return registration;
    }
   
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.THEME", "bootstrap");
            servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
        };
    }
    
//    @Bean
//    public ViewResolver thymeleafViewResolver() {
//        ThymeleafViewResolver vr = new ThymeleafViewResolver();
//        vr.setCharacterEncoding("UTF-8");
//        vr.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        // all message/* views will not be handled by this resolver;
//        vr.setExcludedViewNames(new String[]{"index.xhtml"});
//        return vr;
//    }
    
    @Bean
    public FilterRegistrationBean test404Filter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Test404Filter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/test404Error/*");
        return registration;
    }
    
    @Bean
    public FilterRegistrationBean test5xxFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Test5xxFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/test5xxError/*");
        return registration;
    }
    
    @Bean
    public FilterRegistrationBean testExceptionFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TestExceptionFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/testException/*");
        return registration;
    }
    

	
	/*@Bean
	   public ForwardedHeaderFilter forwardedHeaderFilter() throws ServletException { 
		ForwardedHeaderFilter filter = new ForwardedHeaderFilter();
		   FilterConfig filterConfig = null;
		   filter.init(filterConfig);
		return filter;
	}*/
	
	@Bean
    FilterRegistrationBean forwardedHeaderFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new ForwardedHeaderFilter());
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegBean;
    }
	
}
