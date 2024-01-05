package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.model.Restaurant;
import dev.tienvv.demo.BE_Vinpearl.repository.RestaurantRepository;
import dev.tienvv.demo.BE_Vinpearl.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void remove(Long id) {
        restaurantRepository.deleteById(id);
    }
}
