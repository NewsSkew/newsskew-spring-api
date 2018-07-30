package com.jamesskipp.rest.service.article;

import com.jamesskipp.rest.domain.Article;
import org.springframework.stereotype.Component;

@Component
public interface ArticleService {

    /**
     * Creates an article and returns it if successful.
     *
     * @param article Article the article to create
     * @return Article the article that was just inserted
     */
    Article createArticle(Article article);

    /**
     * Returns whether or not there is an article within
     * the database with the same title, author.
     *
     * @param article
     * @return
     */
    boolean articleExists(Article article);
}
