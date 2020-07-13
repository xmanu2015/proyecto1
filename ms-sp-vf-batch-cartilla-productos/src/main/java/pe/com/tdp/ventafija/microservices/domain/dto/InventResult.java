package pe.com.tdp.ventafija.microservices.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class InventResult {
    private Integer insertCount;
    private Integer updateCount;
    private Integer errorCount;
    private Integer orderCount;
    private List<InventSyntaxError> syntaxErrors;

    public InventResult() {
        super();
        this.insertCount = 0;
        this.updateCount = 0;
        this.errorCount = 0;
        this.orderCount = 0;
        this.syntaxErrors = new ArrayList<>();
    }

    public InventResult(Integer insertCount, Integer updateCount, Integer errorCount, Integer orderCount) {
        this.insertCount = insertCount;
        this.updateCount = updateCount;
        this.errorCount = errorCount;
        this.orderCount = orderCount;
    }

    public Integer getInsertCount() {
        return insertCount;
    }

    public void setInsertCount(Integer insertCount) {
        this.insertCount = insertCount;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public List<InventSyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public void setSyntaxErrors(List<InventSyntaxError> syntaxErrors) {
        this.syntaxErrors = syntaxErrors;
    }
}
