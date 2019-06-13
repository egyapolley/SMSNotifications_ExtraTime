package com.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface SoapInterface {


    @WebMethod(operationName = "sendSMS")
    @WebResult(name = "result")
    String sendMesssage(@WebParam(name = "inputValues") SoapRequest request);
}
