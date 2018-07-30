package com.jamesskipp.rest.controller;


import com.jamesskipp.rest.constants.ApiUrlConstants;
import com.jamesskipp.rest.domain.Article;
import com.jamesskipp.rest.service.article.ArticleService;
import com.jamesskipp.rest.util.validators.ArticleValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller For Articles
 * 
 * @author jskipp
 */
@RestController
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(path = ApiUrlConstants.ARTICLES, method = RequestMethod.GET)
    public ResponseEntity<?> getArticleById(@RequestParam String id) {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (StringUtils.isNotEmpty(id)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        }

        return response;
    }

    @RequestMapping(path = ApiUrlConstants.ARTICLES, method = RequestMethod.POST)
    public ResponseEntity<?> createArticle(@RequestBody Article article, BindingResult result) {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ArticleValidator articleValidator = new ArticleValidator();
        articleValidator.validate(article, result);

        if (result.hasErrors()) {
            response = new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            Article serviceResponse = this.articleService.createArticle(article);
            if (serviceResponse != null && StringUtils.isNotEmpty(serviceResponse.getId())) {
                response = new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
            }
        }

        return response;
    }
}