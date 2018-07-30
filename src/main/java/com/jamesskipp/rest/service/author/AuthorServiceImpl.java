package com.jamesskipp.rest.service.author;

import com.jamesskipp.rest.domain.Author;
import com.jamesskipp.rest.repository.AuthorRepository;
import com.jamesskipp.rest.util.AppUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        Author response = null;

        if (!this.authorExists(author)) {
            Author repoAuthor = authorRepository.insert(author);

            response = repoAuthor;
        }

        return response;
    }

    @Override
    public Author deleteAuthorById(String id) {
        Author response = null;

        Author deletedAuthor = this.authorRepository.findById(id).orElse(null);

        if (AppUtils.exists(deletedAuthor)) {
            this.authorRepository.deleteById(id);

            response = deletedAuthor;
        }

        return response;
    }

    @Override
    public Author findAuthor(Author author) {
        Example<Author> exampleAuthor = this.getExampleFromAuthor(author);

        return this.authorRepository.findOne(exampleAuthor).orElse(null);
    }

    @Override
    public boolean authorExists(Author author) {
        Example<Author> exampleAuthor = this.getExampleFromAuthor(author);

        long authorCount = this.authorRepository.count(exampleAuthor);

        return authorCount > 0;
    }

    /**
     * Returns the example representation of an author
     * for use in queries.
     *
     * Uses first and last name, case sensitive.
     *
     * @param author
     * @return
     */
    private Example<Author> getExampleFromAuthor(Author author) {
        Author tempAuthor = new Author();
        tempAuthor.setFirstName(author.getFirstName());
        tempAuthor.setLastName(author.getLastName());

        return Example.of(tempAuthor);
    }
}
