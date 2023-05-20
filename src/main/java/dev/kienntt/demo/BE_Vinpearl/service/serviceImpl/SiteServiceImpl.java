package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.Site;
import dev.kienntt.demo.BE_Vinpearl.model.User;
import dev.kienntt.demo.BE_Vinpearl.repository.SiteRepository;
import dev.kienntt.demo.BE_Vinpearl.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteRepository siteRepository;

    @Override
    public Iterable findAll() {
        return siteRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return siteRepository.findById(id);
    }

    @Override
    public Site save(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public void remove(Long id) {
        siteRepository.deleteById(id);
    }

    @Override
    public List<Site> getListSiteByCustomer(Long customerId) {
        return siteRepository.getListSiteCustomer(customerId);
    }
}
