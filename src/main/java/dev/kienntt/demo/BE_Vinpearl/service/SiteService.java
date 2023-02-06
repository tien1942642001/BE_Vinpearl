package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Site;
import dev.kienntt.demo.BE_Vinpearl.model.User;

import java.util.Optional;

public interface SiteService {
    Iterable findAll();

    Optional findById(Long id);

    Site save(Site site);

    void remove(Long id);
}
