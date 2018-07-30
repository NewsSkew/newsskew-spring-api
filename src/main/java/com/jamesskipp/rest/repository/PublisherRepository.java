package com.jamesskipp.rest.repository;

import com.jamesskipp.rest.domain.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, String> {

}
