package pe.com.tdp.ventafija.microservices.common.context;


import pe.com.tdp.ventafija.microservices.domain.dto.ServiceCallEvent;

public class VentaFijaContextImpl implements VentaFijaContext {
    private static final long serialVersionUID = 1L;
    private ServiceCallEvent event;

    public VentaFijaContextImpl() {
    }

    public void setServiceCallEvent(ServiceCallEvent event) {
        this.event = event;
    }

    public ServiceCallEvent getServiceCallEvent() {
        return this.event;
    }
}