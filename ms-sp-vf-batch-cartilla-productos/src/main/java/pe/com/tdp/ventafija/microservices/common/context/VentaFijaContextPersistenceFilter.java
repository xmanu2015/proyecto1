package pe.com.tdp.ventafija.microservices.common.context;


import pe.com.tdp.ventafija.microservices.domain.dto.ServiceCallEvent;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class VentaFijaContextPersistenceFilter implements Filter {
    private static final String X_HEADER_USUARIO = "X_HTTP_USUARIO";
    private static final String X_HEADER_ORDERID = "X_HTTP_ORDERID";
    private static final String X_HEADER_DOCIDENT = "X_HTTP_DOCIDENT";
    private static final String X_HEADER_APPSOURCE = "X_HTTP_APPSOURCE";
    private static final String X_HEADER_APPVERSION = "X_HTTP_APPVERSION";

    private String serviceCode;

    public VentaFijaContextPersistenceFilter(String serviceCode) {
        super();
        this.serviceCode = serviceCode;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String username = httpRequest.getHeader(X_HEADER_USUARIO);
        String orderId = httpRequest.getHeader(X_HEADER_ORDERID);
        String docNumber = httpRequest.getHeader(X_HEADER_DOCIDENT);
        String appSource = httpRequest.getHeader(X_HEADER_APPSOURCE);
        String appVersion = httpRequest.getHeader(X_HEADER_APPVERSION);

        ServiceCallEvent event = new ServiceCallEvent();
        event.setUsername(username);
        event.setOrderId(orderId);
        event.setServiceCode(serviceCode);
        event.setDocNumber(docNumber);
        event.setSourceApp(appSource);
        event.setSourceAppVersion(appVersion);

        try {
            VentaFijaContext ctx = new VentaFijaContextImpl();
            ctx.setServiceCallEvent(event);

            pe.com.tdp.ventafija.microservices.common.context.VentaFijaContextHolder.setContext(ctx);
            chain.doFilter(request, response);
        } finally {
            VentaFijaContextHolder.clearContext();
        }
    }

    @Override
    public void destroy() {}
}
