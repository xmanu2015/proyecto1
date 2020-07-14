package pe.com.tdp.ventafija.microservices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TdpCatalogAditionalData {
    private int productId;
    private int parameterId;
    private String value;
    private String herramienta;

    public TdpCatalogAditionalData() {

    }
}
