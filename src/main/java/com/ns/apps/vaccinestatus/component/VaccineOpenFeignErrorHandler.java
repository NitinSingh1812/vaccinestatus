package com.ns.apps.vaccinestatus.component;

import feign.Response;
import feign.codec.ErrorDecoder;

public class VaccineOpenFeignErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return null;
    }
}
