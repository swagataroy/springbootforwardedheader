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

public class Test404Filter implements Filter{
	
	Logger log = LoggerFactory.getLogger(Test404Filter.class);


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("doFilter()");
		// pass the request along the filter chain
		
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "Document not found");
		return;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
