package com.jamesskipp.rest.util.validators;

import com.jamesskipp.rest.constants.ErrorCodes;
import com.jamesskipp.rest.constants.FieldNames;
import com.jamesskipp.rest.domain.Author;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuthorValidator implements Validator {

    @Override
    public boolean supports(Class authorClass) {
        return Author.class.equals(authorClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;

        if (StringUtils.isEmpty(author.getName())
                && (StringUtils.isEmpty(author.getFirstName()) || StringUtils.isEmpty(author.getLastName()))
                && StringUtils.isEmpty(author.getId())) {
            if (StringUtils.isEmpty(author.getName())
                    && StringUtils.isEmpty(author.getFirstName()) || StringUtils.isEmpty(author.getLastName())) {
                errors.rejectValue(FieldNames.AUTHOR_NAME, ErrorCodes.EMPTY_FIELD);
            }
            if (StringUtils.isEmpty(author.getId())) {
                errors.rejectValue(FieldNames.AUTHOR_ID, ErrorCodes.EMPTY_FIELD);
            }
        }
    }
}

