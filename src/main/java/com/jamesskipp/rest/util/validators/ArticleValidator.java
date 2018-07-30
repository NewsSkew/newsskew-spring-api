package com.jamesskipp.rest.util.validators;

import com.jamesskipp.rest.constants.ErrorCodes;
import com.jamesskipp.rest.constants.FieldNames;
import com.jamesskipp.rest.domain.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class ArticleValidator implements Validator {

    @Override
    public boolean supports(Class articleClass) {
        return Article.class.equals(articleClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Article article = (Article) target;

        if (StringUtils.isEmpty(article.getTitle())) {
            errors.rejectValue(FieldNames.ARTICLE_TITLE, ErrorCodes.EMPTY_FIELD);
        }

        if (StringUtils.isEmpty(article.getUrl())) {
            errors.rejectValue(FieldNames.ARTICLE_TITLE, ErrorCodes.EMPTY_FIELD);
        }

        if (article.getPublisher() != null) {
            PublisherValidator publisherValidator = new PublisherValidator();
            publisherValidator.validate(article.getPublisher(), errors);
        } else {
            errors.rejectValue(FieldNames.ARTICLE_PUBLISHER, ErrorCodes.EMPTY_FIELD);
        }

        if (article.getAuthor() != null) {
            AuthorValidator authorValidator = new AuthorValidator();
            authorValidator.validate(article.getAuthor(), errors);
        } else {
            errors.rejectValue(FieldNames.ARTICLE_AUTHOR, ErrorCodes.EMPTY_FIELD);
        }
    }
}
