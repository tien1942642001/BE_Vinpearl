package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<Service> findAll();

    List<Description> findAllDescription();

    List<Content> findAllContent();

    Optional findById(Long id);

    Service save(Service service);

    Description saveDescription(Description description);

    Content saveContent(Content content);

    void remove(Long id);

    Page<Service> search(String name, String description, Long price, Pageable pageable);

    ServiceDescription saveServiceDescriptionContent(Service service);
}
