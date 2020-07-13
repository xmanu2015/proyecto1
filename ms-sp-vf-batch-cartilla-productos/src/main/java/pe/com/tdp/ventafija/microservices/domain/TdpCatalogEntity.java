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
    @Column(name = "codigoproducto")
    private String codigoproducto;
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

    public TdpCatalogEntity(){}

//    public TdpCatalogData(String codigoproducto, String commercialoperation, String segmento, String canal, String entidad, String provincia, String distrito, String campaign, String campaingcode, String priority, String prodtypecode, String producttype, String prodcategorycode, String productcategory, String productcode, String productname, String bloquetv, String svainternet, String svalinea, String tiporegistro, String discount, String price, String promprice, String monthperiod, String installcost, String linetype, String paymentmethod, String cashprice, String financingcost, String financingmonth, String equipmenttype, String returnmonth, String returnperiod, String internetspeed, String promspeed, String periodpromspeed, String internettech, String tvsignal, String tvtech, String equiplinea, String equipinternet, String equiptv, String origintvtechnology, String typetypedecoorigin, String typedecoorigin, String quantitytypedeco, String internettechnologyorigin, String internetequipmentorigin, String comfortcomfortrepeaterequipment, String comfortrepeater, String voicetechnology, String migrationlogicfromtdmtovoip, String migratevoiptovoip, String hfctoftthmigrationlogic, String herramienta) {
//        this.codigoproducto = codigoproducto;
//        this.commercialoperation = commercialoperation;
//        this.segmento = segmento;
//        this.canal = canal;
//        this.entidad = entidad;
//        this.provincia = provincia;
//        this.distrito = distrito;
//        this.campaign = campaign;
//        this.campaingcode = campaingcode;
//        this.priority = priority;
//        this.prodtypecode = prodtypecode;
//        this.producttype = producttype;
//        this.prodcategorycode = prodcategorycode;
//        this.productcategory = productcategory;
//        this.productcode = productcode;
//        this.productname = productname;
//        this.bloquetv = bloquetv;
//        this.svainternet = svainternet;
//        this.svalinea = svalinea;
//        this.tiporegistro = tiporegistro;
//        this.discount = discount;
//        this.price = price;
//        this.promprice = promprice;
//        this.monthperiod = monthperiod;
//        this.installcost = installcost;
//        this.linetype = linetype;
//        this.paymentmethod = paymentmethod;
//        this.cashprice = cashprice;
//        this.financingcost = financingcost;
//        this.financingmonth = financingmonth;
//        this.equipmenttype = equipmenttype;
//        this.returnmonth = returnmonth;
//        this.returnperiod = returnperiod;
//        this.internetspeed = internetspeed;
//        this.promspeed = promspeed;
//        this.periodpromspeed = periodpromspeed;
//        this.internettech = internettech;
//        this.tvsignal = tvsignal;
//        this.tvtech = tvtech;
//        this.equiplinea = equiplinea;
//        this.equipinternet = equipinternet;
//        this.equiptv = equiptv;
//        this.origintvtechnology = origintvtechnology;
//        this.typetypedecoorigin = typetypedecoorigin;
//        this.typedecoorigin = typedecoorigin;
//        this.quantitytypedeco = quantitytypedeco;
//        this.internettechnologyorigin = internettechnologyorigin;
//        this.internetequipmentorigin = internetequipmentorigin;
//        this.comfortcomfortrepeaterequipment = comfortcomfortrepeaterequipment;
//        this.comfortrepeater = comfortrepeater;
//        this.voicetechnology = voicetechnology;
//        this.migrationlogicfromtdmtovoip = migrationlogicfromtdmtovoip;
//        this.migratevoiptovoip = migratevoiptovoip;
//        this.hfctoftthmigrationlogic = hfctoftthmigrationlogic;
//        this.herramienta = herramienta;
//    }


