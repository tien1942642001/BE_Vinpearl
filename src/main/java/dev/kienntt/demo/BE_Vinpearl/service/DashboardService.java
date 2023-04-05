package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingTourStatistic;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    List<Map<String, Object>> getTotal();

    List<BookingTourStatistic> statistics();
}
