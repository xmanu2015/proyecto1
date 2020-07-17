package pe.com.tdp.ventafija.microservices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TdpCatalogData {
    private String codigoproducto;
    private String commercialoperation;
    private String segmento;
    private String canal;
    private String entidad;
    private String provincia;
    private String distrito;
    private String campaign;
    private String campaingcode;
    private Integer priority;
    private String prodtypecode;
    private String producttype;
    private String prodcategorycode;
    private String productcategory;
    private String productcode;
    private String productname;
    private String bloquetv;
    private String svainternet;
    private String svalinea;
    private String tiporegistro;
    private String discount;
    private Double price;
    private Double promprice;
    private Integer monthperiod;
    private Double installcost;
    private String linetype;
    private String paymentmethod;
    private Double cashprice;
    private Double financingcost;
    private Integer financingmonth;
    private String equipmenttype;
    private Integer returnmonth;
    private String returnperiod;
    private Integer internetspeed;
    private Integer promspeed;
    private Integer periodpromspeed;
    private String internettech;
    private String tvsignal;
    private String tvtech;
    private String equiplinea;
    private String equipinternet;
    private String equiptv;
    private String origintvtechnology;
    private String typetypedecoorigin;
    private String typedecoorigin;
    private String quantitytypedeco;
    private String internettechnologyorigin;
    private String internetequipmentorigin;
    private String comfortcomfortrepeaterequipment;
    private String comfortrepeater;
    private String voicetechnology;
    private String migrationlogicfromtdmtovoip;
    private String migratevoiptovoip;
    private String hfctoftthmigrationlogic;
    private String herramienta;

    public TdpCatalogData() {

    }
}
