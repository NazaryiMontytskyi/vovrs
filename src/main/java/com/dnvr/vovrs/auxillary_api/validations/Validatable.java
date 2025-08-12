package com.dnvr.vovrs.auxillary_api.validations;

public interface Validatable<T> {

    boolean isValid(T toValidate);
}
