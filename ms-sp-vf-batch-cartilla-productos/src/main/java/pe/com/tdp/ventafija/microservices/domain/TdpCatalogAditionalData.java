package pe.com.tdp.ventafija.microservices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TdpCatalogAditionalData {
    private Integer productId;
    private Integer parameterId;
    private String value;
    private String herramienta;

    public TdpCatalogAditionalData() {

    }
}
