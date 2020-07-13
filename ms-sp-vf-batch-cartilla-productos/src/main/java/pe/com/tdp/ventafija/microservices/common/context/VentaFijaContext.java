package pe.com.tdp.ventafija.microservices.common.context;

import pe.com.tdp.ventafija.microservices.domain.dto.ServiceCallEvent;

import java.io.Serializable;

public interface VentaFijaContext extends Serializable {
    void setServiceCallEvent(ServiceCallEvent var1);

    ServiceCallEvent getServiceCallEvent();
}
