package pe.com.tdp.ventafija.microservices.common.context;

public interface VentaFijaContextHolderStrategy {

    void clearContext();
    pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext getContext();
    void setContext(pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext context);
    VentaFijaContext createEmptyContext();
}
