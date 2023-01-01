package com.techinc.coffeehouse.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

// faultStringOrReason annotation's attribute suppressed the message which is passed to the constructor
@SoapFault(faultCode = FaultCode.CLIENT)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
