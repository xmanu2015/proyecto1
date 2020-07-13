package pe.com.tdp.ventafija.microservices.domain;

public class InventarioPuntosVentaResponseData {

    private String fileName;
    private String codsap;
    private String model;
    private String cod1;
    private String codStorehouse;
    private String state;
    private Double quantity1;
    private Double extra1;
    private Double extra2;
    private Double extra3;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCodsap() {
        return codsap;
    }

    public void setCodsap(String codsap) {
        this.codsap = codsap;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCod1() {
        return cod1;
    }

    public void setCod1(String cod1) {
        this.cod1 = cod1;
    }

    public String getCodStorehouse() {
        return codStorehouse;
    }

    public void setCodStorehouse(String cod_storehouse) {
        this.codStorehouse = cod_storehouse;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(Double quantity1) {
        this.quantity1 = quantity1;
    }

    public Double getExtra1() {
        return extra1;
    }

    public void setExtra1(Double extra1) {
        this.extra1 = extra1;
    }

    public Double getExtra2() {
        return extra2;
    }

    public void setExtra2(Double extra2) {
        this.extra2 = extra2;
    }

    public Double getExtra3() {
        return extra3;
    }

    public void setExtra3(Double extra3) {
        this.extra3 = extra3;
    }
}
