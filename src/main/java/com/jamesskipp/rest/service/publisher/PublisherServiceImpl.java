package com.jamesskipp.rest.service.publisher;

import com.jamesskipp.rest.domain.Publisher;
import com.jamesskipp.rest.repository.PublisherRepository;
import com.jamesskipp.rest.util.AppUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        Publisher response = null;

        if (!this.publisherExists(publisher)) {
            Publisher repoPublisher = this.publisherRepository.insert(publisher);

            response = repoPublisher;
        }

        return response;
    }

    @Override
    public Publisher deletePublisherById(String id) {
        Publisher response = null;

        Publisher deletedPublisher = this.publisherRepository.findById(id).orElse(null);

        if (AppUtils.exists(deletedPublisher)) {
            this.publisherRepository.deleteById(id);

            response = deletedPublisher;
        }

        return response;
    }

    @Override
    public Publisher findPublisher(Publisher publisher) {
        Example<Publisher> publisherExample = this.getPublisherExample(publisher);

        return this.publisherRepository.findOne(publisherExample).orElse(null);
    }

    @Override
    public boolean publisherExists(Publisher publisher) {
        Example<Publisher> publisherExample = this.getPublisherExample(publisher);

        long publisherCount = this.publisherRepository.count(publisherExample);

        return publisherCount > 0;
    }

    /**
     * Returns an example representation of the publisher.
     *
     * Uses case sensitive name and url.
     *
     * @param publisher
     * @return
     */
    private Example<Publisher> getPublisherExample(Publisher publisher) {
        Publisher tempPublisher = new Publisher();
        tempPublisher.setName(publisher.getName());
        tempPublisher.setUrl(publisher.getUrl());

        return Example.of(tempPublisher);
    }
}
