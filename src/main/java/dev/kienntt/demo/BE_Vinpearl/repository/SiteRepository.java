package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}
