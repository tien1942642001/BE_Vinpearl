package dev.tienvv.demo.BE_Vinpearl.repository;

import dev.tienvv.demo.BE_Vinpearl.domain.dto.HotelDto;
import dev.tienvv.demo.BE_Vinpearl.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

    @Query("select distinct s from Site s left join Tour t on s.id = t.leavingToId left join BookingTour bt on t.id = bt.tour.id WHERE bt.customerId = :customerId and bt.paymentStatus = 1")
    List<Site> getListSiteCustomer(Long customerId);
}