//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getCodigoproducto() {
//        return codigoproducto;
//    }
//
//    public void setCodigoproducto(String codigoproducto) {
//        this.codigoproducto = codigoproducto;
//    }
//
//    public String getCommercialoperation() {
//        return commercialoperation;
//    }
//
//    public void setCommercialoperation(String commercialoperation) {
//        this.commercialoperation = commercialoperation;
//    }
//
//    public String getSegmento() {
//        return segmento;
//    }
//
//    public void setSegmento(String segmento) {
//        this.segmento = segmento;
//    }
//
//    public String getCanal() {
//        return canal;
//    }
//
//    public void setCanal(String canal) {
//        this.canal = canal;
//    }
//
//    public String getEntidad() {
//        return entidad;
//    }
//
//    public void setEntidad(String entidad) {
//        this.entidad = entidad;
//    }
//
//    public String getProvincia() {
//        return provincia;
//    }
//
//    public void setProvincia(String provincia) {
//        this.provincia = provincia;
//    }
//
//    public String getDistrito() {
//        return distrito;
//    }
//
//    public void setDistrito(String distrito) {
//        this.distrito = distrito;
//    }
//
//    public String getCampaign() {
//        return campaign;
//    }
//
//    public void setCampaign(String campaign) {
//        this.campaign = campaign;
//    }
//
//    public String getCampaingcode() {
//        return campaingcode;
//    }
//
//    public void setCampaingcode(String campaingcode) {
//        this.campaingcode = campaingcode;
//    }
//
//    public String getPriority() {
//        return priority;
//    }
//
//    public void setPriority(String priority) {
//        this.priority = priority;
//    }
//
//    public String getProdtypecode() {
//        return prodtypecode;
//    }
//
//    public void setProdtypecode(String prodtypecode) {
//        this.prodtypecode = prodtypecode;
//    }
//
//    public String getProducttype() {
//        return producttype;
//    }
//
//    public void setProducttype(String producttype) {
//        this.producttype = producttype;
//    }
//
//    public String getProdcategorycode() {
//        return prodcategorycode;
//    }
//
//    public void setProdcategorycode(String prodcategorycode) {
//        this.prodcategorycode = prodcategorycode;
//    }
//
//    public String getProductcategory() {
//        return productcategory;
//    }
//
//    public void setProductcategory(String productcategory) {
//        this.productcategory = productcategory;
//    }
//
//    public String getProductcode() {
//        return productcode;
//    }
//
//    public void setProductcode(String productcode) {
//        this.productcode = productcode;
//    }
//
//    public String getProductname() {
//        return productname;
//    }
//
//    public void setProductname(String productname) {
//        this.productname = productname;
//    }
//
//    public String getBloquetv() {
//        return bloquetv;
//    }
//
//    public void setBloquetv(String bloquetv) {
//        this.bloquetv = bloquetv;
//    }
//
//    public String getSvainternet() {
//        return svainternet;
//    }
//
//    public void setSvainternet(String svainternet) {
//        this.svainternet = svainternet;
//    }
//
//    public String getSvalinea() {
//        return svalinea;
//    }
//
//    public void setSvalinea(String svalinea) {
//        this.svalinea = svalinea;
//    }
//
//    public String getTiporegistro() {
//        return tiporegistro;
//    }
//
//    public void setTiporegistro(String tiporegistro) {
//        this.tiporegistro = tiporegistro;
//    }
//
//    public String getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(String discount) {
//        this.discount = discount;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getPromprice() {
//        return promprice;
//    }
//
//    public void setPromprice(String promprice) {
//        this.promprice = promprice;
//    }
//
//    public String getMonthperiod() {
//        return monthperiod;
//    }
//
//    public void setMonthperiod(String monthperiod) {
//        this.monthperiod = monthperiod;
//    }
//
//    public String getInstallcost() {
//        return installcost;
//    }
//
//    public void setInstallcost(String installcost) {
//        this.installcost = installcost;
//    }
//
//    public String getLinetype() {
//        return linetype;
//    }
//
//    public void setLinetype(String linetype) {
//        this.linetype = linetype;
//    }
//
//    public String getPaymentmethod() {
//        return paymentmethod;
//    }
//
//    public void setPaymentmethod(String paymentmethod) {
//        this.paymentmethod = paymentmethod;
//    }
//
//    public String getCashprice() {
//        return cashprice;
//    }
//
//    public void setCashprice(String cashprice) {
//        this.cashprice = cashprice;
//    }
//
//    public String getFinancingcost() {
//        return financingcost;
//    }
//
//    public void setFinancingcost(String financingcost) {
//        this.financingcost = financingcost;
//    }
//
//    public String getFinancingmonth() {
//        return financingmonth;
//    }
//
//    public void setFinancingmonth(String financingmonth) {
//        this.financingmonth = financingmonth;
//    }
//
//    public String getEquipmenttype() {
//        return equipmenttype;
//    }
//
//    public void setEquipmenttype(String equipmenttype) {
//        this.equipmenttype = equipmenttype;
//    }
//
//    public String getReturnmonth() {
//        return returnmonth;
//    }
//
//    public void setReturnmonth(String returnmonth) {
//        this.returnmonth = returnmonth;
//    }
//
//    public String getReturnperiod() {
//        return returnperiod;
//    }
//
//    public void setReturnperiod(String returnperiod) {
//        this.returnperiod = returnperiod;
//    }
//
//    public String getInternetspeed() {
//        return internetspeed;
//    }
//
//    public void setInternetspeed(String internetspeed) {
//        this.internetspeed = internetspeed;
//    }
//
//    public String getPromspeed() {
//        return promspeed;
//    }
//
//    public void setPromspeed(String promspeed) {
//        this.promspeed = promspeed;
//    }
//
//    public String getPeriodpromspeed() {
//        return periodpromspeed;
//    }
//
//    public void setPeriodpromspeed(String periodpromspeed) {
//        this.periodpromspeed = periodpromspeed;
//    }
//
//    public String getInternettech() {
//        return internettech;
//    }
//
//    public void setInternettech(String internettech) {
//        this.internettech = internettech;
//    }
//
//    public String getTvsignal() {
//        return tvsignal;
//    }
//
//    public void setTvsignal(String tvsignal) {
//        this.tvsignal = tvsignal;
//    }
//
//    public String getTvtech() {
//        return tvtech;
//    }
//
//    public void setTvtech(String tvtech) {
//        this.tvtech = tvtech;
//    }
//
//    public String getEquiplinea() {
//        return equiplinea;
//    }
//
//    public void setEquiplinea(String equiplinea) {
//        this.equiplinea = equiplinea;
//    }
//
//    public String getEquipinternet() {
//        return equipinternet;
//    }
//
//    public void setEquipinternet(String equipinternet) {
//        this.equipinternet = equipinternet;
//    }
//
//    public String getEquiptv() {
//        return equiptv;
//    }
//
//    public void setEquiptv(String equiptv) {
//        this.equiptv = equiptv;
//    }
//
//    public String getOrigintvtechnology() {
//        return origintvtechnology;
//    }
//
//    public void setOrigintvtechnology(String origintvtechnology) {
//        this.origintvtechnology = origintvtechnology;
//    }
//
//    public String getTypetypedecoorigin() {
//        return typetypedecoorigin;
//    }
//
//    public void setTypetypedecoorigin(String typetypedecoorigin) {
//        this.typetypedecoorigin = typetypedecoorigin;
//    }
//
//    public String getTypedecoorigin() {
//        return typedecoorigin;
//    }
//
//    public void setTypedecoorigin(String typedecoorigin) {
//        this.typedecoorigin = typedecoorigin;
//    }
//
//    public String getQuantitytypedeco() {
//        return quantitytypedeco;
//    }
//
//    public void setQuantitytypedeco(String quantitytypedeco) {
//        this.quantitytypedeco = quantitytypedeco;
//    }
//
//    public String getInternettechnologyorigin() {
//        return internettechnologyorigin;
//    }
//
//    public void setInternettechnologyorigin(String internettechnologyorigin) {
//        this.internettechnologyorigin = internettechnologyorigin;
//    }
//
//    public String getInternetequipmentorigin() {
//        return internetequipmentorigin;
//    }
//
//    public void setInternetequipmentorigin(String internetequipmentorigin) {
//        this.internetequipmentorigin = internetequipmentorigin;
//    }
//
//    public String getComfortcomfortrepeaterequipment() {
//        return comfortcomfortrepeaterequipment;
//    }
//
//    public void setComfortcomfortrepeaterequipment(String comfortcomfortrepeaterequipment) {
//        this.comfortcomfortrepeaterequipment = comfortcomfortrepeaterequipment;
//    }
//
//    public String getComfortrepeater() {
//        return comfortrepeater;
//    }
//
//    public void setComfortrepeater(String comfortrepeater) {
//        this.comfortrepeater = comfortrepeater;
//    }
//
//    public String getVoicetechnology() {
//        return voicetechnology;
//    }
//
//    public void setVoicetechnology(String voicetechnology) {
//        this.voicetechnology = voicetechnology;
//    }
//
//    public String getMigrationlogicfromtdmtovoip() {
//        return migrationlogicfromtdmtovoip;
//    }
//
//    public void setMigrationlogicfromtdmtovoip(String migrationlogicfromtdmtovoip) {
//        this.migrationlogicfromtdmtovoip = migrationlogicfromtdmtovoip;
//    }
//
//    public String getMigratevoiptovoip() {
//        return migratevoiptovoip;
//    }
//
//    public void setMigratevoiptovoip(String migratevoiptovoip) {
//        this.migratevoiptovoip = migratevoiptovoip;
//    }
//
//    public String getHfctoftthmigrationlogic() {
//        return hfctoftthmigrationlogic;
//    }
//
//    public void setHfctoftthmigrationlogic(String hfctoftthmigrationlogic) {
//        this.hfctoftthmigrationlogic = hfctoftthmigrationlogic;
//    }
//
//    public String getHerramienta() {
//        return herramienta;
//    }
//
//    public void setHerramienta(String herramienta) {
//        this.herramienta = herramienta;
//    }
}
