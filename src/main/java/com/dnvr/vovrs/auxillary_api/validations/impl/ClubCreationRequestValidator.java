package com.dnvr.vovrs.auxillary_api.validations.impl;

import com.dnvr.vovrs.auxillary_api.validations.Validatable;
import com.dnvr.vovrs.dto.ClubCreationRequestBody;
import com.dnvr.vovrs.model.ClubDirection;

import java.util.Set;

public class ClubCreationRequestValidator implements Validatable<ClubCreationRequestBody> {
    @Override
    public boolean isValid(ClubCreationRequestBody toValidate) {
        return isLengthOfNameIsValid(toValidate.name()) &&
                isDirectionIsValid(toValidate.direction()) &&
                isNumberOfColeadersIsCorrect(toValidate.coLeadersIds());
    }

    private boolean isLengthOfNameIsValid(String name){
        return name.length() > 0 && name.length() <= 255;
    }

    private boolean isDirectionIsValid(ClubDirection direction){
        return direction != null;
    }

    private boolean isNumberOfColeadersIsCorrect(Set<Long> coleadersIds){
        return coleadersIds.size() < 3 && coleadersIds.size() >= 0;
    }
}
