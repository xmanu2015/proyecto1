package pe.com.tdp.ventafija.microservices.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<E> {

    private String responseCode;
    @JsonInclude(Include.NON_NULL)
    private String responseMessage;
    @JsonInclude(Include.NON_NULL)
    private E responseData;

}