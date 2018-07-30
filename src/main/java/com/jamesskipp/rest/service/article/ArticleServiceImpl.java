package com.jamesskipp.rest.service.article;

import com.jamesskipp.rest.domain.Article;
import com.jamesskipp.rest.domain.Author;
import com.jamesskipp.rest.domain.Publisher;
import com.jamesskipp.rest.repository.ArticleRepository;
import com.jamesskipp.rest.service.author.AuthorService;
import com.jamesskipp.rest.service.publisher.PublisherService;
import com.jamesskipp.rest.util.AppUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    private AuthorService authorService;

    private PublisherService publisherService;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              AuthorService authorService,
                              PublisherService publisherService) {
        this.articleRepository = articleRepository;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @Override
    public Article createArticle(Article article) {
        Article response = null;
        Article repoArticle = null;

        // Make sure the article doesn't already exist
        if (!this.articleExists(article)) {

            // Get the author and create if they don't already exist
            Author author = article.getAuthor();
            Author repoAuthor;
            boolean authorCreated = false;
            if (!this.authorService.authorExists(author)) {
                repoAuthor = this.authorService.createAuthor(author);

                authorCreated = true;
            } else {
                repoAuthor = this.authorService.findAuthor(author);
            }

            // Set the authorId for the found or created author
            if (AppUtils.exists(repoAuthor)) {
                article.setAuthorId(repoAuthor.getId());
            }

            // Get the publisher and create if they don't already exist
            Publisher publisher = article.getPublisher();
            Publisher repoPublisher;
            boolean publisherCreated = false;
            if (!this.publisherService.publisherExists(publisher)) {
                repoPublisher = this.publisherService.createPublisher(publisher);

                publisherCreated = true;
            } else {
                repoPublisher = this.publisherService.findPublisher(publisher);
            }

            // Set the publisherId of the found or created publisher
            if (AppUtils.exists(repoPublisher)) {
                article.setPublisherId(repoPublisher.getId());
            }

            // Insert the article
            if (!AppUtils.stringsAreEmpty(article.getAuthorId(), article.getPublisherId())) {
                repoArticle = this.articleRepository.insert(article);

                if (AppUtils.exists(repoArticle)) {
                    response = repoArticle;
                } else {
                    // Delete the newly created author if there was an issue
                    if (StringUtils.isNotEmpty(article.getAuthorId()) && authorCreated) {
                        this.authorService.deleteAuthorById(article.getAuthorId());
                    }
                    // Delete the newly created publisher if there was an issue
                    if (StringUtils.isNotEmpty(article.getPublisherId()) && publisherCreated) {
                        this.publisherService.deletePublisherById(article.getPublisherId());
                    }
                }
            } else {
                // Delete the newly created author if there was an issue
                if (StringUtils.isNotEmpty(article.getAuthorId()) && authorCreated) {
                    this.authorService.deleteAuthorById(article.getAuthorId());
                }
                // Delete the newly created publisher if there was an issue
                if (StringUtils.isNotEmpty(article.getPublisherId()) && publisherCreated) {
                    this.publisherService.deletePublisherById(article.getPublisherId());
                }
            }
        }

        return response;
    }

    @Override
    public boolean articleExists(Article article) {
        Article tempArticle = new Article();
        tempArticle.setAuthor(article.getAuthor());
        tempArticle.setTitle(article.getTitle());

        Example<Article> exampleArticle = Example.of(tempArticle);

        Long articleCount = this.articleRepository.count(exampleArticle);

        return articleCount > 0;
    }
}
