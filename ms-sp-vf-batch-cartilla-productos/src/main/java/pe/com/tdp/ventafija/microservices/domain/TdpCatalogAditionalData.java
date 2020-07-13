package pe.com.tdp.ventafija.microservices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TdpCatalogAditionalData {
    private String productId;
    private String parameterId;
    private String value;
    private String herramienta;

    public TdpCatalogAditionalData() {

    }
}
