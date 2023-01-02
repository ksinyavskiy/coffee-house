package com.techinc.coffeehouse.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT, faultStringOrReason = "Coffee bean was not found")
public class CoffeeBeanNotFoundException extends RuntimeException {

}
