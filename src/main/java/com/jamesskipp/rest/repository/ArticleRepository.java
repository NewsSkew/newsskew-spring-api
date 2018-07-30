package com.jamesskipp.rest.repository;

import com.jamesskipp.rest.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {

}
