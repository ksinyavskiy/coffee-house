package com.techinc.coffeehouse.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class MilkNotFoundException extends RuntimeException {
    public MilkNotFoundException(String msg) {
        super(msg);
    }
}
