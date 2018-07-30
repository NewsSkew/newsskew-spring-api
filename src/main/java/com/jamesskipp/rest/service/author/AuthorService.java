package com.jamesskipp.rest.service.author;

import com.jamesskipp.rest.domain.Author;
import org.springframework.stereotype.Component;

@Component
public interface AuthorService {

    /**
     * Creates a new author in the database.
     *
     * @param author Author to be created
     * @return Author the created author
     */
    Author createAuthor(Author author);

    /**
     * Delete an author by its id.
     *
     * @param id
     * @return
     */
    Author deleteAuthorById(String id);

    /**
     * Queries for an author by first and
     * last name.
     *
     * @param author
     * @return
     */
    Author findAuthor(Author author);

    /**
     * Returns whether an author with that
     * first and last name already exists.
     *
     * @param author
     * @return
     */
    boolean authorExists(Author author);
}
