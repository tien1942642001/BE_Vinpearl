package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.HistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorySearchRepository extends JpaRepository<HistorySearch, Long> {
    List<HistorySearch> findAllByCustomerId(Long customerId);
}
