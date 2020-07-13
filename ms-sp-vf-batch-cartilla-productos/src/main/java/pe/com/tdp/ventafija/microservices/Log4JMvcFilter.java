package pe.com.tdp.ventafija.microservices;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

public class Log4JMvcFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(Log4JMvcFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			
			// TODO actualizar
			MDC.put("username", "veera"); // vendedor
			
			MDC.put("loginid", "123");   // id vendedor
			MDC.put("phonenumber", "veera"); // telelfono
			MDC.put("clientcode", "veera");  // id vendedor
			chain.doFilter(request, response);
		} finally {
			MDC.remove("username");
			MDC.remove("loginid");
			MDC.remove("phonenumber");
			MDC.remove("clientcode");;
		}
	}

	@Override
	public void destroy() {}

}
