package com.jamesskipp.rest.repository;

import com.jamesskipp.rest.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
