package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.HotelDto;
import dev.kienntt.demo.BE_Vinpearl.model.Site;
import dev.kienntt.demo.BE_Vinpearl.model.User;

import java.util.List;
import java.util.Optional;

public interface SiteService {
    Iterable findAll();

    Optional findById(Long id);

    Site save(Site site);

    void remove(Long id);

    List<Site> getListSiteByCustomer(Long customerId);
}
