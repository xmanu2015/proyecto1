package pe.com.tdp.ventafija.microservices.domain.dto;


import java.util.Date;

public class ServiceCallEvent {
    private Date eventDateTime;
    private Long id;
    private String username;
    private String msg;
    private String orderId;
    private String docNumber;
    private String serviceCode;
    private String serviceUrl;
    private String serviceRequest;
    private String serviceResponse;
    private String sourceApp;
    private String sourceAppVersion;
    private String result;

    public ServiceCallEvent() {
        super();
    }

    public ServiceCallEvent(ServiceCallEventBuilder builder) {
        super();
        this.eventDateTime = builder.getEventDateTime();
        this.id = builder.getId();
        this.username = builder.getUsername();
        this.msg = builder.getMsg();
        this.orderId = builder.getOrderId();
        this.docNumber = builder.getDocNumber();
        this.serviceCode = builder.getServiceCode();
        this.serviceUrl = builder.getServiceUrl();
        this.serviceRequest = builder.getServiceRequest();
        this.serviceResponse = builder.getServiceResponse();
        this.sourceApp = builder.getSourceApp();
        this.sourceAppVersion = builder.getSourceAppVersion();
        this.result = builder.getResult();
    }

    public ServiceCallEvent(ServiceCallEvent event) {
        super();
        this.eventDateTime = event.getEventDateTime();
        this.id = event.getId();
        this.username = event.getUsername();
        this.msg = event.getMsg();
        this.orderId = event.getOrderId();
        this.docNumber = event.getDocNumber();
        this.serviceCode = event.getServiceCode();
        this.serviceUrl = event.getServiceUrl();
        this.serviceRequest = event.getServiceRequest();
        this.serviceResponse = event.getServiceResponse();
        this.sourceApp = event.getSourceApp();
        this.sourceAppVersion = event.getSourceAppVersion();
        this.result = event.getResult();
    }

    public Date getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(String serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public String getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(String serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public String getSourceApp() {
        return sourceApp;
    }

    public void setSourceApp(String sourceApp) {
        this.sourceApp = sourceApp;
    }

    public String getSourceAppVersion() {
        return sourceAppVersion;
    }

    public void setSourceAppVersion(String sourceAppVersion) {
        this.sourceAppVersion = sourceAppVersion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class ServiceCallEventBuilder {
        private Date eventDateTime;
        private Long id;
        private String username;
        private String msg;
        private String orderId;
        private String docNumber;
        private String serviceCode;
        private String serviceUrl;
        private String serviceRequest;
        private String serviceResponse;
        private String sourceApp;
        private String sourceAppVersion;
        private String result;

        public ServiceCallEventBuilder withEventDateTime(Date eventDateTime) {
            this.eventDateTime = eventDateTime;
            return this;
        }

        public ServiceCallEventBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ServiceCallEventBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public ServiceCallEventBuilder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public ServiceCallEventBuilder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public ServiceCallEventBuilder withDocNumber(String docNumber) {
            this.docNumber = docNumber;
            return this;
        }

        public ServiceCallEventBuilder withServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }

        public ServiceCallEventBuilder withServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
            return this;
        }

        public ServiceCallEventBuilder withServiceRequest(String serviceRequest) {
            this.serviceRequest = serviceRequest;
            return this;
        }

        public ServiceCallEventBuilder withServiceResponse(String serviceResponse) {
            this.serviceResponse = serviceResponse;
            return this;
        }

        public ServiceCallEventBuilder withSourceApp(String sourceApp) {
            this.sourceApp = sourceApp;
            return this;
        }

        public ServiceCallEventBuilder withSourceAppVersion(String sourceAppVersion) {
            this.sourceAppVersion = sourceAppVersion;
            return this;
        }

        public ServiceCallEventBuilder withResult(String result) {
            this.result = result;
            return this;
        }

        public ServiceCallEvent build () {
            return new ServiceCallEvent(this);
        }

        public Date getEventDateTime() {
            return eventDateTime;
        }

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getMsg() {
            return msg;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getDocNumber() {
            return docNumber;
        }

        public String getServiceCode() {
            return serviceCode;
        }

        public String getServiceUrl() {
            return serviceUrl;
        }

        public String getServiceRequest() {
            return serviceRequest;
        }

        public String getServiceResponse() {
            return serviceResponse;
        }

        public String getSourceApp() {
            return sourceApp;
        }

        public String getSourceAppVersion() {
            return sourceAppVersion;
        }

        public String getResult() {
            return result;
        }
    }
}