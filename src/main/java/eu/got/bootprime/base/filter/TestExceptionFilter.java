package eu.got.bootprime.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

public class TestExceptionFilter implements Filter{
	
	Logger log = LoggerFactory.getLogger(TestExceptionFilter.class);


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("doFilter()");
		// pass the request along the filter chain
		
		throw new RuntimeException("Manually generated exception");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
