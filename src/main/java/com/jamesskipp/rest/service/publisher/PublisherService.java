package com.jamesskipp.rest.service.publisher;

import com.jamesskipp.rest.domain.Publisher;
import org.springframework.stereotype.Component;

@Component
public interface PublisherService {

    /**
     * Create a new publisher
     *
     * @param publisher to be created
     * @return Publisher the publisher that was created
     */
    Publisher createPublisher(Publisher publisher);

    /**
     * Find a publisher by name and url
     *
     * @param publisher
     * @return
     */
    Publisher findPublisher(Publisher publisher);

    /**
     * Remove a publisher by Id
     *
     * @param publisher
     * @return
     */
    Publisher deletePublisherById(String id);

    /**
     * Find out if a publisher with that name and
     * url exists
     *
     * @param publisher
     * @return
     */
    boolean publisherExists(Publisher publisher);

}
