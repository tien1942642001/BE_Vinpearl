package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.repository.TourRepository;
import dev.kienntt.demo.BE_Vinpearl.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    @Override
    public List<Tour> findAll() {
        return (List<Tour>) tourRepository.findAll();
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Long id) {
        try{
            tourRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Page<Tour> searchTourPage(Long startTime, Long endTime, Pageable pageable) {
//        PageRequest page_req = new PageRequest(0, buildingId, Sort.Direction.DESC, "idNode");
        return tourRepository.searchBookingRoomsPage(startTime, endTime, pageable);
    }
}
