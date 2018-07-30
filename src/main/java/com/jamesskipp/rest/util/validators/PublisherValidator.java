package com.jamesskipp.rest.util.validators;

import com.jamesskipp.rest.constants.ErrorCodes;
import com.jamesskipp.rest.constants.FieldNames;
import com.jamesskipp.rest.domain.Publisher;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PublisherValidator implements Validator {

    @Override
    public boolean supports(Class publisherClass) {
        return Publisher.class.equals(publisherClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Publisher publisher = (Publisher) target;

        if (StringUtils.isEmpty(publisher.getName()) && StringUtils.isEmpty(publisher.getId())) {
            if (StringUtils.isEmpty(publisher.getName())) {
                errors.rejectValue(FieldNames.PUBLISHER_NAME, ErrorCodes.EMPTY_FIELD);
            }
            if (StringUtils.isEmpty(publisher.getId())) {
                errors.rejectValue(FieldNames.PUBLISHER_ID, ErrorCodes.EMPTY_FIELD);
            }
        }

        if (StringUtils.isEmpty(publisher.getUrl())) {
            errors.rejectValue(FieldNames.PUBLISHER_URL, ErrorCodes.EMPTY_FIELD);
        }
    }

}
