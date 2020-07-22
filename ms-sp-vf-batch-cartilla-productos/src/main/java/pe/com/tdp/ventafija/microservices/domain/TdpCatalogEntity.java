package pe.com.tdp.ventafija.microservices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "tdp_catalog_2", schema = "ibmx_a07e6d02edaf552")
@Getter
@Setter
@AllArgsConstructor
public class TdpCatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "product_codigo")
    private String product_codigo;
    @Column(name = "commercialoperation")
    private String commercialoperation;
    @Column(name = "segmento")
    private String segmento;
    @Column(name = "canal")
    private String canal;
    @Column(name = "entidad")
    private String entidad;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "distrito")
    private String distrito;
    @Column(name = "campaign")
    private String campaign;
    @Column(name = "campaingcode")
    private String campaingcode;
    @Column(name = "priority")
    private String priority;
    @Column(name = "prodtypecode")
    private String prodtypecode;
    @Column(name = "producttype")
    private String producttype;
    @Column(name = "prodcategorycode")
    private String prodcategorycode;
    @Column(name = "productcategory")
    private String productcategory;
    @Column(name = "productcode")
    private String productcode;
    @Column(name = "productname")
    private String productname;
    @Column(name = "bloquetv")
    private String bloquetv;
    @Column(name = "svainternet")
    private String svainternet;
    @Column(name = "svalinea")
    private String svalinea;
    @Column(name = "tiporegistro")
    private String tiporegistro;
    @Column(name = "discount")
    private String discount;
    @Column(name = "price")
    private String price;
    @Column(name = "promprice")
    private String promprice;
    @Column(name = "monthperiod")
    private String monthperiod;
    @Column(name = "installcost")
    private String installcost;
    @Column(name = "linetype")
    private String linetype;
    @Column(name = "paymentmethod")
    private String paymentmethod;
    @Column(name = "cashprice")
    private String cashprice;
    @Column(name = "financingcost")
    private String financingcost;
    @Column(name = "financingmonth")
    private String financingmonth;
    @Column(name = "equipmenttype")
    private String equipmenttype;
    @Column(name = "returnmonth")
    private String returnmonth;
    @Column(name = "returnperiod")
    private String returnperiod;
    @Column(name = "internetspeed")
    private String internetspeed;
    @Column(name = "promspeed")
    private String promspeed;
    @Column(name = "periodpromspeed")
    private String periodpromspeed;
    @Column(name = "internettech")
    private String internettech;
    @Column(name = "tvsignal")
    private String tvsignal;
    @Column(name = "tvtech")
    private String tvtech;
    @Column(name = "equiplinea")
    private String equiplinea;
    @Column(name = "equipinternet")
    private String equipinternet;
    @Column(name = "equiptv")
    private String equiptv;
    @Column(name = "origintvtechnology")
    private String origintvtechnology;
    @Column(name = "typetypedecoorigin")
    private String typetypedecoorigin;
    @Column(name = "typedecoorigin")
    private String typedecoorigin;
    @Column(name = "quantitytypedeco")
    private String quantitytypedeco;
    @Column(name = "internettechnologyorigin")
    private String internettechnologyorigin;
    @Column(name = "internetequipmentorigin")
    private String internetequipmentorigin;
    @Column(name = "comfortcomfortrepeaterequipment")
    private String comfortcomfortrepeaterequipment;
    @Column(name = "comfortrepeater")
    private String comfortrepeater;
    @Column(name = "voicetechnology")
    private String voicetechnology;
    @Column(name = "migrationlogicfromtdmtovoip")
    private String migrationlogicfromtdmtovoip;
    @Column(name = "migratevoiptovoip")
    private String migratevoiptovoip;
    @Column(name = "hfctoftthmigrationlogic")
    private String hfctoftthmigrationlogic;
    @Column(name = "herramienta")
    private String herramienta;
    @Column(name = "prefijo")
    private String prefijo;

    public TdpCatalogEntity(){}
}
