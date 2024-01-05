package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.model.*;
import dev.tienvv.demo.BE_Vinpearl.repository.*;
import dev.tienvv.demo.BE_Vinpearl.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ServiceDescriptionRepository serviceDescriptionRepository;
    @Autowired
    private ServiceContentRepository serviceContentRepository;

    @Override
    public List<Service> findAll() {
        return (List<Service>) serviceRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void remove(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Page<Service> search(String name, String description, Long price, Pageable pageable) {
//        return roomTypeRepository.searchRoomTypesPage(acreage, name, pageable);
        return serviceRepository.search(name, description, price, pageable);
    }

    @Override
    public Description saveDescription(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    public List<Description> findAllDescription() {
        return descriptionRepository.findAll();
    }

    @Override
    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public List<Content> findAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public ServiceDescription saveServiceDescriptionContent(Service service) {
        for (Long descriptionId: service.getDescriptionIds()) {
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setDescriptionId(descriptionId);
            serviceDescription.setServiceId(service.getId());
            serviceDescriptionRepository.save(serviceDescription);
        }

        for (Long contentId: service.getContentIds()) {
            ServiceContent serviceContent = new ServiceContent();
            serviceContent.setServiceId(service.getId());
            serviceContent.setContentId(contentId);
            serviceContentRepository.save(serviceContent);
        }

        return null;
    }

}
